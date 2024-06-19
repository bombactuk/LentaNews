package portal.management.edu.traning.dao.impl;

import portal.management.edu.traning.dao.DaoException;
import portal.management.edu.traning.dao.NewsDao;
import portal.management.edu.traning.dao.impl.connection_pool.ConnectionPool;
import portal.management.edu.traning.dao.impl.mapper.ResultSetBuilder;
import portal.management.edu.traning.dao.impl.mapper.entity.NewsCategoriesMapper;
import portal.management.edu.traning.dao.impl.mapper.entity.NewsMapper;
import portal.management.edu.traning.entity.News;
import portal.management.edu.traning.entity.NewsCategories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class NewsDaoBase implements NewsDao {

    private final ConnectionPool dataBase = ConnectionPool.getInstance();

    private static final String SELECT_NEWS_LIST = "SELECT * FROM news WHERE status = 'active' ORDER BY post_date DESC";

    @Override
    public List<News> displayAllNews() throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_NEWS_LIST);

            ResultSetBuilder<News> userBuilder = new ResultSetBuilder<>(new NewsMapper());

            return userBuilder.buildList(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_NEWS_CATEGORIES_LIST = "SELECT * FROM categories";

    @Override
    public List<NewsCategories> displayAllNewsCategories() throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_NEWS_CATEGORIES_LIST);

            ResultSetBuilder<NewsCategories> userBuilder = new ResultSetBuilder<>(new NewsCategoriesMapper());

            return userBuilder.buildList(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_INFO_NEWS = "SELECT * FROM news WHERE id_news = ?";

    @Override
    public News infoNews(News news) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_INFO_NEWS);

            prSt.setInt(1, news.getIdNews());

            ResultSetBuilder<News> userBuilder = new ResultSetBuilder<>(new NewsMapper());

            return userBuilder.buildObj(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_SEARCH_NEWS = "SELECT * FROM news WHERE CONCAT(title, short_description, post_date)" +
            " LIKE CONCAT('%', ? , '%') AND status = 'active' ORDER BY post_date DESC";

    @Override
    public List<News> searchNews(String meaning) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_SEARCH_NEWS);

            prSt.setString(1, meaning);

            ResultSetBuilder<News> userBuilder = new ResultSetBuilder<>(new NewsMapper());

            return userBuilder.buildList(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_SEARCH_CATEGORIES_NEWS = "SELECT * FROM news n " +
            "JOIN categories c ON n.status = 'active' AND n.categories_id_categories = c.id_categories " +
            "WHERE c.id_categories = ? ORDER BY post_date DESC";

    @Override
    public List<News> searchCategoriesNews(int idCategories) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_SEARCH_CATEGORIES_NEWS);

            prSt.setInt(1, idCategories);

            ResultSetBuilder<News> userBuilder = new ResultSetBuilder<>(new NewsMapper());

            return userBuilder.buildList(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String INSERT_NEWS_INTO_DATA_BASE = "INSERT INTO news" +
            " (title, short_description, content, post_date, id_admin, categories_id_categories, status) VALUES(?,?,?,?,?,?,?)";

    @Override
    public boolean addNews(News news) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            if (news != null) {

                PreparedStatement prSt = dbConnection.prepareCall(INSERT_NEWS_INTO_DATA_BASE);

                prSt.setString(1, news.getTitle());
                prSt.setString(2, news.getShortDescription());
                prSt.setString(3, news.getContent());
                prSt.setString(4, news.getPostDate().toString());
                prSt.setInt(5, news.getIdAdmin());
                prSt.setInt(6, news.getIdCategories());
                prSt.setString(7, ConstantsForPreparedStatementDaoBase.DB_STATUS_ACTIVE);

                return prSt.executeUpdate() > 0;

            } else {

                return false;

            }

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String UPDATE_EDIT_NEWS_INTO_DATA_BASE = "UPDATE news SET title = ?, short_description = ?," +
            " content = ? WHERE id_news = ?";

    @Override
    public boolean editNews(News news) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            if (news != null) {

                PreparedStatement prSt = dbConnection.prepareCall(UPDATE_EDIT_NEWS_INTO_DATA_BASE);

                prSt.setString(1, news.getTitle());
                prSt.setString(2, news.getShortDescription());
                prSt.setString(3, news.getContent());
                prSt.setInt(4, news.getIdNews());

                return prSt.executeUpdate() > 0;

            } else {

                return false;

            }

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String UPDATE_DELETE_NEWS_INTO_DATA_BASE = "UPDATE news SET status = 'inactive' WHERE id_news = ?";

    @Override
    public boolean deleteNews(News news) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            if (news != null) {

                PreparedStatement prSt = dbConnection.prepareCall(UPDATE_DELETE_NEWS_INTO_DATA_BASE);

                prSt.setInt(1, news.getIdNews());

                return prSt.executeUpdate() > 0;

            } else {

                return false;

            }

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

}
