package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Complaint;
import id.co.mii.serverapp.models.FollowUp;
import id.co.mii.serverapp.models.History;
import id.co.mii.serverapp.models.Status;
import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.models.dto.request.FollowUpRequest;
import id.co.mii.serverapp.repositories.FollowUpRepository;
import id.co.mii.serverapp.repositories.HistoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class FollowUpService {

  private FollowUpRepository followUpRepository;
  private ModelMapper modelMapper;
  private ComplaintService complaintService;
  private UserService userService;
  private StatusService statusService;
  private HistoryRepository historyRepository;

  public List<FollowUp> getAll() {
    return followUpRepository.findAll();
  }

  public FollowUp createDTO(FollowUpRequest followUpRequest) {
    // validasi complaint id
    Complaint complaint = complaintService.getById(
      followUpRequest.getComplaintId()
    );
    if (complaint == null) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Complaint ID tidak ditemukan"
      );
    }

    // validasi officer id
    User officer = userService.getById(followUpRequest.getOfficerId());
    if (officer == null) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Officer ID tidak ditemukan"
      );
    }

    FollowUp followUp = modelMapper.map(followUpRequest, FollowUp.class);
    followUp.setComplaint(complaint);
    followUp.setUser(officer);

    // Validasi status
    if (followUpRequest.getStatusId() != null) {
      Status status = statusService.getById(followUpRequest.getStatusId());
      if (status == null) {
        throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "ID Status tidak valid"
        );
      }
      complaint.setStatus(status);
    }

    History history = modelMapper.map(followUpRequest, History.class);
    history.setNotes(followUpRequest.getFollowUpNotes());
    history.setComplaint(
      complaintService.getById(followUpRequest.getComplaintId())
    );
    history.setStatus(statusService.getById(followUpRequest.getStatusId()));
    historyRepository.save(history);

    return followUpRepository.save(followUp);
  }

  public FollowUp getById(Integer id) {
    return followUpRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Data FollowUp tidak ditemukan"
        )
      );
  }

  public FollowUp update(Integer id, FollowUp followUp) {
    getById(id);
    followUp.setId(id);
    return followUpRepository.save(followUp);
  }

  public FollowUp delete(Integer id) {
    FollowUp followUp = getById(id);
    followUpRepository.delete(followUp);

    return followUp;
  }
}
