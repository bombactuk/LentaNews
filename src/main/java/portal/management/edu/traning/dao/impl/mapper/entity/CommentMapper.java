package portal.management.edu.traning.dao.impl.mapper.entity;

import portal.management.edu.traning.dao.impl.mapper.MapperResultSet;
import portal.management.edu.traning.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CommentMapper implements MapperResultSet<Comment> {

    @Override
    public Comment map(ResultSet resultSet) throws SQLException {

        return new Comment(

                resultSet.getInt("id_comments"),
                resultSet.getString("content"),
                LocalDate.parse(resultSet.getString("date_post")),
                resultSet.getInt("users_id_user"),
                resultSet.getString("name")

        );

    }

}
