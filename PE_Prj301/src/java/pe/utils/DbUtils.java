/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class DbUtils {
//    Do not change this code
    private static final String DB_NAME = "PRJ_SE1921";
    private static final String DB_USER_NAME = "SA";
    private static final String DB_PASSWORD = "12345";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//load driver
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME; //make connection string
        conn = DriverManager.getConnection(url, DB_USER_NAME, DB_PASSWORD);//open connection. Ham nay khi chay neu co loi xay ra -> SQLException.dc
//        3 buoc tren la buoc so 14 trong so do
        return conn;
    }
}
