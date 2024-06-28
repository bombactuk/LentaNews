package portal.management.edu.traning.dao.impl.connection_pool;

import java.sql.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());
    private static final ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private String driverName;
    private String url;
    private String login;
    private String password;
    private int poolSize;

    public ConnectionPool() {

        DBResourceManager dbResourceManager = DBResourceManager.getInstance();

        this.driverName = dbResourceManager.getValue(DBparameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBparameter.DB_URL);
        this.login = dbResourceManager.getValue(DBparameter.DB_LOGIN);
        this.password = dbResourceManager.getValue(DBparameter.DB_PASSWORD);

        this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBparameter.DB_POOL_SIZE));

        try {

            initPoolData();

        } catch (SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);

        }

    }

    private void initPoolData() throws ClassNotFoundException, SQLException {

        Class.forName(driverName);

        connectionQueue = new ArrayBlockingQueue<>(poolSize);
        givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);

        for (int i = 0; i < poolSize; i++) {

            Connection connection = DriverManager.getConnection(url, login, password);

            PooledConnection pooledConnection = new PooledConnection(connection);

            connectionQueue.add(pooledConnection);

        }

    }

    public void dispose() {

        try {

            clearConnectionQueue();

        } catch (SQLException e) {

            LOGGER.log(Level.SEVERE, ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_1);

        }

        try {

            Enumeration<Driver> drivers = DriverManager.getDrivers();

            while (drivers.hasMoreElements()) {

                Driver driver = drivers.nextElement();

                try {

                    DriverManager.deregisterDriver(driver);

                } catch (SQLException e) {

                    LOGGER.log(Level.SEVERE, ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_2 + driver);

                }

                com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();

            }

        } catch (Exception e) {

            LOGGER.log(Level.SEVERE, ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_3, e);

        }

    }

    private void clearConnectionQueue() throws SQLException {

        closeConnestionsQueue(givenAwayConQueue);
        closeConnestionsQueue(connectionQueue);

    }

    public Connection takeConection() throws InterruptedException {

        Connection connection = null;

        connection = connectionQueue.take();
        givenAwayConQueue.add(connection);

        return connection;
    }

    public void closeConnection(ResultSet rs, PreparedStatement st, Connection con) {

        boolean success = closeResultSet(rs);
        success &= closeStatement(st);
        success &= closeConnection(con);

        if (!success) {

            try {

                takeConection();

            } catch (InterruptedException e) {

                LOGGER.log(Level.SEVERE, ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_4, e);

            }

        }

    }

    public void closeConnection(PreparedStatement st, Connection con) {

        boolean success = closeStatement(st);
        success &= closeConnection(con);

        if (!success) {

            try {

                takeConection();

            } catch (InterruptedException e) {

                LOGGER.log(Level.SEVERE, ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_4, e);

            }

        }

    }

    public boolean closeStatement(PreparedStatement st) {

        try {

            if (st != null) {

                st.close();

                return true;

            }

        } catch (SQLException e) {

            LOGGER.log(Level.SEVERE, ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_5, e);

            return false;

        }

        return true;

    }

    public boolean closeConnection(Connection con) {

        try {

            if (con != null) {

                con.close();

                return true;

            }

        } catch (SQLException e) {

            LOGGER.log(Level.SEVERE, ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_6, e);

            return false;
        }

        return true;

    }

    public boolean closeResultSet(ResultSet rs) {

        try {

            if (rs != null) {

                rs.close();

                return true;

            }

        } catch (SQLException e) {

            LOGGER.log(Level.SEVERE, ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_7, e);

            return false;

        }

        return true;

    }

    private void closeConnestionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;

        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    private class PooledConnection implements Connection {

        private Connection connection;

        public PooledConnection(Connection c) throws SQLException {

            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        public void close() throws SQLException {

            if (connection.isClosed()) {
                throw new SQLException(ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_8);
            }

            if (connection.isReadOnly()) {

                connection.setReadOnly(false);

            }

            if (!givenAwayConQueue.remove(this)) {

                throw new SQLException(ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_9);

            }


            if (!connectionQueue.offer(this)) {

                throw new SQLException(ConstantExceptionConnectionPool.CONSTANT_EXCEPTION_10);

            }

        }

        public void commit() throws SQLException {
            connection.commit();
        }

        public boolean isWrapperFor(Class<?> arg0) throws SQLException {
            return connection.isWrapperFor(arg0);
        }

        public <T> T unwrap(Class<T> arg0) throws SQLException {
            return connection.unwrap(arg0);
        }

        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
                                             int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
                                                  int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        public void rollback() throws SQLException {
            connection.rollback();
        }

        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

    }

}
