package portal.management.edu.traning.logic;

import portal.management.edu.traning.logic.impl.InformationLogicImpl;
import portal.management.edu.traning.logic.impl.UserLogicImpl;

public class LogicProvider {

    private static final LogicProvider instance = new LogicProvider();
    private UserLogic logicUser = new UserLogicImpl();
    private InformationLogic logicInfo = new InformationLogicImpl();

    private LogicProvider() {
    }

    public UserLogic getLogicUser() {
        return this.logicUser;
    }

    public InformationLogic getLogicInfo() {
        return this.logicInfo;
    }

    public static LogicProvider getInstance() {
        return instance;
    }

}
