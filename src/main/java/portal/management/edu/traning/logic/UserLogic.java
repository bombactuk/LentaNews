package portal.management.edu.traning.logic;

import portal.management.edu.traning.entity.User;
import portal.management.edu.traning.entity.UserAuthorizationInfo;
import portal.management.edu.traning.entity.UserInfo;
import portal.management.edu.traning.entity.UserRegistrationInfo;


public interface UserLogic {

    User authorisationUser(UserAuthorizationInfo user) throws LogicException;

    User informationViaTokenUser(User user) throws LogicException;

    boolean registrationUser(UserRegistrationInfo user) throws LogicException;

    boolean addTokenUser(User user) throws LogicException;

    UserInfo infoUser(User user) throws LogicException;

    boolean resetTokenUser() throws LogicException;

    boolean editInfoUser(UserInfo userInfo, User user) throws LogicException;

}
