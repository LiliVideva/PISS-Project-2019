package bg.sofia.uni.fmi.piss.project.domain;

public class Mail {
    private String receiver;
    private String text;
    private String subject;

    public Mail(String receiver, String text, String subject) {
        this.receiver = receiver;
        this.text = text;
        this.subject = subject;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }

    public String getSubject() {
        return subject;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}