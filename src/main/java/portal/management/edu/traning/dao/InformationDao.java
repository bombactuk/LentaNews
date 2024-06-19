package portal.management.edu.traning.dao;

import portal.management.edu.traning.entity.*;

import java.util.List;

public interface InformationDao {

    List<ContactCommunication> allConnectionsWithUs() throws DaoException;

    AboutInfo infoAbout() throws DaoException;

    boolean editAbout(AboutInfo aboutInfo) throws DaoException;

    List<Update> allUpdatesWithUs() throws DaoException;

    boolean addUpdate(Update update) throws DaoException;

    boolean addContact(ContactCommunication contactCommunication) throws DaoException;

    boolean addComment(Comment comment) throws DaoException;

    List<Comment> allCommentWithUs(News news) throws DaoException;

    boolean deleteComment(Comment comment) throws DaoException;

}
