package portal.management.edu.traning.dao.impl.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetBuilder<T> {

    private final MapperResultSet<T> mapper;

    public ResultSetBuilder(MapperResultSet<T> mapper) {
        this.mapper = mapper;
    }

    public List<T> buildList(PreparedStatement preparedStatement) throws SQLException {

        List<T> result = new ArrayList<>();

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                T obj = mapper.map(resultSet);

                result.add(obj);

            }

        }

        return result;

    }

    public T buildObj(PreparedStatement preparedStatement) throws SQLException {

        T result = null;

        try (ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {

                result = mapper.map(resultSet);

            }

        }

        return result;

    }

}
