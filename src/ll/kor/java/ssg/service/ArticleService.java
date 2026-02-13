package ll.kor.java.ssg.service;

import ll.kor.java.ssg.container.Container;
import ll.kor.java.ssg.dto.Article;
import ll.kor.java.ssg.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService() {
        articleRepository = Container.articleRepository;
    }

    public List<Article> getForPrintArticles(String searchKeyword) {
        List<Article> articles = articleRepository.getArticles();

        if ( searchKeyword != null && !searchKeyword.isEmpty()) {
            List<Article> forListArticles = new ArrayList<>();

            for (Article article : articles) {
                if (article.subject.contains(searchKeyword)) {
                    forListArticles.add(article);
                }
            }

            return forListArticles;
        }

        return articles;
    }

    public int getArticleIndexById(int id) {
        return articleRepository.getArticleIndexById(id);
    }

    public Article getArticleById(int id) {
        return articleRepository.getArticleById(id);
    }

    public void remove(Article foundArticle) {
        articleRepository.remove(foundArticle);
    }
}