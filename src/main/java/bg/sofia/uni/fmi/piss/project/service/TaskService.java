package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.entity.Task;
import bg.sofia.uni.fmi.piss.project.form.TaskForm;
import bg.sofia.uni.fmi.piss.project.service.result.Result;

import java.util.List;

public interface TaskService {
    Result<Task> getDetails(Long id);

    Result<Task> getOfferedTaskDetails(Long id);

    List<Task> list(Long difficultyId);

    List<Task> list();

    Result<Task> add(Long taskId, TaskForm form);

    Result<Task> update(Long id, TaskForm form);

    Result<Void> delete(Long id);

    Result<Task> solve(Long id, TaskForm form);

    Result<Void> offer(Long id, TaskForm form);

    Result<Task> approve(Long id, TaskForm form);
}
