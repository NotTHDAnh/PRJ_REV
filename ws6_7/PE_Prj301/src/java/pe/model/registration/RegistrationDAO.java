/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

/**
 *
 * @author ADMIN
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
//        Buoc so 14, 15, 16 trong so do MVC
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Model connect Database
            con = DbUtils.getConnection();
            if (con != null) {
                //2. Create the SQL String
                String sql = "Select username "
                        + "From Registration "
                        + "Where username=? " // ? so 1
                        + "And password=?"; // ? so 2
                //Vi tri dau ? tinh tu trai sang -> phai, tren -> duoi
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Excute Statement Object
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    result = true;
                } //username and password are existed
            } // connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastname(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Model connect Database
            con = DbUtils.getConnection();
            if (con != null) {
                //2. Create the SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?"; 
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Excute Statement Object
                rs = stm.executeQuery();
                //5. Process result
                while(rs.next()){
                    // buoc so 16 trong MVC
                    //5.1 Model loads data from resultSet
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 Model set data to DTO Object
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);
                    if(this.accounts == null){
                        this.accounts = new ArrayList<>();
                    }// account is not available
                    this.accounts.add(dto);
                }// traverse all rows in table
            } // connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    //delete account based on primary key
    public boolean deleteAccount(String username)
           throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Model connect Database
            con = DbUtils.getConnection();
            if (con != null) {
                //2. Create the SQL String
                String sql = "Delete from Registration "
                        + "Where username = ?"; 
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Excute Statement Object
                int effectedRows = stm.executeUpdate();
                //5. Process result
                if(effectedRows > 0) {
                    result = true;
                }
    
            } // connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
                return result;

        }
    }
    
    // update password and role based on primary key
    public boolean updatePassRole(String username,String password, boolean role)
           throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Model connect Database
            con = DbUtils.getConnection();
            if (con != null) {
                //2. Create the SQL String
                String sql = "Update Registration "
                        + "set password=?, isAdmin=? "
                        + "Where username=?";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4. Excute Statement Object
                int effectedRows = stm.executeUpdate();
                //5. Process result
                if(effectedRows > 0) {
                    result = true;
                }
    
            } // connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;

    }
    
    public boolean createAccount(RegistrationDTO account)
                   throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Model connect Database
            con = DbUtils.getConnection();
            if (con != null) {
                //2. Create the SQL String
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values ("
                        + "?,?,?,?"
                        + ")";
                //3. Create statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                //4. Excute Statement Object
                int effectedRows = stm.executeUpdate();
                //5. Process result
                if(effectedRows > 0) {
                    result = true;
                }
    
            } // connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
