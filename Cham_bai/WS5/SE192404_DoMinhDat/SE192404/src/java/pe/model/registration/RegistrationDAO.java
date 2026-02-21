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
 * @author Be Keo
 */
public class RegistrationDAO implements Serializable {

    //usernam & pass (parameter) from Login
    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        // bien co --> dam bao output chi co 1 dau ra

        //Open: con--> stm -->
        boolean result = false; //--> da co phuong thuc connect DB (pe.utils)
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet re = null;

        try {
            //1.Model connects DB --> just done step 14
            con = DbUtils.getConnection(); //--> chua dam bao la connect available
            if (con != null) {
                //2.Create SQL String (cau lenh SQL) --> done 15
                String sql = "Select username "
                        + "From Registration "
                        + "Where username=? "
                        + "And password=?"; //--> laf statement, 

                //3. Create Statement Object (buoc nay la: dung de nap cau lenh SQL vao bo nho)
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Statement Object
                re = stm.executeQuery(); //--> no need to check null, it 100% has data

                //5. Process result --> done 16, 17
                if (re.next()) {
                    result = true;
                }
            } //--> Ket noi available

        } finally {
            //Close (opposite to Open) re --> stm --> con
            if (re != null) {
                re.close();
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

    //lay get cua bien 'accounts'
    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue)
            throws SQLException, ClassNotFoundException {
        // bien co --> dam bao output chi co 1 dau ra

        //Open: con--> stm -->
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet re = null;

        try {
            //1.Model connects DB 
            con = DbUtils.getConnection();
            if (con != null) {
                //2.Create SQL String (cau lenh SQL) 
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname LIKE ?";

                //3. Create Statement Object 
                stm = con.prepareStatement(sql);

                //4. Execute Statement Object
                stm.setString(1, "%" + searchValue + "%");

                re = stm.executeQuery();
                //5. Process result 

                while (re.next()) {
                    //5-1. Load data from dataset (step 16) //copy from database
                    String username = re.getString("username");
                    String password = re.getString("password");
                    String fullName = re.getString("lastname"); //take from db
                    boolean role = re.getBoolean("isAdmin"); //take from db

                    //5-2. 
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);

                }//Conection available
            }
        } finally {
            //Close (opposite to Open) re --> stm --> con
            if (re != null) {
                re.close();
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
