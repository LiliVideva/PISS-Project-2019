package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.domain.Option;
import bg.sofia.uni.fmi.piss.project.dto.TaskDto;
import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import bg.sofia.uni.fmi.piss.project.entity.Task;
import bg.sofia.uni.fmi.piss.project.form.TaskForm;
import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface TaskService {
    Result<Task> save(Task task);

    Page<TaskDto> list(Difficulty difficulty, Pageable pageable);

    Result<Task> add(Long taskId, TaskForm form);

    Task get(Long id);

    Result<Void> delete(Long id);

    Result<Task> update(Long id, TaskForm taskForm);

    Result<Task> solve(Long id, TaskForm taskForm);

    Result<TaskDto> getDetails(Long id);

    boolean validateForm(TaskForm form, BindingResult binding);

    List<Option> getTheoreticalKnowledgeOptions();
}
