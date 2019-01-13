package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.dto.TaskDto;
import bg.sofia.uni.fmi.piss.project.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskAssembler {

  public Task toTask(TaskDto taskDto) {
    return Task.builder()
        .title(taskDto.getTitle())
        .content(taskDto.getContent())
        .solutionValue(taskDto.getSolutionValue())
        .solutionContent(taskDto.getSolutionContent())
        .build();
  }

  public TaskDto toTaskDto(Task task) {
    return TaskDto.builder()
        .taskId(task.getId())
        .title(task.getTitle())
        .content(task.getContent())
        .solutionValue(task.getSolutionValue())
        .solutionContent(task.getSolutionContent())
        .difficultyId(task.getDifficulty() != null ? task.getDifficulty().getId() : null)
        .partId(task.getPart() != null ? task.getPart().getId() : null)
        .theoreticalKnowledgeId(task.getTheoreticalKnowledge() != null ? task.getTheoreticalKnowledge().getId() : null)
        .build();
  }
}
