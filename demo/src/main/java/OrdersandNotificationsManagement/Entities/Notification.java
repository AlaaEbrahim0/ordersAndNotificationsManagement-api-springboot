package OrdersandNotificationsManagement.Entities;

public class Notification {
    private String subject;
    private String content;
    private String receipent;

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

    public String getReceipent() {
        return receipent;
    }

    public void setReceipent(String receipent) {
        this.receipent = receipent;
    }

    public Notification(String subject, String content, String receipent) {
        this.subject = subject;
        this.content = content;
        this.receipent = receipent;
    }
}
