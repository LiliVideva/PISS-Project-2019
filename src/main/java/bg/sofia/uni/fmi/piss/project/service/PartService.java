package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.domain.Option;
import bg.sofia.uni.fmi.piss.project.entity.Part;

import java.util.List;

public interface PartService {
    List<Part> list();

    Part get(Long id);

    List<Option> getPartOptions();
}
