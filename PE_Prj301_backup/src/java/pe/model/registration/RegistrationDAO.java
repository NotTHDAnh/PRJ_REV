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
import pe.utils.DbUtils;

/**
 *
 * @author pct20
 */
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password)
            throws SQLException,ClassNotFoundException{
// buoc 14,15,16 tren so do
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            //1. Model connect Database
            con = DbUtils.getConnection();
            if(con != null){
                //2. Create SQL String
                String sql = "Select username "
                        + "From Registration "
                        + "Where username=? " // ? so 1
                        + "And password=?"; // ? so 2
                //vi tri dau ? tinh tu trai sang phai, tren xuong duoi
                stm = con.prepareStatement(sql);
                stm.setString(1, "username");
                stm.setString(2, "password");
                //4.Excecute Statement Object
                rs = stm.executeQuery();
                //5.Process result
                if(rs.next()){
                    System.out.println("success");
                    result = true;
                } // username and password are existed
            }// connection is available
        }
        finally{
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return result;
    }
}
