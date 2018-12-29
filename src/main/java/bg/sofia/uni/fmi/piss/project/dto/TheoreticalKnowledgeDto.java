package bg.sofia.uni.fmi.piss.project.dto;

import bg.sofia.uni.fmi.piss.project.entity.TheoreticalKnowledge;

import java.util.List;

public class TheoreticalKnowledgeDto {
    private Long id;
    private String name;
    private List<TaskDto> tasks;

    TheoreticalKnowledgeDto(TheoreticalKnowledge theoreticalKnowledge) {
        this.id = theoreticalKnowledge.getId();
        this.name = theoreticalKnowledge.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
