package ll.kor.java.ssg.repository;

import ll.kor.java.ssg.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository extends Repository {

    private List<Article> articles;

    public ArticleRepository() {
        articles = new ArrayList<>();
    }

    public Article add(String subject, String content, int memberId) {
        int id = ++lastId;
        Article article = new Article(id, subject, content, memberId);
        articles.add(article);
        return article;
    }

    public List<Article> getAll() {
        return articles;
    }
}
