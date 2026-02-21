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
 * @author ASUS
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        boolean result = false; // Viet code chi? duoc 1 input va` 1 output
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            // 1. Model connects DB (14)
            con = DbUtils.getConnection();
            if (con != null) {       // SV thuong thieu khi viet
                // 2. Create SQL String (15)
                String sql = "SELECT username "
                    + "FROM Registration " 
                    + "WHERE username = ? "
                    + "AND password = ? "; 
                
                // 3. Create Statement Object   (15)
                stm = con.prepareCall(sql);
                stm.setString(1, username);  //trong sql vi tri' dau tien la` 1
                stm.setString(2, password);
                
                // 4. Execute Query (15)
                rs = stm.executeQuery();
                
                // 5. Process result (16, 17)
                if (rs.next()){
                    result = true;
                }
            } //Connection is available
        } finally {
            if (rs != null){
                rs.close();
            }
            
            if (stm != null){
                stm.close();
            }
            
            if (con != null) {
                con.close();   //cau lenh nay can throws SQLException.
            }
        }

        return result;
    }

    private List<RegistrationDTO> accounts;
    
    public List<RegistrationDTO> getAccounts(){
        return accounts;
    }
    
    public void searchLastname(String searchValue)
            throws SQLException, ClassNotFoundException {
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            // 1. Model connects DB (14)
            con = DbUtils.getConnection();
            if (con != null) {       // SV thuong thieu khi viet
                // 2. Create SQL String (15)
                String sql = "Select username, password, lastname, isAdmin " 
                        + "From Registration " 
                        + "where lastname like ?";
                
                // 3. Create Statement Object   (15)
                stm = con.prepareCall(sql);
                stm.setString(1, "%" + searchValue + "%");
                
                // 4. Execute Query (15)
                rs = stm.executeQuery();
                
                // 5. Process result (16, 17)
                while (rs.next()){
                    // 5.1 Model gets data from Result Set (16)
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    
                    // 5.2 Model sets data to DTO Object (16)
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    
                    if (this.accounts == null){
                        this.accounts = new ArrayList<>();
                    } // account is unavailable
                    this.accounts.add(dto);
                    
                } // end traverse each rows of 
            } //Connection is available
        } finally {
            if (rs != null){
                rs.close();
            }
            
            if (stm != null){
                stm.close();
            }
            
            if (con != null) {
                con.close();   //cau lenh nay can throws SQLException.
            }
        }
    }
}               