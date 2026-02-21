/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model.prjdtb;

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
public class RegistrationDao implements Serializable{
    public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
            boolean result = false;
            Connection conn = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            try {
                conn = DbUtils.getConnection();
                if (conn != null ){
                    String sql="SELECT username "
                            +"FROM Registration "
                            +"WHERE username=? "
                            +"AND password=? ";
                    stm= conn.prepareStatement(sql);
                    stm.setString(1, username);
                    stm.setString(2, password);
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        result=true;
                    }
                }
            
            } catch (ClassNotFoundException | SQLException e) {
            } finally {
                if (rs != null){
                    rs.close();
                }

                if (stm != null){
                    stm.close();
                }
                if (conn != null){
                    conn.close();
                }
            }
            return result;
        
    }
    private List<RegistrationDto> accounts;

    public List<RegistrationDto> getAccounts() {
        
        return accounts;
    }
    public void searchlastname(String searchvalue) throws SQLException{
     
            Connection conn = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            try {
                conn = DbUtils.getConnection();
                if (conn != null ){
                    String sql="SELECT username, password, lastname, isAdmin "
                            + "FROM Registration "
                            + "WHERE lastname LIKE ?";
                    stm= conn.prepareStatement(sql);
                    stm.setString(1,"%"+searchvalue+"%");
                    
                    rs = stm.executeQuery();
                    while(rs.next()){
                        String username= rs.getString("username");
                        String password= rs.getString("password");
                        String lastname= rs.getString("lastname");
                        boolean role= rs.getBoolean("isAdmin");
                        RegistrationDto dto = new RegistrationDto(username, password, lastname, role);
                        if (this.accounts == null){
                            this.accounts = new ArrayList<>();
                        }
                        this.accounts.add(dto);
                    }
             
                }
            
            } catch (ClassNotFoundException | SQLException e) {
            } finally {
                if (rs != null){
                    rs.close();
                }

                if (stm != null){
                    stm.close();
                }
                if (conn != null){
                    conn.close();
                }
            }
            
    }
    
}
