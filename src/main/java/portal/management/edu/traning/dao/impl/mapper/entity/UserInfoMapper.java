package portal.management.edu.traning.dao.impl.mapper.entity;

import portal.management.edu.traning.dao.impl.ConstantsForPreparedStatementDaoBase;
import portal.management.edu.traning.dao.impl.mapper.ConstantsForInquiriesResultSet;
import portal.management.edu.traning.dao.impl.mapper.MapperResultSet;
import portal.management.edu.traning.entity.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserInfoMapper implements MapperResultSet<UserInfo> {

    @Override
    public UserInfo map(ResultSet resultSet) throws SQLException {

        return new UserInfo(

                resultSet.getString(ConstantsForInquiriesResultSet.RESULT_SET_NAME),
                LocalDate.parse(resultSet.getString(ConstantsForInquiriesResultSet.RESULT_SET_BIRTHDAY)),
                resultSet.getString(ConstantsForInquiriesResultSet.RESULT_SET_COUNTRY)

        );

    }

}
