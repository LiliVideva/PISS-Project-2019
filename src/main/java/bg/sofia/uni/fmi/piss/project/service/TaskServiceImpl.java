package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import bg.sofia.uni.fmi.piss.project.entity.Part;
import bg.sofia.uni.fmi.piss.project.entity.Task;
import bg.sofia.uni.fmi.piss.project.entity.TheoreticalKnowledge;
import bg.sofia.uni.fmi.piss.project.form.TaskForm;
import bg.sofia.uni.fmi.piss.project.repository.DifficultyRepository;
import bg.sofia.uni.fmi.piss.project.repository.PartRepository;
import bg.sofia.uni.fmi.piss.project.repository.TaskRepository;
import bg.sofia.uni.fmi.piss.project.repository.TheoreticalKnowledgeRepository;
import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class TaskServiceImpl extends BaseService implements TaskService {

    @Autowired
    private DifficultyRepository difficultyRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private TheoreticalKnowledgeRepository theoreticalKnowledgeRepository;

    @Autowired
    private TaskRepository taskRepository;

    private List<Task> offeredTasks = new ArrayList<>();

    @Override
    public Result<Task> getDetails(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return notFound();
        }
        return ok(task);
    }

    @Override
    public Result<Task> getOfferedTaskDetails(Long id) {
        return offeredTasks.isEmpty() ? badRequest()
                : ok(offeredTasks.stream().filter(task -> task.getId() == id).findFirst().get());
    }

    @Override
    public List<Task> list(Long difficultyId) {
        Difficulty difficulty = difficultyRepository.findById(difficultyId).orElse(null);

        if (difficulty != null) {
            return taskRepository.findByPartAndDifficulty(difficulty.getPart(), difficulty);
        }

        return Collections.emptyList();
    }

    @Override
    public List<Task> list() {
        return taskRepository.findByPartIsNullAAndDifficultyIsNull();
    }

    @Override
    public Result<Task> add(Long taskId, TaskForm form) {
        String title = form.getTitle();
        Difficulty difficulty = difficultyRepository.findById(form.getDifficultyId()).orElse(null);
        Part part = partRepository.findById(form.getPartId()).orElse(null);

        if (taskRepository.existsByTitle(title)) {
            return badRequest("name", "error.name.exists");
        }

        Task task = new Task(title, form.getContent(), form.getSolutionValue(), form.getSolutionContent(),
                difficulty, part, theoreticalKnowledgeRepository.findById(form.getTheoreticalKnowledgeId()).orElse(null));

        verify(save(task));

        return created(task);
    }

    @Override
    public Result<Task> update(Long id, TaskForm form) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return notFound();
        }
        String title = form.getTitle();
        if (isBlank(title)) {
            return badRequest();
        }

        task.setContent(form.getContent());
        task.setSolutionValue(form.getSolutionValue());
        task.setSolutionContent(form.getSolutionContent());
        task.setTheoreticalKnowledge(theoreticalKnowledgeRepository.findById(form.getTheoreticalKnowledgeId()).orElse(null));

        verify(save(task));

        return ok(task);
    }

    @Override
    public Result<Void> delete(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return notFound();
        }

        taskRepository.delete(task);
        return noContent();
    }

    @Override
    public Result<Task> solve(Long id, TaskForm form) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return notFound();
        }

        String title = form.getTitle();
        if (isBlank(title) || isBlank(form.getSolutionValue()) || isBlank(form.getSolutionContent())) {
            return badRequest();
        }

        task.setContent(form.getContent());
        task.setSolutionValue(form.getSolutionValue());
        task.setSolutionContent(form.getSolutionContent());
        task.setDifficulty(difficultyRepository.findById(form.getDifficultyId()).orElse(null));
        task.setPart(partRepository.findById(form.getPartId()).orElse(null));
        task.setTheoreticalKnowledge(theoreticalKnowledgeRepository.findById(form.getTheoreticalKnowledgeId()).orElse(null));

        verify(save(task));

        return ok(task);
    }

    @Override
    public Result<Void> offer(Long id, TaskForm form) {
        TheoreticalKnowledge theoreticalKnowledge = theoreticalKnowledgeRepository.findById(form.getTheoreticalKnowledgeId())
                .orElse(null);
        if (isBlank(form.getSolutionValue()) || isBlank(form.getSolutionContent()) || theoreticalKnowledge == null) {
            return badRequest();
        }


        Task offeredTask = new Task(form.getTitle(), form.getContent(), form.getSolutionValue(), form.getSolutionContent(),
                null, null, theoreticalKnowledge);
        offeredTasks.add(offeredTask);
        return noContent();
    }

    @Override
    public Result<Task> approve(Long id, TaskForm form) {
        String title = form.getTitle();
        Difficulty difficulty = difficultyRepository.findById(form.getDifficultyId()).orElse(null);
        Part part = partRepository.findById(form.getPartId()).orElse(null);

        if (taskRepository.existsByTitle(title)) {
            return badRequest("name", "error.name.exists");
        }

        Task task = new Task(title, form.getContent(), form.getSolutionValue(), form.getSolutionContent(),
                difficulty, part, theoreticalKnowledgeRepository.findById(form.getTheoreticalKnowledgeId()).orElse(null));

        verify(save(task));
        offeredTasks.remove(task);

        return created(task);
    }


    private Result<Task> save(Task task) {
        if (task.getPart() == null || task.getTitle() == null || task.getContent() == null) {
            return badRequest();
        }
        return ok(taskRepository.save(task));
    }

}
