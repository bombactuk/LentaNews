package portal.management.edu.traning.dao.impl;

import portal.management.edu.traning.dao.DaoException;
import portal.management.edu.traning.dao.UserDao;

import portal.management.edu.traning.dao.impl.connectionPool.ConnectionPool;
import portal.management.edu.traning.dao.impl.mapper.ResultSetBuilder;
import portal.management.edu.traning.dao.impl.mapper.entity.UserInfoMapper;
import portal.management.edu.traning.dao.impl.mapper.entity.UserMapper;
import portal.management.edu.traning.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoBase implements UserDao {

    private final ConnectionPool dataBase = ConnectionPool.getInstance();

    private static final String SELECT_ACCOUNT_AUTHORIZATION_USER_AND_INFO = "SELECT " +
            "u.id_user, " +
            "u.info_user_id_info_user, " +
            "r.title " +
            "FROM users u " +
            "JOIN roles_has_users ru ON u.id_user = ru.user_id_user " +
            "JOIN roles r ON ru.role_id_role = r.id_role " +
            "WHERE u.login = ? AND u.password = ?";

    @Override
    public User authorisationUser(UserAuthorizationInfo user) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_ACCOUNT_AUTHORIZATION_USER_AND_INFO);

            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            ResultSetBuilder<User> userBuilder = new ResultSetBuilder<>(new UserMapper());

            return userBuilder.buildObj(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_ACCOUNT_INFORMATION_VAI_TOKEN_USER = "SELECT " +
            "u.id_user, " +
            "u.info_user_id_info_user, " +
            "r.title " +
            "FROM tokens t " +
            "JOIN users u ON t.users_id_user = u.id_user " +
            "JOIN roles_has_users ru ON u.id_user = ru.user_id_user " +
            "JOIN roles r ON ru.role_id_role = r.id_role " +
            "WHERE t.number = ?";

    @Override
    public User informationViaTokenUser(User user) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_ACCOUNT_INFORMATION_VAI_TOKEN_USER);

            prSt.setString(1, user.getToken());

            ResultSetBuilder<User> userBuilder = new ResultSetBuilder<>(new UserMapper());

            return userBuilder.buildObj(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_CHECKING_WHETHER_THERE_IS_AN_ACCOUNT_OR_NOT = "SELECT * FROM users WHERE login = ?";

    private static final String INSERT_ACCOUNT_USER = "INSERT INTO users" +
            " ( login, password, info_user_id_info_user) VALUES(?,?,?)";

    private static final String INSERT_INFO_USER = "INSERT INTO info_users" +
            " ( name, birthday, country) VALUES(?,?,?)";

    private static final String INSERT_ROLE_USER = "INSERT INTO roles_has_users (role_id_role, user_id_user) " +
            "VALUES(?,?)";

    private static final String INSERT_TOKEN_USER = "INSERT INTO tokens (users_id_user) " +
            "VALUES(?)";

    @Override
    public boolean registrationUser(UserRegistrationInfo user) throws DaoException {

        ResultSet resSet;
        ResultSet idKey;
        int idInfoUser = 0;

        synchronized (this) {

            try (Connection dbConnection = dataBase.takeConection()) {

                dbConnection.setAutoCommit(false);

                PreparedStatement prSt = dbConnection.prepareStatement(SELECT_CHECKING_WHETHER_THERE_IS_AN_ACCOUNT_OR_NOT);

                prSt.setString(1, user.getLogin());

                resSet = prSt.executeQuery();

                if (!resSet.next()) {

                    prSt = dbConnection.prepareStatement(INSERT_INFO_USER, PreparedStatement.RETURN_GENERATED_KEYS);

                    prSt.setString(1, user.getName());
                    prSt.setString(2, user.getBirthday().toString());
                    prSt.setString(3, user.getCountry());

                    prSt.executeUpdate();

                    idKey = prSt.getGeneratedKeys();
                    if (idKey.next()) {
                        idInfoUser = idKey.getInt(1);
                    }

                    prSt = dbConnection.prepareCall(INSERT_ACCOUNT_USER);

                    prSt.setString(1, user.getLogin());
                    prSt.setString(2, user.getPassword());
                    prSt.setInt(3, idInfoUser);

                    prSt.executeUpdate();

                    idKey = prSt.getGeneratedKeys();
                    if (idKey.next()) {
                        idInfoUser = idKey.getInt(1);
                    }

                    prSt = dbConnection.prepareCall(INSERT_ROLE_USER);

                    prSt.setInt(1, 1);
                    prSt.setInt(2, idInfoUser);

                    prSt.executeUpdate();

                    prSt = dbConnection.prepareCall(INSERT_TOKEN_USER);

                    prSt.setInt(1, idInfoUser);

                    prSt.executeUpdate();

                    dbConnection.commit();

                    return true;

                } else {

                    return false;

                }

            } catch (InterruptedException | SQLException e) {

                throw new DaoException(e);

            }

        }

    }

    private static final String UPDATE_ADD_TOKEN_USER = "UPDATE tokens SET number = ? WHERE users_id_user = ?";

    @Override
    public boolean addTokenUser(User user) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareCall(UPDATE_ADD_TOKEN_USER);

            prSt.setString(1, user.getToken());
            prSt.setInt(2, user.getIdUser());

            return prSt.executeUpdate() > 0;

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_INFO_USER = "SELECT u.id_user, iu.name, iu.birthday, iu.country " +
            "FROM users u " +
            "JOIN info_users iu ON u.info_user_id_info_user = iu.id_info_user " +
            "WHERE u.id_user = ?";

    @Override
    public UserInfo infoUser(User user) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_INFO_USER);

            prSt.setInt(1, user.getIdUser());

            ResultSetBuilder<UserInfo> userInfoBuilder = new ResultSetBuilder<>(new UserInfoMapper());

            return userInfoBuilder.buildObj(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String UPDATE_RESET_TOKEN_USER = "UPDATE tokens SET number = ? ";

    @Override
    public boolean resetTokenUser() throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {


            PreparedStatement prSt = dbConnection.prepareCall(UPDATE_RESET_TOKEN_USER);

            prSt.setString(1, "");

            prSt.executeUpdate();

            return prSt.executeUpdate() > 0;

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

}
