package ll.kor.java.ssg.container;

import ll.kor.java.ssg.repository.ArticleRepository;
import ll.kor.java.ssg.repository.MemberRepository;
import ll.kor.java.ssg.service.ArticleService;
import ll.kor.java.ssg.service.MemberService;

public class Container {

    public static ArticleRepository articleRepository;
    public static MemberRepository memberRepository;

    public static ArticleService articleService;
    public static MemberService memberService;

    static {
        articleRepository = new ArticleRepository();
        memberRepository = new MemberRepository();

        articleService = new ArticleService();
        memberService = new MemberService();
    }
}
