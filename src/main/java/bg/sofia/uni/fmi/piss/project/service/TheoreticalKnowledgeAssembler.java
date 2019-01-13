package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.dto.TheoreticalKnowledgeDto;
import bg.sofia.uni.fmi.piss.project.entity.TheoreticalKnowledge;
import org.springframework.stereotype.Component;

@Component
public class TheoreticalKnowledgeAssembler {
  public TheoreticalKnowledge toTheoreticalKnowledge(TheoreticalKnowledgeDto catDto) {
    TheoreticalKnowledge cat = new TheoreticalKnowledge();
    cat.setName(catDto.getName());
    return cat;
  }

  public TheoreticalKnowledgeDto toTheoreticalKnowledgeDto(TheoreticalKnowledge cat) {
    TheoreticalKnowledgeDto catDto = new TheoreticalKnowledgeDto();
    catDto.setName(cat.getName());
    catDto.setTheoreticalKnowledgeId(cat.getId());
    return catDto;
  }
}
