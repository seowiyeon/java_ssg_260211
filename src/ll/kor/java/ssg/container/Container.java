package ll.kor.java.ssg.container;

import ll.kor.java.ssg.dto.Article;
import ll.kor.java.ssg.repository.ArticleRepository;
import ll.kor.java.ssg.repository.MemberRepository;
import ll.kor.java.ssg.service.ArticleService;

public class Container {
    public static ArticleRepository articleRepository;
    public static MemberRepository memberRepository;
    public static ArticleService articleService;

    static {
        articleRepository = new ArticleRepository();
        memberRepository = new MemberRepository();
        articleService = new ArticleService();
    }
}