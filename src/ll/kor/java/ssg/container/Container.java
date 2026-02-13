package ll.kor.java.ssg.container;

import ll.kor.java.ssg.repository.ArticleRepository;
import ll.kor.java.ssg.repository.MemberRepository;

public class Container {
    public static ArticleRepository articleRepository;
    public static MemberRepository memberRepository;

    static {
        articleRepository = new ArticleRepository();
        memberRepository = new MemberRepository();
    }
}