package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.domain.Option;
import bg.sofia.uni.fmi.piss.project.entity.TheoreticalKnowledge;
import bg.sofia.uni.fmi.piss.project.repository.TheoreticalKnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheoreticalKnowledgeServiceImpl extends BaseService implements TheoreticalKnowledgeService {

    @Autowired
    private TheoreticalKnowledgeRepository theoreticalKnowledgeRepository;

    @Override
    public TheoreticalKnowledge get(Long id) {
        return theoreticalKnowledgeRepository.findById(id).orElse(null);
    }

    @Override
    public List<TheoreticalKnowledge> list() {
        return theoreticalKnowledgeRepository.findAll();
    }

    @Override
    public List<Option> getTheoreticalKnowledgeOptions() {
        return theoreticalKnowledgeRepository.findAll().stream().map(theoreticalKnowledge ->
                new Option(theoreticalKnowledge.getId(), theoreticalKnowledge.getName()))
                .collect(Collectors.toList());
    }
}
