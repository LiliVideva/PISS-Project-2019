package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.dto.TheoreticalKnowledgeDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface TheoreticalKnowledgeService {
  ResponseEntity<TheoreticalKnowledgeDto> getTheoreticalKnowledge(Long id);

  ResponseEntity<List<TheoreticalKnowledgeDto>> getAllTheoreticalKnowledges();
}
