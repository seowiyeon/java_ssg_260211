package ll.kor.java.ssg.controller;

import ll.kor.java.ssg.dto.Article;
import ll.kor.java.ssg.dto.Member;
import ll.kor.java.ssg.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController {
    private List<Article> articles;
    Scanner sc;

    public ArticleController(Scanner sc) {
        articles = new ArrayList<>();
        this.sc = sc;
    }

    public void doWrite() {
        int id = articles.size() + 1;
        String regDate = Util.getNowDateStr();
        IO.print("제목 : ");
        String subject = sc.nextLine();
        IO.print("내용 : ");
        String content = sc.nextLine();

        Article article = new Article(id, regDate, subject, content);
        articles.add(article);

        IO.println(String.format("%d번 글이 생성되었습니다.", id));
    }

    public void showList(String cmd) {
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

        IO.println("번호 | 조회 | 제목");
        for (int i = forListArticles.size() - 1; i >= 0; i--) {
            Article article = forListArticles.get(i);

            IO.println(String.format("%d   | %d   | %s", article.id, article.hit, article.subject));
        }
    }

    public void showDetail(String cmd) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
            return;
        }

        foundArticle.increaseHit();

        IO.println(String.format("번호 : %d", foundArticle.id));
        IO.println(String.format("날짜 : %s", foundArticle.regDate));
        IO.println(String.format("제목 : %s", foundArticle.subject));
        IO.println(String.format("내용 : %s", foundArticle.content));
        IO.println(String.format("조회 : %d", foundArticle.hit));
    }

    public void doDelete(String cmd) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        int foundIndex = getArticleIndexById(id);

        if (foundIndex == -1) {
            IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
            return;
        }

        articles.remove(foundIndex);

        IO.println(String.format("%d번 게시물이 삭제되었습니다.", id));
    }

    public void doModify(String cmd) {
        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
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