package portal.management.edu.traning.dao.impl;

import portal.management.edu.traning.dao.DaoException;
import portal.management.edu.traning.dao.InformationDao;
import portal.management.edu.traning.dao.impl.connectionPool.ConnectionPool;
import portal.management.edu.traning.dao.impl.mapper.ResultSetBuilder;
import portal.management.edu.traning.dao.impl.mapper.entity.AboutInfoMapper;
import portal.management.edu.traning.dao.impl.mapper.entity.CommentMapper;
import portal.management.edu.traning.dao.impl.mapper.entity.ContactCommunicationMapper;
import portal.management.edu.traning.dao.impl.mapper.entity.UpdateMapper;
import portal.management.edu.traning.entity.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InformationDaoBase implements InformationDao {

    private final ConnectionPool dataBase = ConnectionPool.getInstance();

    private static final String SELECT_CONTACTS_LIST = "SELECT * FROM contacts_us WHERE status = 'active'";

    @Override
    public List<ContactCommunication> allConnectionsWithUs() throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_CONTACTS_LIST);

            ResultSetBuilder<ContactCommunication> contactBuilder = new ResultSetBuilder<>(new ContactCommunicationMapper());

            return contactBuilder.buildList(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_INFO_ABOUT = "SELECT * FROM about_portal WHERE status = 'active'";

    @Override
    public AboutInfo infoAbout() throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_INFO_ABOUT);

            ResultSetBuilder<AboutInfo> aboutInfoBuilder = new ResultSetBuilder<>(new AboutInfoMapper());

            return aboutInfoBuilder.buildObj(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_UPDATES_LIST = "SELECT * FROM updates_portal ORDER BY date_post DESC LIMIT 10;";

    @Override
    public List<Update> allUpdatesWithUs() throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_UPDATES_LIST);

            ResultSetBuilder<Update> updateBuilder = new ResultSetBuilder<>(new UpdateMapper());

            return updateBuilder.buildList(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String INSERT_UPDATE_INTO_DATA_BASE = "INSERT INTO updates_portal" +
            " (title, content, date_post, id_admin) VALUES(?,?,?,?)";

    @Override
    public boolean addUpdate(Update update) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            if (update != null) {

                PreparedStatement prSt = dbConnection.prepareCall(INSERT_UPDATE_INTO_DATA_BASE);

                prSt.setString(1, update.getTitle());
                prSt.setString(2, update.getContent());
                prSt.setString(3, update.getDate().toString());
                prSt.setInt(4, update.getIdAdmin());

                return prSt.executeUpdate() > 0;

            } else {

                return false;

            }

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String INSERT_CONTACT_COMMUNICATION_INTO_DATA_BASE = "INSERT INTO contacts_us" +
            " (img, link, status, id_admin) VALUES(?,?,?,?)";

    @Override
    public boolean addContact(ContactCommunication contactCommunication) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            if (contactCommunication != null) {

                PreparedStatement prSt = dbConnection.prepareCall(INSERT_CONTACT_COMMUNICATION_INTO_DATA_BASE);

                prSt.setString(1, contactCommunication.getImg());
                prSt.setString(2, contactCommunication.getLink());
                prSt.setString(3, contactCommunication.getStatus());
                prSt.setInt(4, contactCommunication.getIdAdmin());

                return prSt.executeUpdate() > 0;

            } else {

                return false;

            }

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String INSERT_COMMENT_INTO_DATA_BASE = "INSERT INTO comments" +
            " (content, date_post, news_id_news, users_id_user) VALUES(?,?,?,?)";

    @Override
    public boolean addComment(Comment comment) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            if (comment != null) {

                PreparedStatement prSt = dbConnection.prepareCall(INSERT_COMMENT_INTO_DATA_BASE);

                prSt.setString(1, comment.getContent());
                prSt.setString(2, comment.getDatePost().toString());
                prSt.setInt(3, comment.getIdNews());
                prSt.setInt(4, comment.getIdUser());

                return prSt.executeUpdate() > 0;

            } else {

                return false;

            }

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String SELECT_COMMENT_LIST = "SELECT c.id_comments, c.content, c.date_post, c.users_id_user, i.name " +
            "FROM comments c " +
            "JOIN users u ON c.users_id_user = u.id_user " +
            "JOIN info_users i ON u.info_user_id_info_user = i.id_info_user " +
            "WHERE c.news_id_news = ? ORDER BY date_post DESC";

    @Override
    public List<Comment> allCommentWithUs(News news) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            PreparedStatement prSt = dbConnection.prepareStatement(SELECT_COMMENT_LIST);

            prSt.setInt(1, news.getIdNews());

            ResultSetBuilder<Comment> commentBuilder = new ResultSetBuilder<>(new CommentMapper());

            return commentBuilder.buildList(prSt);

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

    private static final String DELETE_COMMENT_INTO_DATA_BASE = "DELETE FROM comments WHERE id_comments = ?;";

    @Override
    public boolean deleteComment(Comment comment) throws DaoException {

        try (Connection dbConnection = dataBase.takeConection()) {

            if (comment != null) {

                PreparedStatement prSt = dbConnection.prepareCall(DELETE_COMMENT_INTO_DATA_BASE);

                prSt.setInt(1, comment.getIdComment());

                return prSt.executeUpdate() > 0;

            } else {

                return false;

            }

        } catch (InterruptedException | SQLException e) {

            throw new DaoException(e);

        }

    }

}
