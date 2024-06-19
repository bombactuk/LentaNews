package portal.management.edu.traning.dao;

import portal.management.edu.traning.entity.User;
import portal.management.edu.traning.entity.UserAuthorizationInfo;
import portal.management.edu.traning.entity.UserInfo;
import portal.management.edu.traning.entity.UserRegistrationInfo;

public interface UserDao {

    User authorisationUser(UserAuthorizationInfo user) throws DaoException;

    User informationViaTokenUser(User user) throws DaoException;

    boolean registrationUser(UserRegistrationInfo user) throws DaoException;

    boolean addTokenUser(User user) throws DaoException;

    UserInfo infoUser(User user) throws DaoException;

    boolean resetTokenUser() throws DaoException;

    boolean editInfoUser(UserInfo userInfo, User user) throws DaoException;

}
