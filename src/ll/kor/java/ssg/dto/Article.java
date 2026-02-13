package ll.kor.java.ssg.dto;

public class Article {
    public int id;
    public String subject;
    public String content;
    public int memberId;

    public Article(int id, String subject, String content, int memberId) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.memberId = memberId;
    }
}
