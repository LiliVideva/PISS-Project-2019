package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.dto.PartDto;
import bg.sofia.uni.fmi.piss.project.entity.Part;
import org.springframework.stereotype.Component;

@Component
public class PartAssembler {
  public Part toPart(PartDto catDto) {
    Part cat = new Part();
    cat.setName(catDto.getName());
    return cat;
  }

  public PartDto toPartDto(Part cat) {
    PartDto catDto = new PartDto();
    catDto.setName(cat.getName());
    catDto.setPartId(cat.getId());
    return catDto;
  }
}
