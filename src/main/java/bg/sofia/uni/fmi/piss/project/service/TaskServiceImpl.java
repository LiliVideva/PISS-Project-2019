package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.domain.Option;
import bg.sofia.uni.fmi.piss.project.dto.TaskDto;
import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import bg.sofia.uni.fmi.piss.project.entity.Part;
import bg.sofia.uni.fmi.piss.project.entity.Task;
import bg.sofia.uni.fmi.piss.project.form.TaskForm;
import bg.sofia.uni.fmi.piss.project.repository.DifficultyRepository;
import bg.sofia.uni.fmi.piss.project.repository.PartRepository;
import bg.sofia.uni.fmi.piss.project.repository.TaskRepository;
import bg.sofia.uni.fmi.piss.project.repository.TheoreticalKnowledgeRepository;
import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Result<Task> save(Task task) {
        if (task.getPart() == null || task.getTitle() == null || task.getContent() == null) {
            return badRequest();
        }
        return ok(taskRepository.save(task));
    }

    @Override
    public Page<TaskDto> list(Difficulty difficulty, Pageable pageable) {
        return null;
    }

    @Override
    public Result<Task> add(Long taskId, TaskForm form) {
        Difficulty difficulty = difficultyRepository.findById(form.getDifficultyId()).orElse(null);
        Part part = partRepository.findById(form.getPartId()).orElse(null);

        if (difficulty == null || part == null) {
            return badRequest();
        }
        if (taskRepository.existsByPartAndDifficulty(part, difficulty)) {
            return badRequest("name", "error.name.exists");
        }

        Task task;

        if (form.getSolutionValue() == null || form.getSolutionContent() == null) {
            task = new Task(form.getTitle(), form.getContent(), difficulty, part);
        } else {
            task = new Task(form.getTitle(), form.getContent(), form.getSolutionValue(), form.getSolutionContent(),
                    difficulty, part, theoreticalKnowledgeRepository.findById(form.getTheoreticalKnowledgeId()).orElse(null));
        }

        verify(save(task));

        return created(task);
    }


    @Override
    public Task get(Long id) {
        return null;
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
    public Result<TaskDto> getDetails(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return notFound();
        }

        TaskDto details = new TaskDto(task);
        return ok(details);
    }

    @Override
    public boolean validateForm(TaskForm form, BindingResult binding) {
        return false;
    }

    @Override
    public List<Option> getTheoreticalKnowledgeOptions() {
        return theoreticalKnowledgeRepository.findAll().stream().map(theoreticalKnowledge ->
                new Option(theoreticalKnowledge.getId(), theoreticalKnowledge.getName()))
                .collect(Collectors.toList());
    }
}
