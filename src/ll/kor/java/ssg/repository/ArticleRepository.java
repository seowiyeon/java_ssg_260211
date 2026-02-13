package ll.kor.java.ssg.repository;

import ll.kor.java.ssg.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    public List<Article> articles;

    public ArticleRepository() {
        articles = new ArrayList<>();
    }
}
