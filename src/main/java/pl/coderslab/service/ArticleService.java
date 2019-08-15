package pl.coderslab.service;

import pl.coderslab.dao.ArticleDao;
import pl.coderslab.dto.Article;
import pl.coderslab.exception.ArticleNotFoundException;

import java.util.Collection;

public class ArticleService {

    private final ArticleDao articleDao;

    public ArticleService() {
        this.articleDao = new ArticleDao();
    }

    public Collection<Article> findAllArticles() {
        return articleDao.findAllArticles();
    }

    public Article findArticleById(final int id) throws ArticleNotFoundException {
        return articleDao.findArticleById(id);
    }

    public int saveArticle(final String title, final String author) {
        return articleDao.saveArticle(title, author);
    }

    public int deleteArticleById(final int id) {
        return articleDao.deleteArticleById(id);
    }

    public int updateArticle(final Article article) {
        return articleDao.updateArticle(article);
    }
}
