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
 * @author Gia Thinh
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        //viet 1 phuong thuc la chi co mot input dau vao va 1 output dau ra
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
        //1.MD thuc hien ket noi DB(Buoc 14 trong so do)            
            con = DbUtils.getConnection();
            if(con != null){
        //2. Create SQL string (b15)
        //1. Moi menh de chi duoc viet tren 1 dong
        //2. Truoc khi xuong dong phai chen 1 ki tu khoang trang
        //3. Han che su dung ki tu "*"
        String sql = "Select username "
                + "From Registration "
                + "Where username=? "
                + "And password=?";
        //3. Tao ra Statement object(nap cau lenh sql vao trong bo nho)(b15)
        stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
        //4. Excute Query(b15)
        rs = stm.executeQuery();
        //5. Xu li ket qua(b16,b17)
        if(rs.next()){
            result = true;
            }//username and password are matched
         } // connection is available(la co san)
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
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
        //viet 1 phuong thuc la chi co mot input dau vao va 1 output dau ra
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
        //1.MD thuc hien ket noi DB(Buoc 14 trong so do)            
            con = DbUtils.getConnection();
            if(con != null){
        //2. Create SQL string (b15)
        //1. Moi menh de chi duoc viet tren 1 dong
        //2. Truoc khi xuong dong phai chen 1 ki tu khoang trang
        //3. Han che su dung ki tu "*"
        String sql = "Select username, password, lastname, isAdmin "
                + "From Registration "
                + "Where lastname Like ?";
        //3. Tao ra Statement object(nap cau lenh sql vao trong bo nho)(b15)
        stm = con.prepareStatement(sql);
        stm.setString(1, "%" + searchValue + "%");
        //4. Excute Query(b15)
        rs = stm.executeQuery();
        //5. Xu li ket qua(b16,b17)
        while(rs.next()){
            //5.1 Model gets data from result set
            String username = rs.getString("username");
            String password = rs.getString("password");
            String fullName = rs.getString("lastname");
            boolean role = rs.getBoolean("isAdmin");
            //5.2 Model sets data to DTO object
            RegistrationDTO dto = new RegistrationDTO(
                    username, password, fullName, role);
            if(this.accounts==null){
                this.accounts = new ArrayList<>();
            }
            this.accounts.add(dto);
        }//end traverse each rows of table
         } // connection is available(la co san)
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    }
}
