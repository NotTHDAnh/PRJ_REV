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
 * @author David
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
                //2. Model manipulates DB
                //2.1 Create SQL String
                String sql = "Select username "
                        + "From Registration "
                        + "Where username=? "
                        + "And password=?";
                //2.2 Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //2.3 Execute Query
                rs = stm.executeQuery();
                //3. Model gets data from DB and processes result
                if (rs.next()) {
                    result = true;
                }//traverse each row in table
            }//connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
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
    
    public void searchLastName(String searchValue) 
        throws SQLException, ClassNotFoundException {
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Model connects DB
            con = DbUtils.getConnection();
            if (con != null) {
                //2. Model manipulates DB
                //2.1 Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //2.2 Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //2.3 Execute Query
                rs = stm.executeQuery();
                //3. Model gets data from DB and processes result
                while (rs.next()) {
                    //3.1 Model gets data from Result Set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //3.2 Model sets data from DTO Object
                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, lastname, role);
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//account is unvailiable
                    this.accounts.add(dto);
                }//traverse each row in table
            }//connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
