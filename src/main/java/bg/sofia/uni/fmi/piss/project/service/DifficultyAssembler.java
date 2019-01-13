package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.dto.DifficultyDto;
import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import org.springframework.stereotype.Component;

@Component
public class DifficultyAssembler {
  public Difficulty toDifficulty(DifficultyDto catDto) {
    Difficulty cat = new Difficulty();
    cat.setName(catDto.getName());
    return cat;
  }

  public DifficultyDto toDifficultyDto(Difficulty cat) {
    DifficultyDto catDto = new DifficultyDto();
    catDto.setName(cat.getName());
    catDto.setDifficultyId(cat.getId());
    return catDto;
  }
}
