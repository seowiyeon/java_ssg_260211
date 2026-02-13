package ll.kor.java.ssg.service;

import ll.kor.java.ssg.container.Container;
import ll.kor.java.ssg.dto.Article;
import ll.kor.java.ssg.repository.ArticleRepository;

import java.util.List;

public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService() {
        articleRepository = Container.articleRepository;
    }

    public void write(String subject, String content, int memberId) {
        articleRepository.add(subject, content, memberId);
    }

    public List<Article> getArticles() {
        return articleRepository.getAll();
    }
}
