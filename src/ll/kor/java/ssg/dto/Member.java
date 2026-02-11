package ll.kor.java.ssg.dto;

public class Member extends Dto {
    public String loginId;
    public String loginPw;

    public Member(int id, String regDate, String loginId, String loginPw) {
        this.id = id;
        this.regDate = regDate;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}