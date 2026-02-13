package ll.kor.java.ssg.repository;

import ll.kor.java.ssg.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository extends Repository {
    private List<Article> articles;

    public ArticleRepository() {
        articles = new ArrayList<>();
    }

    public void add(Article article) {
        articles.add(article);
        lastId++;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Article getArticleById(int id) {
        int index = getArticleIndexById(id);

        if (index != -1) {
            return articles.get(index);
        }

        return null;
    }

    public int getArticleIndexById(int id) {
        int i = 0;
        for (Article article : articles) {
            if (article.id == id) {
                return i;
            }
            i++;
        }

        return -1;
    }

    public void remove(Article foundArticle) {
        articles.remove(foundArticle);
    }
}