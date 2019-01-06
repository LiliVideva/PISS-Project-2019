package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.domain.Option;
import bg.sofia.uni.fmi.piss.project.entity.Part;
import bg.sofia.uni.fmi.piss.project.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl extends BaseService implements PartService {

    @Autowired
    private PartRepository partRepository;

    @Override
    public Part get(Long id) {
        return partRepository.findById(id).orElse(null);
    }

    @Override
    public List<Part> list() {
        return partRepository.findAll();
    }


    @Override
    public List<Option> getPartOptions() {
        return partRepository.findAll().stream().map(theoreticalKnowledge ->
                new Option(theoreticalKnowledge.getId(), theoreticalKnowledge.getName()))
                .collect(Collectors.toList());
    }
}
