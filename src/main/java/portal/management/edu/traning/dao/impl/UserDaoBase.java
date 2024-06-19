package portal.management.edu.traning.dao.impl;

import portal.management.edu.traning.dao.DaoException;
import portal.management.edu.traning.dao.UserDao;

import portal.management.edu.traning.dao.impl.connection_pool.ConnectionPool;
import portal.management.edu.traning.dao.impl.mapper.ResultSetBuilder;
import portal.management.edu.traning.dao.impl.mapper.entity.UserInfoMapper;
import portal.management.edu.traning.dao.impl.mapper.entity.UserMapper;
import portal.management.edu.traning.dao.impl.mapper.entity.UserRoleMapper;
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

            ResultSetBuilder<User> userBuilder = new ResultSetBuilder<>(new UserRoleMapper());

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

            ResultSetBuilder<User> userBuilder = new ResultSetBuilder<>(new UserRoleMapper());

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

                try (PreparedStatement prSt = dbConnection.prepareStatement(SELECT_CHECKING_WHETHER_THERE_IS_AN_ACCOUNT_OR_NOT)) {

                    prSt.setString(1, user.getLogin());

                    resSet = prSt.executeQuery();

                    if (!resSet.next()) {

                        PreparedStatement prSt1 = dbConnection.prepareStatement(INSERT_INFO_USER, PreparedStatement.RETURN_GENERATED_KEYS);

                        prSt1.setString(1, user.getName());
                        prSt1.setString(2, user.getBirthday().toString());
                        prSt1.setString(3, user.getCountry());

                        prSt1.executeUpdate();

                        idKey = prSt1.getGeneratedKeys();
                        if (idKey.next()) {
                            idInfoUser = idKey.getInt(1);
                        }

                        PreparedStatement prSt2 = dbConnection.prepareCall(INSERT_ACCOUNT_USER);

                        prSt2.setString(1, user.getLogin());
                        prSt2.setString(2, user.getPassword());
                        prSt2.setInt(3, idInfoUser);

                        prSt2.executeUpdate();

                        idKey = prSt2.getGeneratedKeys();
                        if (idKey.next()) {
                            idInfoUser = idKey.getInt(1);
                        }

                        PreparedStatement prSt3 = dbConnection.prepareCall(INSERT_ROLE_USER);

                        prSt3.setInt(1, 1);
                        prSt3.setInt(2, idInfoUser);

                        prSt3.executeUpdate();

                        PreparedStatement prSt4 = dbConnection.prepareCall(INSERT_TOKEN_USER);

                        prSt4.setInt(1, idInfoUser);

                        prSt4.executeUpdate();

                        dbConnection.commit();

                        return true;

                    } else {

                        return false;

                    }

                } catch (SQLException e) {

                    dbConnection.rollback();

                }

                return false;

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

            prSt.setString(1, ConstantsForPreparedStatementDaoBase.DB_FIELD_EMPTINESS);

            prSt.executeUpdate();

            return prSt.executeUpdate() > 0;

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_INFO_USER_ID = "SELECT * FROM users WHERE id_user = ?";
    private static final String UPDATE_INFO_USER = "UPDATE info_users " +
            "SET name = ?, birthday = ?, country = ? " +
            "WHERE id_info_user = ?";

    @Override
    public boolean editInfoUser(UserInfo userInfo, User user) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareCall(SELECT_INFO_USER_ID);

            prSt.setInt(1, user.getIdUser());

            ResultSetBuilder<User> userBuilder = new ResultSetBuilder<>(new UserMapper());

            PreparedStatement prSt1 = dbConnection.prepareCall(UPDATE_INFO_USER);

            prSt1.setString(1, userInfo.getName());
            prSt1.setString(2, userInfo.getBirthday().toString());
            prSt1.setString(3, userInfo.getCountry());
            prSt1.setInt(4, userBuilder.buildObj(prSt).getIdInfoUser());

            return prSt1.executeUpdate() > 0;

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

}
