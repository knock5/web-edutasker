package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.History;
import id.co.mii.serverapp.models.dto.request.HistoryRequest;
import id.co.mii.serverapp.repositories.ComplaintRepository;
import id.co.mii.serverapp.repositories.HistoryRepository;
import id.co.mii.serverapp.repositories.StatusRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class HistoryService {

  private HistoryRepository historyRepository;
  private StatusRepository statusRepository;
  private ComplaintRepository complaintRepository;
  private ModelMapper modelMapper;

  public List<History> getAll() {
    return historyRepository.findAll();
  }

  public History createDTO(HistoryRequest historyRequest) {
    History history = modelMapper.map(historyRequest, History.class);

    // set history complaint id
    history.setComplaint(
      complaintRepository
        .findById(historyRequest.getComplaintId())
        .orElseThrow(() ->
          new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Complaint not found"
          )
        )
    );

    // set history status id
    history.setStatus(
      statusRepository
        .findById(historyRequest.getStatusId())
        .orElseThrow(() ->
          new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found")
        )
    );

    return historyRepository.save(history);
  }

  public History getById(Integer id) {
    return historyRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Kosong")
      );
  }

  public History update(Integer id, History history) {
    getById(id);
    history.setId(id);
    return historyRepository.save(history);
  }

  public History delete(Integer id) {
    History history = getById(id);
    historyRepository.delete(history);
    return history;
  }

  // get history by people id
  public List<History> getHistoryByPeopleId(Integer id) {
    return historyRepository.findByComplaintPeopleId(id);
  }
}
