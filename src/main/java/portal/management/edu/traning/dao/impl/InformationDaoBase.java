package portal.management.edu.traning.dao.impl;

import portal.management.edu.traning.dao.DaoException;
import portal.management.edu.traning.dao.InformationDao;
import portal.management.edu.traning.dao.impl.configuration.ConfigFilesDataBase;
import portal.management.edu.traning.entity.AboutInfo;
import portal.management.edu.traning.entity.ContactCommunication;
import portal.management.edu.traning.entity.Update;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InformationDaoBase implements InformationDao {

    private final ConfigFilesDataBase dataBase = ConfigFilesDataBase.getInstance();

    private static final String contactsList = "SELECT * FROM contacts_us WHERE status = 'active'";

    @Override
    public List<ContactCommunication> allConnectionsWithUs() throws DaoException {

        List<ContactCommunication> contacts = new ArrayList<>();

        ResultSet resSet;

        try (Connection dbConnection = dataBase.getConnection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(contactsList);

            resSet = prSt.executeQuery();

            while (resSet.next()) {

                contacts.add(new ContactCommunication(resSet.getString(2),
                        resSet.getString(3)));

            }

            return contacts;

        } catch (IOException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String infoAbout = "SELECT * FROM about_portal WHERE status = 'active'";

    @Override
    public AboutInfo infoAbout() throws DaoException {

        ResultSet resSet;

        try (Connection dbConnection = dataBase.getConnection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(infoAbout);

            resSet = prSt.executeQuery();

            if (resSet.next()) {

                return new AboutInfo(resSet.getString(2));

            } else {

                return null;

            }

        } catch (IOException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String updatesList = "SELECT * FROM updates_portal ORDER BY date_post DESC LIMIT 10;";

    @Override
    public List<Update> allUpdatesWithUs() throws DaoException {

        List<Update> updates = new ArrayList<>();

        ResultSet resSet;

        try (Connection dbConnection = dataBase.getConnection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(updatesList);

            resSet = prSt.executeQuery();

            while (resSet.next()) {

                updates.add(new Update(resSet.getString(2), resSet.getString(3),
                        LocalDate.parse(resSet.getString(4))));

            }

            return updates;

        } catch (IOException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String insertUpdateIntoDataBase = "INSERT INTO updates_portal" +
            " (title, content, date_post, id_admin) VALUES(?,?,?,?)";

    @Override
    public boolean addUpdate(Update update) throws DaoException {

        try (Connection dbConnection = dataBase.getConnection()) {

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

        } catch (IOException | SQLException e) {

            throw new DaoException(e);

        }

    }

}
