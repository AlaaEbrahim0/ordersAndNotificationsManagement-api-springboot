package OrdersandNotificationsManagement.Entities;

import java.util.ArrayList;
import java.util.List;

public class NotificationTemplate {
    private int id;
    private static int idCounter = 1;
    private String subject;
    private String content;
    private List<String> availableLanguages;
    private List<String> availableChannels;
    private List<String> placeholders;

    public NotificationTemplate() {
    }

    public NotificationTemplate(String subject, String content, List<String> availableLanguages, List<String> placeholders) {
        id = idCounter;
        this.subject = subject;
        this.content = content;
        this.availableLanguages = availableLanguages;
        this.availableChannels = new ArrayList<>();
        this.availableChannels.add("email");
        availableChannels.add("sms");
        this.placeholders = placeholders;
        idCounter++;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAvailableLanguages() {
        return availableLanguages;
    }

    public void setAvailableLanguages(List<String> availableLanguages) {
        this.availableLanguages = availableLanguages;
    }

    public List<String> getAvailableChannels() {
        return availableChannels;
    }

    public List<String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(List<String> placeholders) {
        this.placeholders = placeholders;
    }

}
