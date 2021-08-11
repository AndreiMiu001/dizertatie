package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DAO {
	protected final String mConnectionDatabaseName = "jdbc:mysql://127.0.0.1:3306/evot2";
    protected final String mConnectionUserName = "root";
    protected final String mConnectionPassword = "admin";
    protected Connection mConnection = null;
	
    public void connect() throws ClassNotFoundException, SQLException {
        if (mConnection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mConnection = DriverManager.getConnection(mConnectionDatabaseName,
                    mConnectionUserName, mConnectionPassword);
            if (mConnection != null) {
                mConnection.setAutoCommit(false);
            }
        }
    }

    public void disconnect() throws SQLException {
        if (mConnection != null) {
            mConnection.commit();
            mConnection.close();
            mConnection = null;
        }
    }

    public void disconnect(boolean errState) throws SQLException {
        if (mConnection != null && errState == true) {
            mConnection.commit();
            mConnection.close();
        } else if (errState == false) {
            mConnection.rollback();
            mConnection.close();
        }
    }
}
