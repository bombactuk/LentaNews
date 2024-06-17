package portal.management.edu.traning.logic;

import com.mysql.cj.log.Log;
import portal.management.edu.traning.entity.News;
import portal.management.edu.traning.entity.NewsCategories;

import java.util.List;

public interface NewsLogic {

    List<News> displayAllNews() throws LogicException;

    List<NewsCategories> displayAllNewsCategories() throws LogicException;

    News infoNews(News news) throws LogicException;

    List<News> searchNews(String meaning) throws LogicException;

    List<News> searchCategoriesNews(int idCategories) throws LogicException;

    boolean addNews(News news) throws LogicException;

    boolean editNews(News news) throws LogicException;

    boolean deleteNews(News news) throws LogicException;

}
