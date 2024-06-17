package portal.management.edu.traning.dao;

import portal.management.edu.traning.entity.News;
import portal.management.edu.traning.entity.NewsCategories;

import java.util.List;

public interface NewsDao {

    List<News> displayAllNews() throws DaoException;

    List<NewsCategories> displayAllNewsCategories() throws DaoException;

    News infoNews(News news) throws DaoException;

    List<News> searchNews(String meaning) throws DaoException;

    List<News> searchCategoriesNews(int idCategories) throws DaoException;

    boolean addNews(News news) throws DaoException;

    boolean editNews(News news) throws DaoException;

    boolean deleteNews(News news) throws DaoException;

}
