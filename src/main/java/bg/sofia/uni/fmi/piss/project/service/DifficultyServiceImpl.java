package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.domain.Option;
import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import bg.sofia.uni.fmi.piss.project.repository.DifficultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DifficultyServiceImpl extends BaseService implements DifficultyService {

    @Autowired
    private DifficultyRepository difficultyRepository;

    @Override
    public Difficulty get(Long id) {
        return difficultyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Difficulty> list() {
        return difficultyRepository.findAll();
    }

    @Override
    public List<Option> getDifficultyOptions() {
        return difficultyRepository.findAll().stream().map(theoreticalKnowledge ->
                new Option(theoreticalKnowledge.getId(), theoreticalKnowledge.getName()))
                .collect(Collectors.toList());
    }
}
