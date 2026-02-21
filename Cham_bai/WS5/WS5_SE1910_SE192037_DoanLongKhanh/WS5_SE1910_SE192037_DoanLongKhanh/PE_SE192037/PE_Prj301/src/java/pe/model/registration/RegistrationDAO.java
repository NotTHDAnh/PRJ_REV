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
 * @author 77
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            if (con != null) {
                String sql = "Select username "
                        + "From Registration "
                        + "Where username=? "
                        + "And password=? ";
                //3.Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Execute Statement 
                rs = stm.executeQuery();
                //5.Process Result
                if(rs.next()){
                    result = true;
                }//username and password are matched
            }
            //connnection is availiable 
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
    
    public void searchLastname(String searchValue)
            throws SQLException, ClassNotFoundException {
        
       
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            if (con != null) {
                String sql = "Select username, password, lastname, isAdmin "
                             + "From Registration "
                             + "Where lastname Like ?";
                //3.Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4.Execute Statement 
                rs = stm.executeQuery();
                //5.Process Result
                while(rs.next()){
                    //5.1 Model loads data form ResultSet
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 Model sets data to DTO Object
                    RegistrationDTO dto = new RegistrationDTO(
                    username, password, fullname, role);
                    if(this.accounts == null){
                        this.accounts = new ArrayList<>();
                    }//acccount is unavailable
                    this.accounts.add(dto);
                    
                }//username and password are matched
            }
            //connnection is availiable 
        } finally {
            if (rs != null) {
                con.close();
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
