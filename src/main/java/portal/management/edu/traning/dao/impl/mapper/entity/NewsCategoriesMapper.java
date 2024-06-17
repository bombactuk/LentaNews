package portal.management.edu.traning.dao.impl.mapper.entity;

import portal.management.edu.traning.dao.impl.mapper.MapperResultSet;
import portal.management.edu.traning.entity.NewsCategories;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsCategoriesMapper implements MapperResultSet<NewsCategories> {

    @Override
    public NewsCategories map(ResultSet resultSet) throws SQLException {

        return new NewsCategories(

                resultSet.getInt(1),
                resultSet.getString(2)

        );

    }

}
