/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.utils;

import java.sql.Connection; //(goi java.sql bat buoc phai sai khi su dung tatca sql trong sql server)
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

    public static Connection getConnection() 
            throws ClassNotFoundException, SQLException {
        Connection conn = null;
        //1. Model load Driver
        // Phai add dc goi java
        // co classnotfoundexception : 2.do chua add driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 1.neu sai bat ki ki tu gi trong day thi se co xuat hien classnotfound
        //tao ra connection string
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
        //3. Open connection
        conn = DriverManager.getConnection(url, DB_USER_NAME, DB_PASSWORD);// dong code nay se phat sinh ra loi SQLException:
                                                                             //1. TCP/IP connection refuse(Disable)
                                                                             //2. Noi dung trong chuoi url connection la do viet sai, port sai
                                                                             //3. Sai password
        return conn;
    }
}
