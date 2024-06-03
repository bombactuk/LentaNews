package portal.management.edu.traning.dao;

import portal.management.edu.traning.dao.impl.InformationDaoBase;
import portal.management.edu.traning.dao.impl.UserDaoBase;

public class DaoProvider {

    private static final DaoProvider INSTANCE;

    static {
        INSTANCE = new DaoProvider();
    }

    private DaoProvider() {
    }

    private UserDao userDao = new UserDaoBase();
    private InformationDao infoDao = new InformationDaoBase();

    public UserDao getUserDao() {
        return userDao;
    }

    public InformationDao getInfoDao() {
        return infoDao;
    }

    public static DaoProvider getInstance() {
        return INSTANCE;
    }

}
