package portal.management.edu.traning.dao.impl.mapper.entity;

import portal.management.edu.traning.dao.impl.mapper.MapperResultSet;
import portal.management.edu.traning.entity.News;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class NewsMapper implements MapperResultSet<News> {

    @Override
    public News map(ResultSet resultSet) throws SQLException {

        return new News(

                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                LocalDate.parse(resultSet.getString(5)),
                resultSet.getInt(6),
                resultSet.getInt(7)

        );

    }

}
