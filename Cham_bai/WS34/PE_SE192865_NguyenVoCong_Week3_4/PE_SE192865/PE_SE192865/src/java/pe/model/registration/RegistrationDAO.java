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
 * @author LENOVO
 */
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password) 
        throws SQLException, ClassNotFoundException
    {
    
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
        //1 Model connect to database
        con = DbUtils.getConnection();
        if(con != null){
            
        
        //2 Mode manipulate
        //2.1 create sql String
        String sql = "Select username "
                + "From Registration "
                + "Where username=? "
                + "And password=?";
        //2.2 create  statement object
        stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
        //2.3 execute Query
        rs = stm.executeQuery();
        
        //3 Model processes result
        if(rs.next()){ //buoc 16
            result = true; //buoc 17
        }
        }//connection is available
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if( con != null){
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
            throws SQLException, ClassNotFoundException
    {
    
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
        //1 Model connect to database
        con = DbUtils.getConnection();
        if(con != null){
            
        
        //2 Mode manipulate
        //2.1 create sql String
        String sql = "Select username, password, lastname, isAdmin "
                + "From Registration "
                + "Where lastname Like ?";
        //2.2 create  statement object
        stm = con.prepareStatement(sql);
        stm.setString(1, "%" + searchValue + "%");
        //2.3 execute Query
        rs = stm.executeQuery();
        
        //3 Model processes result
       while (rs.next()) {
           //3.1 Model get data from ResultSet
           String username = rs.getString("username");
           String password = rs.getString("password");
           String fullName = rs.getString("lastname");
           boolean role = rs.getBoolean("isAdmin");
           //3.2 Model sets data to DTO Object
           RegistrationDTO dto = new RegistrationDTO(
                        username, password, fullName, role);
           if (this.accounts == null) {
               this.accounts = new ArrayList<>();
           }//account is unvailable
           this.accounts.add(dto);
       }//traverse each row in table
        }//connection is available
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if( con != null){
                con.close();
            }
        }
        
    }
   
}