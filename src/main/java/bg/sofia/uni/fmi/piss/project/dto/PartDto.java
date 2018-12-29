package bg.sofia.uni.fmi.piss.project.dto;

import bg.sofia.uni.fmi.piss.project.entity.Part;

import java.util.List;

public class PartDto {
    private Long id;
    private String name;
    private List<DifficultyDto> difficulties;
    private List<TaskDto> tasks;

    public PartDto(Part part) {
        this.id = part.getId();
        this.name = part.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<DifficultyDto> getDifficulties() {
        return difficulties;
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

    public void setDifficulties(List<DifficultyDto> difficulties) {
        this.difficulties = difficulties;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
