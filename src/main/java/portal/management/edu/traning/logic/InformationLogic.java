package portal.management.edu.traning.logic;

import portal.management.edu.traning.entity.AboutInfo;
import portal.management.edu.traning.entity.ContactCommunication;
import portal.management.edu.traning.entity.Update;

import java.util.List;

public interface InformationLogic {

    List<ContactCommunication> allConnectionsWithUs() throws LogicException;

    AboutInfo infoAbout() throws LogicException;

    List<Update> allUpdatesWithUs() throws LogicException;

}
