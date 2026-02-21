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
 * @author Administrator
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Model connects DB
            con = DbUtils.getConnection();
            if (con != null) {
                //2. Create the SQL String
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?"; //statement
                //3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Statement object
                rs = stm.executeQuery();
                //5. Process result
                // tra ve 1 dong boi username la primary key, dung if
                if (rs.next()) {
                    result = true;
                }//username and password are existed;
            } // connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) { // mo sau dong truoc
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
    throws SQLException,ClassNotFoundException{
       
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Model connects DB
            con = DbUtils.getConnection();
            if (con != null) {
                //2. Create the SQL String
                String sql = "select username, password, lastname, isAdmin "
                        +"From Registration "
                        +"Where lastname Like ? "; //statement
                //3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1,"%" + searchValue + "%");
                
                //4. Execute Statement object
                rs = stm.executeQuery();
                //5. Process result
                while(rs.next()){
                    //5.1 model loads data from resultsSet
                    //5.2 model sets data to DTO object
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto  = new RegistrationDTO(username, password, fullName, role);
                    if(this.accounts == null){
                        this.accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
                }//traverse all rows in table
                // tra ve 1 dong boi username la primary key, dung if

                }//username and password are existed;
           // connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) { // mo sau dong truoc
                stm.close();
            }
            if (con != null) {
                con.close();

            }
        }
    }
}
