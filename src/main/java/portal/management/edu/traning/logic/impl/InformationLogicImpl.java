package portal.management.edu.traning.logic.impl;

import portal.management.edu.traning.dao.DaoException;
import portal.management.edu.traning.dao.DaoProvider;
import portal.management.edu.traning.dao.InformationDao;
import portal.management.edu.traning.entity.AboutInfo;
import portal.management.edu.traning.entity.ContactCommunication;
import portal.management.edu.traning.entity.Update;
import portal.management.edu.traning.logic.InformationLogic;
import portal.management.edu.traning.logic.LogicException;

import java.util.List;

public class InformationLogicImpl implements InformationLogic {

    private final DaoProvider provider = DaoProvider.getInstance();
    private final InformationDao dao = provider.getInfoDao();

    @Override
    public List<ContactCommunication> allConnectionsWithUs() throws LogicException {

        try {
            return dao.allConnectionsWithUs();
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public AboutInfo infoAbout() throws LogicException {

        try {
            return dao.infoAbout();
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public List<Update> allUpdatesWithUs() throws LogicException {

        try {
            return dao.allUpdatesWithUs();
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public boolean addUpdate(Update update) throws LogicException {

        try {
            return dao.addUpdate(update);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

}
