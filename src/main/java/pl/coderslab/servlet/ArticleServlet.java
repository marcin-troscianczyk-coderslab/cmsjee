package pl.coderslab.servlet;

import pl.coderslab.dto.Article;
import pl.coderslab.exception.ArticleNotFoundException;
import pl.coderslab.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ArticleServlet", urlPatterns = "/articles")
public class ArticleServlet extends HttpServlet {

    private final ArticleService articleService;

    public ArticleServlet() {
        this.articleService = new ArticleService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String articleIdAsString = request.getParameter("id");

        if (articleIdAsString != null) {

            try {

                final int articleId = Integer.parseInt(articleIdAsString);

                try {

                    final Article article = articleService.findArticleById(articleId);
                    request.setAttribute("article", article);

                    request.getRequestDispatcher("WEB-INF/views/singleArticle.jsp").forward(request, response);

                } catch (final ArticleNotFoundException e) {
                    request.getRequestDispatcher("WEB-INF/views/articleNotFound.html").forward(request, response);
                }

            } catch (final NumberFormatException e) {
                System.out.println("Cannot parse article id!");
            }
        } else {

            final Collection<Article> articles = articleService.findAllArticles();
            request.setAttribute("articles", articles);

            request.getRequestDispatcher("WEB-INF/views/allArticles.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String title = request.getParameter("title");
        final String author = request.getParameter("author");

        if (title != null && !title.isBlank() && author != null && !author.isBlank()) {

            articleService.saveArticle(title, author);

            response.sendRedirect("/articles");
        } else {
            request.getRequestDispatcher("WEB-INF/views/badParameters.html").forward(request, response);
        }

    }
}
