package pl.coderslab.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {

    private static DataSource ds;

    public static Connection getConn() throws SQLException {
        return getInstance().getConnection();
    }

    private static DataSource getInstance() {
        if(ds == null) {
            try {
                Context ctx = new InitialContext();
                ds = (DataSource)ctx.lookup("java:comp/env/jdbc/cms");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }

        return ds;
    }
}
