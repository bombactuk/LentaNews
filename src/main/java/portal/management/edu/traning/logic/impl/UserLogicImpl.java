package portal.management.edu.traning.logic.impl;

import portal.management.edu.traning.dao.DaoException;
import portal.management.edu.traning.dao.DaoProvider;
import portal.management.edu.traning.dao.UserDao;
import portal.management.edu.traning.entity.User;
import portal.management.edu.traning.entity.UserAuthorizationInfo;
import portal.management.edu.traning.entity.UserInfo;
import portal.management.edu.traning.entity.UserRegistrationInfo;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.UserLogic;

public class UserLogicImpl implements UserLogic {

    private final DaoProvider provider = DaoProvider.getInstance();
    private final UserDao dao = provider.getUserDao();

    @Override
    public User authorisationUser(UserAuthorizationInfo user) throws LogicException {

        try {
            return dao.authorisationUser(user);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public User informationViaTokenUser(User user) throws LogicException {

        try {
            return dao.informationViaTokenUser(user);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }


    @Override
    public boolean registrationUser(UserRegistrationInfo user) throws LogicException {

        try {
            return dao.registrationUser(user);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public boolean addTokenUser(User user) throws LogicException {

        try {
            return dao.addTokenUser(user);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public UserInfo infoUser(User user) throws LogicException {

        try {
            return dao.infoUser(user);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public boolean resetTokenUser() throws LogicException {

        try {
            return dao.resetTokenUser();
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public boolean editInfoUser(UserInfo userInfo, User user) throws LogicException {

        try {
            return dao.editInfoUser(userInfo, user);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

}
