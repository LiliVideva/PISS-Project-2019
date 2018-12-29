package bg.sofia.uni.fmi.piss.project.domain;

public class Option {
    private Long id;
    private String name;
    private boolean selected;

    public Option(Long id, String name, boolean selected) {
        this.id = id;
        this.name = name;
        this.selected = selected;
    }

    public Option(Long id, String name) {
        this(id, name, false);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return String.format("Option[id=%d, name=%s, selected=%s", id, name, selected);
    }
}
