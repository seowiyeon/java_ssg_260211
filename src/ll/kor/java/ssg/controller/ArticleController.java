package ll.kor.java.ssg.controller;

import ll.kor.java.ssg.container.Container;
import ll.kor.java.ssg.dto.Article;
import ll.kor.java.ssg.service.ArticleService;

import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {

    private ArticleService articleService;
    private Scanner sc;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        articleService = Container.articleService;
    }

    @Override
    public void makeTestData() {
        // 🔥 테스트 게시글 2개 생성
        articleService.write("테스트 제목1", "테스트 내용1", 1);
        articleService.write("테스트 제목2", "테스트 내용2", 1);
    }

    @Override
    public void doAction(String cmd, String actionMethodName) {
        switch (actionMethodName) {
            case "write":
                write();
                break;
            case "list":
                list();
                break;
        }
    }

    private void write() {
        System.out.print("제목: ");
        String subject = sc.nextLine();

        System.out.print("내용: ");
        String content = sc.nextLine();

        int memberId = MemberController.getLoginedMember().id;

        articleService.write(subject, content, memberId);
        System.out.println("게시글 작성 완료");
    }

    private void list() {
        List<Article> articles = articleService.getArticles();

        System.out.println("번호 / 제목");
        for (Article article : articles) {
            System.out.println(article.id + " / " + article.subject);
        }
    }
}
