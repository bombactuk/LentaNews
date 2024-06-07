package portal.management.edu.traning.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapperResultSet<T> {

    T map(ResultSet resultSet) throws SQLException;

}
