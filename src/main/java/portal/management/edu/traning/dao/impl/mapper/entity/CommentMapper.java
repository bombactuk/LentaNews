package portal.management.edu.traning.dao.impl.mapper.entity;

import portal.management.edu.traning.dao.impl.mapper.ConstantsForInquiriesResultSet;
import portal.management.edu.traning.dao.impl.mapper.MapperResultSet;
import portal.management.edu.traning.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CommentMapper implements MapperResultSet<Comment> {

    @Override
    public Comment map(ResultSet resultSet) throws SQLException {

        return new Comment(

                resultSet.getInt(ConstantsForInquiriesResultSet.RESULT_SET_COMMENT_ID),
                resultSet.getString(ConstantsForInquiriesResultSet.RESULT_SET_CONTENT),
                LocalDate.parse(resultSet.getString(ConstantsForInquiriesResultSet.RESULT_SET_DATE_POST)),
                resultSet.getInt(ConstantsForInquiriesResultSet.RESULT_SET_ID_USER_INFO),
                resultSet.getString(ConstantsForInquiriesResultSet.RESULT_SET_NAME)

        );

    }

}
