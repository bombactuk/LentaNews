package portal.management.edu.traning.dao.impl.mapper.entity;

import portal.management.edu.traning.dao.impl.mapper.MapperResultSet;
import portal.management.edu.traning.entity.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserInfoMapper implements MapperResultSet<UserInfo> {

    @Override
    public UserInfo map(ResultSet resultSet) throws SQLException {

        return new UserInfo(

                resultSet.getString("name"),
                LocalDate.parse(resultSet.getString("birthday")),
                resultSet.getString("country")

        );

    }

}
