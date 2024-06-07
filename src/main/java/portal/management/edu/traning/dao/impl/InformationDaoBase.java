package portal.management.edu.traning.dao.impl;

import portal.management.edu.traning.dao.DaoException;
import portal.management.edu.traning.dao.InformationDao;
import portal.management.edu.traning.dao.impl.connectionPool.ConnectionPool;
import portal.management.edu.traning.dao.impl.mapper.ResultSetBuilder;
import portal.management.edu.traning.dao.impl.mapper.entity.AboutInfoMapper;
import portal.management.edu.traning.dao.impl.mapper.entity.ContactCommunicationMapper;
import portal.management.edu.traning.dao.impl.mapper.entity.UpdateMapper;
import portal.management.edu.traning.dao.impl.mapper.entity.UserMapper;
import portal.management.edu.traning.entity.AboutInfo;
import portal.management.edu.traning.entity.ContactCommunication;
import portal.management.edu.traning.entity.Update;
import portal.management.edu.traning.entity.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InformationDaoBase implements InformationDao {

    private final ConnectionPool dataBase = ConnectionPool.getInstance();

    private static final String contactsList = "SELECT * FROM contacts_us WHERE status = 'active'";

    @Override
    public List<ContactCommunication> allConnectionsWithUs() throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(contactsList);

            ResultSetBuilder<ContactCommunication> contactBuilder = new ResultSetBuilder<>(new ContactCommunicationMapper());

            return contactBuilder.buildList(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String infoAbout = "SELECT * FROM about_portal WHERE status = 'active'";

    @Override
    public AboutInfo infoAbout() throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(infoAbout);

            ResultSetBuilder<AboutInfo> aboutInfoBuilder = new ResultSetBuilder<>(new AboutInfoMapper());

            return aboutInfoBuilder.buildObj(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String updatesList = "SELECT * FROM updates_portal ORDER BY date_post DESC LIMIT 10;";

    @Override
    public List<Update> allUpdatesWithUs() throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(updatesList);

            ResultSetBuilder<Update> updateBuilder = new ResultSetBuilder<>(new UpdateMapper());

            return updateBuilder.buildList(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String insertUpdateIntoDataBase = "INSERT INTO updates_portal" +
            " (title, content, date_post, id_admin) VALUES(?,?,?,?)";

    @Override
    public boolean addUpdate(Update update) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            if (update != null) {

                PreparedStatement prSt = dbConnection.prepareCall(insertUpdateIntoDataBase);

                prSt.setString(1, update.getTitle());
                prSt.setString(2, update.getContent());
                prSt.setString(3, update.getDate().toString());
                prSt.setInt(4, update.getIdAdmin());

                return prSt.executeUpdate() > 0;

            } else {

                return false;

            }

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

}
