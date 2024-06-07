package portal.management.edu.traning.dao.impl.mapper.entity;

import portal.management.edu.traning.dao.impl.mapper.MapperResultSet;
import portal.management.edu.traning.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements MapperResultSet<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {

        return new User(

                resultSet.getInt("id_user"),
                resultSet.getString("title")

        );

    }

}
