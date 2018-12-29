package bg.sofia.uni.fmi.piss.project.dto;

import bg.sofia.uni.fmi.piss.project.entity.Task;

import java.util.Set;

public class TaskDto {
    private Task task;

    private Set<TheoreticalKnowledgeDto> theoreticalKnowledges;

    public TaskDto(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public Set<TheoreticalKnowledgeDto> getTheoreticalKnowledges() {
        return theoreticalKnowledges;
    }


    public void setTask(Task task) {
        this.task = task;
    }

    public void setTheoreticalKnowledges(Set<TheoreticalKnowledgeDto> theoreticalKnowledges) {
        this.theoreticalKnowledges = theoreticalKnowledges;
    }
}
