package bg.sofia.uni.fmi.piss.project.form;

import bg.sofia.uni.fmi.piss.project.dto.TaskDto;
import bg.sofia.uni.fmi.piss.project.util.Constants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class TaskForm {

    @NotNull
    @Length(min = Constants.MIN_NAME_LENGTH, max = Constants.MAX_NAME_LENGTH)
    private String title;

    @NotNull
    @Length(max = Constants.MAX_TEXT_LENGTH)
    private String content;

    private String solutionValue;

    private String solutionContent;

    private Long partId;

    private Long difficultyId;

    @NotNull(groups = {Add.class})
    private Long theoreticalKnowledgeId;

    public interface Add extends Default {

    }

    public TaskForm() {

    }

    public TaskForm(TaskDto details) {
        this.title = details.getTask().getTitle();
        this.content = details.getTask().getContent();
        this.solutionValue = details.getTask().getSolutionValue();
        this.solutionContent = details.getTask().getSolutionContent();
        this.theoreticalKnowledgeId = details.getTask().getTheoreticalKnowledge().getId();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getSolutionValue() {
        return solutionValue;
    }

    public String getSolutionContent() {
        return solutionContent;
    }

    public Long getDifficultyId() {
        return difficultyId;
    }

    public Long getPartId() {
        return partId;
    }

    public Long getTheoreticalKnowledgeId() {
        return theoreticalKnowledgeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSolutionValue(String solutionValue) {
        this.solutionValue = solutionValue;
    }

    public void setSolutionContent(String solutionContent) {
        this.solutionContent = solutionContent;
    }

    public void setDifficultyId(Long difficultyId) {
        this.difficultyId = difficultyId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public void setTheoreticalKnowledgeId(Long theoreticalKnowledgeId) {
        this.theoreticalKnowledgeId = theoreticalKnowledgeId;
    }
}
