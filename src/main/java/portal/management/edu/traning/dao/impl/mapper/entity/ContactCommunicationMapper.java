package portal.management.edu.traning.dao.impl.mapper.entity;

import portal.management.edu.traning.dao.impl.mapper.MapperResultSet;
import portal.management.edu.traning.entity.ContactCommunication;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactCommunicationMapper implements MapperResultSet<ContactCommunication> {

    @Override
    public ContactCommunication map(ResultSet resultSet) throws SQLException {

        return new ContactCommunication(

                resultSet.getString(2),
                resultSet.getString(3)

        );

    }

}
