package portal.management.edu.traning.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import portal.management.edu.traning.entity.ContactCommunication;
import portal.management.edu.traning.logic.InformationLogic;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener

public class InitializingContactWithUsListener implements ServletContextListener {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final InformationLogic infoLogic = logicProvider.getLogicInfo();

    private static final Logger LOGGER = Logger.getLogger(InitializingContactWithUsListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {

            List<ContactCommunication> contacts = infoLogic.allConnectionsWithUs();

            if (contacts != null) {

                sce.getServletContext().setAttribute("contacts", contacts);

            }

        } catch (LogicException e) {

            LOGGER.log(Level.SEVERE, "Error initializing context", e);

        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
