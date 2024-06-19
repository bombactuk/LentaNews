package portal.management.edu.traning.logic.impl;

import portal.management.edu.traning.dao.DaoException;
import portal.management.edu.traning.dao.DaoProvider;
import portal.management.edu.traning.dao.InformationDao;
import portal.management.edu.traning.entity.*;
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
    public boolean editAbout(AboutInfo aboutInfo) throws LogicException {

        try {
            return dao.editAbout(aboutInfo);
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

    @Override
    public boolean addContact(ContactCommunication contactCommunication) throws LogicException {

        try {
            return dao.addContact(contactCommunication);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public boolean addComment(Comment comment) throws LogicException {

        try {
            return dao.addComment(comment);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public List<Comment> allCommentWithUs(News news) throws LogicException {

        try {
            return dao.allCommentWithUs(news);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

    @Override
    public boolean deleteComment(Comment comment) throws LogicException {

        try {
            return dao.deleteComment(comment);
        } catch (DaoException e) {
            throw new LogicException(e);
        }

    }

}
