package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.domain.Option;
import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import bg.sofia.uni.fmi.piss.project.entity.Part;

import java.util.List;

public interface DifficultyService {
    Difficulty get(Long id);

    List<Option> getDifficultyOptions(Part part);

}
