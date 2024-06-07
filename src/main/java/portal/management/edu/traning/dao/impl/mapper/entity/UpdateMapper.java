package portal.management.edu.traning.dao.impl.mapper.entity;

import portal.management.edu.traning.dao.impl.mapper.MapperResultSet;
import portal.management.edu.traning.entity.Update;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateMapper implements MapperResultSet<Update> {

    @Override
    public Update map(ResultSet resultSet) throws SQLException {

        return new Update(

                resultSet.getString(2),
                resultSet.getString(3),
                LocalDate.parse(resultSet.getString(4))

        );

    }

}
