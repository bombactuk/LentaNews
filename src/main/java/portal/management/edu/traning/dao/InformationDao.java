package portal.management.edu.traning.dao;

import portal.management.edu.traning.entity.AboutInfo;
import portal.management.edu.traning.entity.ContactCommunication;
import portal.management.edu.traning.entity.Update;

import java.util.List;

public interface InformationDao {

    List<ContactCommunication> allConnectionsWithUs() throws DaoException;

    AboutInfo infoAbout() throws DaoException;

    List<Update> allUpdatesWithUs() throws DaoException;

}
