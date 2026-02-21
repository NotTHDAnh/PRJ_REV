/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

/**
 *
 * @author DuckTAD
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password) 
        throws SQLException, ClassNotFoundException{
        boolean result = false;
        Connection con = null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
             //1. model connect database
        con= DbUtils.getConnection();
        if (con!=null){
            //2. taoj cau lenh sql String
            //statement
//            String sql ="Select username "
//                    + "From Registration "
//                    + "Where username=? "
//                    + "AND password=?";// chon tat ca username trong bang Registration vs dieu kien usernam = a va password = b
            String sql ="Select username "
                    + "From Registration "
                    + "Where username=? "
                    + "AND password=?";// chon tat ca username trong bang Registration vs dieu kien usernam = a va password = b
            //3. tao statament object, tinhx
            stm = con.prepareStatement(sql);// truyen 2 tham so ?
            stm.setString(1, username);
            stm.setString(2, password);
            //4. exe statament object CUD--> so int the hien so dong affected row|R--> result set: 1 object tro den 1 list, moi phan tu trong list se mapping vs 1 dong trong server, phan tu dau tien chua BeginOfFile, vaf cuoi cung chua EndOfFile, de dich chuyen con tro su dung phuong thuc next() tra ve T or F co ddacwj tinh la forward only
           rs=stm.executeQuery();
            //5. process result, phai bt cau lenh tra 1 row hay nhiu row, 1 thif dung if, nhieu thi dung while
            if(rs.next()){
                result=true;//DTO
            }
        }// connection is available
        } finally {
            if( rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    
        return result;
    }
    public boolean checkLogin(String username, String password, boolean isAdmin) 
        throws SQLException, ClassNotFoundException{
        boolean result = false;
        Connection con = null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
             //1. model connect database
        con= DbUtils.getConnection();
        if (con!=null){
            //2. taoj cau lenh sql String
            //statement
//            String sql ="Select username "
//                    + "From Registration "
//                    + "Where username=? "
//                    + "AND password=?";// chon tat ca username trong bang Registration vs dieu kien usernam = a va password = b
            String sql ="Select username "
                    + "From Registration "
                    + "Where username=? "
                    + "AND password=? "
                    + "AND isAdmin=1";// chon tat ca username trong bang Registration vs dieu kien usernam = a va password = b va la admin
            //3. tao statament object, tinhx
            stm = con.prepareStatement(sql);// truyen 2 tham so ?
            stm.setString(1, username);
            stm.setString(2, password);
            //4. exe statament object CUD--> so int the hien so dong affected row|R--> result set: 1 object tro den 1 list, moi phan tu trong list se mapping vs 1 dong trong server, phan tu dau tien chua BeginOfFile, vaf cuoi cung chua EndOfFile, de dich chuyen con tro su dung phuong thuc next() tra ve T or F co ddacwj tinh la forward only
           rs=stm.executeQuery();
            //5. process result, phai bt cau lenh tra 1 row hay nhiu row, 1 thif dung if, nhieu thi dung while
            if(rs.next()){
                result=true;//DTO
            }
        }// connection is available
        } finally {
            if( rs != null){
                rs.close();
            }
            if (stm != null){
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
    public void searchLastname(String searchValue)throws SQLException, ClassNotFoundException{
        
        Connection con = null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
             //1. model connect database
        con= DbUtils.getConnection();
        if (con!=null){
            //2. taoj cau lenh sql String
            //statement
           String sql ="Select username, password, lastname, isAdmin "
                   + "From Registration "
                   + "Where lastname Like ?";// chon tat ca username trong bang Registration vs dieu kien usernam = a va password = b
            //3. tao statament object, tinhx
            stm = con.prepareStatement(sql);// truyen 2 tham so ?
           stm.setString(1, "%"+ searchValue+ "%");
            //4. exe statament object CUD--> so int the hien so dong affected row|R--> result set: 1 object tro den 1 list, moi phan tu trong list se mapping vs 1 dong trong server, phan tu dau tien chua BeginOfFile, vaf cuoi cung chua EndOfFile, de dich chuyen con tro su dung phuong thuc next() tra ve T or F co ddacwj tinh la forward only
           rs=stm.executeQuery();
            //5. process result, phai bt cau lenh tra 1 row hay nhiu row, 1 thif dung if, nhieu thi dung while
           while(rs.next()){
               //5.1 model load data from resultSet
               String username = rs.getString("username");
               String password = rs.getString("password");
               String fullName = rs.getString("lastname");
               boolean role = rs.getBoolean("isAdmin");
               //5.2 model set data to DTO object
                       RegistrationDTO dto =new RegistrationDTO(username, password, fullName, role);
                       if(this.accounts==null){
                           this.accounts=new ArrayList<>();
                       }// account is unavailable
                       this.accounts.add(dto);
           }//end traverse is available
        }// connection is available
        } finally {
            if( rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    }
    
}
