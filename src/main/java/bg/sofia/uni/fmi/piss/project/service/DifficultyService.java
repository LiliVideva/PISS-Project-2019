package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.dto.DifficultyDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface DifficultyService {
  ResponseEntity<DifficultyDto> getDifficulty(Long id);

  ResponseEntity<List<DifficultyDto>> getAllDifficultiesByPart(Long partId);

}
