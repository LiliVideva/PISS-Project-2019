package bg.sofia.uni.fmi.piss.project.dto;

import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import org.springframework.data.domain.Page;

public class DifficultyDto {
    private Long id;
    private String name;
    private Page<TaskDto> tasks;

    public DifficultyDto(Difficulty difficulty) {
        this.id = difficulty.getId();
        this.name = difficulty.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Page<TaskDto> getTasks() {
        return tasks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTasks(Page<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
