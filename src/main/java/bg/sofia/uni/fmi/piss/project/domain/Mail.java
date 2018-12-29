package bg.sofia.uni.fmi.piss.project.domain;

import java.util.Collection;

public class Mail {
    private String appId;
    private Collection<String> receivers;
    private String text;
    private String subject;

    public Mail(String appId, Collection<String> receivers, String text, String subject) {
        this.appId = appId;
        this.receivers = receivers;
        this.text = text;
        this.subject = subject;
    }

    public String getAppId() {
        return appId;
    }

    public Collection<String> getReceivers() {
        return receivers;
    }

    public String getText() {
        return text;
    }

    public String getSubject() {
        return subject;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setReceivers(Collection<String> receivers) {
        this.receivers = receivers;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
