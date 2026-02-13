package ll.kor.java.ssg.controller;

import ll.kor.java.ssg.container.Container;
import ll.kor.java.ssg.dto.Article;
import ll.kor.java.ssg.dto.Member;
import ll.kor.java.ssg.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {
    private List<Article> articles;
    private Scanner sc;
    private String cmd;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        articles = Container.articleRepository.articles;
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "list":
                showList();
                break;
            case "detail":
                showDetail();
                break;
            case "write":
                doWrite();
                break;
            case "modify":
                doModify();
                break;
            case "delete":
                doDelete();
                break;
            default:
                IO.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }

    public void makeTestData() {
        IO.println("테스트를 위한 게시물 데이터를 생성합니다.");

        articles.add(new Article(1, Util.getNowDateStr(), 1, "제목 1", "내용 1", 10));
        articles.add(new Article(2, Util.getNowDateStr(), 2, "제목 2", "내용 2", 43));
        articles.add(new Article(3, Util.getNowDateStr(), 3, "제목 3", "내용 3", 33));
    }

    private void doWrite() {
        int id = articles.size() + 1;
        String regDate = Util.getNowDateStr();
        IO.print("제목 : ");
        String subject = sc.nextLine();
        IO.print("내용 : ");
        String content = sc.nextLine();

        Article article = new Article(id, regDate, loginedMember.id, subject, content);
        articles.add(article);

        IO.println(String.format("%d번 글이 생성되었습니다.", id));
    }

    private void showList() {
        if (articles.isEmpty()) {
            IO.println("게시물이 없습니다.");
            return;
        }

        String searchKeyword = cmd.substring("article list".length()).trim();
        List<Article> forListArticles = articles;

        if (!searchKeyword.isEmpty()) {
            forListArticles = new ArrayList<>();

            for (Article article : articles) {
                if (article.subject.contains(searchKeyword)) {
                    forListArticles.add(article);
                }
            }

            if (forListArticles.isEmpty()) {
                IO.println("검색 결과가 존재하지 않습니다.");
                return;
            }
        }

        IO.println("번호 |     작성자 | 조회 | 제목");
        for (int i = forListArticles.size() - 1; i >= 0; i--) {
            Article article = forListArticles.get(i);

            List<Member> members = Container.memberRepository.members;
            String writerName = "홍길동";

            for ( Member member : members ) {
                if ( article.memberId == member.id ) {
                    writerName = member.name;
                    break;
                }
            }

            IO.println(String.format("%4d | %6s | %4d | %s", article.id, writerName, article.hit, article.subject));
        }
    }

    private void showDetail() {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
            return;
        }

        foundArticle.increaseHit();

        IO.println(String.format("번호 : %d", foundArticle.id));
        IO.println(String.format("작성자 : %d", foundArticle.memberId));
        IO.println(String.format("날짜 : %s", foundArticle.regDate));
        IO.println(String.format("제목 : %s", foundArticle.subject));
        IO.println(String.format("내용 : %s", foundArticle.content));
        IO.println(String.format("조회 : %d", foundArticle.hit));
    }

    private void doDelete() {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
            return;
        }

        if ( foundArticle.memberId != loginedMember.id ) {
            IO.println("권한이 없습니다.");
            return;
        }

        articles.remove(foundArticle);

        IO.println(String.format("%d번 게시물이 삭제되었습니다.", id));
    }

    private void doModify() {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
            return;
        }

        if ( foundArticle.memberId != loginedMember.id ) {
            IO.println("권한이 없습니다.");
            return;
        }

        IO.print("제목 : ");
        String subject = sc.nextLine();
        IO.print("내용 : ");
        String content = sc.nextLine();

        foundArticle.regDate = Util.getNowDateStr();
        foundArticle.subject = subject;
        foundArticle.content = content;

        IO.println(String.format("%d번 게시물이 수정되었습니다.", id));
    }

    private int getArticleIndexById(int id) {
        int i = 0;
        for (Article article : articles) {
            if (article.id == id) {
                return i;
            }
            i++;
        }

        return -1;
    }

    private Article getArticleById(int id) {
        int index = getArticleIndexById(id);

        if (index != -1) {
            return articles.get(index);
        }

        return null;
    }
}