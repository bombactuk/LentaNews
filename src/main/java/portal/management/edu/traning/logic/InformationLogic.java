package portal.management.edu.traning.logic;

import com.mysql.cj.log.Log;
import portal.management.edu.traning.entity.*;

import java.util.List;

public interface InformationLogic {

    List<ContactCommunication> allConnectionsWithUs() throws LogicException;

    AboutInfo infoAbout() throws LogicException;

    List<Update> allUpdatesWithUs() throws LogicException;

    boolean addUpdate(Update update) throws LogicException;

    boolean addContact(ContactCommunication contactCommunication) throws LogicException;

    boolean addComment(Comment comment) throws LogicException;

    List<Comment> allCommentWithUs(News news) throws LogicException;

    boolean deleteComment(Comment comment) throws LogicException;

}
