package ll.kor.java.ssg.dto;

public class Article extends Dto {
    public String subject;
    public String content;
    public int hit;
    public int memberId; // 8

    public Article(int id, String regDate, int memberId, String subject, String content) {
        this(id, regDate, memberId, subject, content, 0);
    }

    public Article(int id, String regDate, int memberId, String subject, String content, int hit) {
        this.id = id;
        this.regDate = regDate;
        this.memberId = memberId;
        this.subject = subject;
        this.content = content;
        this.hit = hit;
    }

    public void increaseHit() {
        hit++;
    }
}