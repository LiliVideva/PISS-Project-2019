package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.domain.Option;
import bg.sofia.uni.fmi.piss.project.entity.TheoreticalKnowledge;

import java.util.List;

public interface TheoreticalKnowledgeService {
    TheoreticalKnowledge get(Long id);

    List<TheoreticalKnowledge> list();

    List<Option> getTheoreticalKnowledgeOptions();
}
