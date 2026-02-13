package ll.kor.java.ssg.repository;

import ll.kor.java.ssg.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository extends Repository {
    public List<Article> articles;

    public ArticleRepository() {
        articles = new ArrayList<>();
    }

    public void add(Article article) {
        articles.add(article);
        lastId++;
    }
}