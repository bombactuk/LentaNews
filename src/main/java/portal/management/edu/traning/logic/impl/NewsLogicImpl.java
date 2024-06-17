package portal.management.edu.traning.logic.impl;

import portal.management.edu.traning.dao.DaoException;
import portal.management.edu.traning.dao.DaoProvider;
import portal.management.edu.traning.dao.NewsDao;
import portal.management.edu.traning.entity.News;
import portal.management.edu.traning.entity.NewsCategories;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.NewsLogic;

import java.util.List;

public class NewsLogicImpl implements NewsLogic {

    private final DaoProvider provider = DaoProvider.getInstance();
    private final NewsDao dao = provider.getNewsDao();

    @Override
    public List<News> displayAllNews() throws LogicException {

        try {
            return dao.displayAllNews();
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public List<NewsCategories> displayAllNewsCategories() throws LogicException {

        try {
            return dao.displayAllNewsCategories();
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public News infoNews(News news) throws LogicException {

        try {
            return dao.infoNews(news);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public List<News> searchNews(String meaning) throws LogicException {

        try {
            return dao.searchNews(meaning);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public List<News> searchCategoriesNews(int idCategories) throws LogicException {

        try {
            return dao.searchCategoriesNews(idCategories);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public boolean addNews(News news) throws LogicException {

        try {
            return dao.addNews(news);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public boolean editNews(News news) throws LogicException {

        try {
            return dao.editNews(news);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public boolean deleteNews(News news) throws LogicException {

        try {
            return dao.deleteNews(news);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

}
