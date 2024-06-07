package portal.management.edu.traning.dao.impl.mapper.entity;

import portal.management.edu.traning.dao.impl.mapper.MapperResultSet;
import portal.management.edu.traning.entity.AboutInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AboutInfoMapper implements MapperResultSet<AboutInfo> {

    @Override
    public AboutInfo map(ResultSet resultSet) throws SQLException {

        return new AboutInfo(

                resultSet.getString(2)

        );

    }

}
