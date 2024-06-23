package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Complaint;
import id.co.mii.serverapp.models.TaskDosen;
import id.co.mii.serverapp.models.dto.request.ComplaintRequest;
import id.co.mii.serverapp.repositories.ComplaintRepository;
import id.co.mii.serverapp.repositories.TaskDosenRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ComplaintService {

  private ComplaintRepository complaintRepository;
  private TaskDosenService taskDosenService;
  private StatusService statusService;
  private PeopleService peopleService;
  private ModelMapper modelMapper;

  public List<Complaint> getAll() {
    return complaintRepository.findAll();
  }

  public Complaint createDTO(ComplaintRequest complaintRequest) {
    if (
      complaintRequest.getBody() == null || complaintRequest.getBody().isEmpty()
    ) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Body tidak boleh kosong"
      );
    }

    if (
      complaintRequest.getPeopleId() == null ||
      complaintRequest.getPeopleId() == 0
    ) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "People Id tidak boleh kosong"
      );
    }

    Complaint complaint = modelMapper.map(complaintRequest, Complaint.class);

    complaint.setStatus(statusService.findByName("terkirim"));
    complaint.setPeople(peopleService.getById(complaintRequest.getPeopleId()));
    complaint.setTaskDosen(
      taskDosenService.getById(complaintRequest.getTaskDosenId())
    );

    return complaintRepository.save(complaint);
  }

  public Complaint getById(Integer id) {
    return complaintRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Kosong")
      );
  }

  public Complaint delete(Integer id) {
    Complaint complaint = getById(id);
    complaintRepository.delete(complaint);
    return complaint;
  }

  // get task list not resolved
  public List<Complaint> findAllByStatusName() {
    return complaintRepository.findAllByStatusNameNotSelesai();
  }

  // get all task list by user id
  public List<Complaint> getComplaintByUserId(Integer userId) {
    return complaintRepository.findByPeopleId(userId);
  }

  // get task list by dosen id
  public List<Complaint> getTaskByDosenId(Integer id) {
    return complaintRepository.findAllByTaskDosenPeopleId(id);
  }
}
