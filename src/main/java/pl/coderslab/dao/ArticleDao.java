package pl.coderslab.dao;

import pl.coderslab.dto.Article;
import pl.coderslab.exception.ArticleNotFoundException;
import pl.coderslab.util.DBUtil;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ArticleDao {

    public Collection<Article> findAllArticles() {

        try (final Connection connection = DBUtil.getConn();
             final Statement stmt = connection.createStatement()) {

            try (final ResultSet rs = stmt.executeQuery("select id, title, author from article")) {

                final Collection<Article> result = new ArrayList<>();

                while (rs.next()) {

                    final int id = rs.getInt("id");
                    final String title = rs.getString("title");
                    final String author = rs.getString("author");

                    final Article article = new Article(id, title, author);

                    result.add(article);
                }

                return result;
            } catch (final SQLException e) {
                System.out.println(e);
            };

        } catch (final SQLException e) {
            System.out.println(e);
        }

        return Collections.emptyList();
    }

    public Article findArticleById(final int id) throws ArticleNotFoundException {

        try (final Connection connection = DBUtil.getConn();
             final PreparedStatement stmt = connection.prepareStatement("select id, title, author from article where id = ?")) {

            stmt.setInt(1, id);

            try (final ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    final String title = rs.getString("title");
                    final String author = rs.getString("author");

                    final Article article = new Article(id, title, author);

                    return article;
                }

            } catch (final SQLException e) {
                System.out.println(e);
            };

        } catch (final SQLException e) {
            System.out.println(e);
        }

        throw new ArticleNotFoundException();

    }

    public int saveArticle(final String title, final String author) {

        try (final Connection connection = DBUtil.getConn();
             final PreparedStatement stmt =
                     connection.prepareStatement("insert into article(title, author) values (?, ?)")) {

            stmt.setString(1, title);
            stmt.setString(2, author);

            int result = stmt.executeUpdate();

            return result;

        } catch (final SQLException e) {
            System.out.println(e);
        }

        return 0;
    }


    public int deleteArticleById(final int id) {

        try (final Connection connection = DBUtil.getConn();
             final PreparedStatement stmt =
                     connection.prepareStatement("delete from article a where a.id = ?")) {

            stmt.setInt(1, id);

            int result = stmt.executeUpdate();

            return result;

        } catch (final SQLException e) {
            System.out.println(e);
        }

        return 0;

    }


    public int updateArticle(final Article article) {


        try (final Connection connection = DBUtil.getConn();
             final PreparedStatement stmt =
                     connection.prepareStatement("update article a set a.author = ?, a.title = ? where a.id = ?")) {

            stmt.setString(1, article.getAuthor());
            stmt.setString(2, article.getTitle());
            stmt.setInt(3, article.getId());

            int result = stmt.executeUpdate();

            return result;

        } catch (final SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

}
