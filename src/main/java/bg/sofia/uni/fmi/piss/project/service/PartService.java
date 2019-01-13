package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.dto.PartDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface PartService {
  ResponseEntity<PartDto> getPart(Long id);

  ResponseEntity<List<PartDto>> getAllParts();
}
