package portal.management.edu.traning.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import portal.management.edu.traning.dao.impl.connection_pool.ConnectionPool;

@WebListener

public class DataBaseConnectionPoolListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ConnectionPool.getInstance();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        ConnectionPool.getInstance().dispose();

    }

}
