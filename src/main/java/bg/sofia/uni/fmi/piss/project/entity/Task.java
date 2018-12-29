package bg.sofia.uni.fmi.piss.project.entity;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "solution_value")
    private String solutionValue;

    @Column(name = "solution_content")
    private String solutionContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theoretical_knowledge_id")
    private TheoreticalKnowledge theoreticalKnowledge;

    public Task(String title, String content, String solutionValue, String solutionContent, Difficulty difficulty,
                Part part, TheoreticalKnowledge theoreticalKnowledge) {
        this.title = title;
        this.content = content;
        this.solutionValue = solutionValue;
        this.solutionContent = solutionContent;
        this.difficulty = difficulty;
        this.part = part;
        this.theoreticalKnowledge = theoreticalKnowledge;
    }

    public Task(String title, String content, Difficulty difficulty, Part part) {
        this(title, content, null, null, difficulty, part, null);
    }

    public Long getId() {
        return id;
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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Part getPart() {
        return part;
    }

    public TheoreticalKnowledge getTheoreticalKnowledge() {
        return theoreticalKnowledge;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public void setTheoreticalKnowledge(TheoreticalKnowledge theoreticalKnowledge) {
        this.theoreticalKnowledge = theoreticalKnowledge;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return String.format("Task[id=%d, title='%s', content='%s', solution value='%s', solution content='%s']",
                id, title, content, solutionValue, solutionContent);
    }
}
