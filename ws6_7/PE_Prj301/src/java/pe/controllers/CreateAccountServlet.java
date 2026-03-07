/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.model.registration.RegistrationCreateError;
import pe.model.registration.RegistrationDAO;
import pe.model.registration.RegistrationDTO;

/**
 *
 * @author PTAK
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private static final String ERROR_PAGE = "createNewAccount.jsp";
    private static final String LOGIN_PAGE = "login.html";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;

        // 1.controller get all user's information
        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullName");
        
        RegistrationCreateError errors = new RegistrationCreateError();
        // Create a place to store srrors
        boolean foundErr = false;
        // Create a variable to determine errors
        try  {
            //2. Controller processs all user's errors
            if(username.trim().length() < 6 || username.trim().length() > 20){
                foundErr = true;
                errors.setUsernameLengthErr("Username is required typing from 6 to 20 character");
            }
            
            if(password.trim().length() < 6 || password.trim().length() > 30){
                foundErr = true;
                errors.setPasswordLengthErr("Password is required typing from 6 to 30 character");
            }
            else if(!confirm.trim().equals(password.trim())){
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }
            
            if(fullname.trim().length() < 2 || fullname.trim().length() > 50){
                foundErr = true;
                errors.setFullNameLengthErr("fullname is required typing from 2 to 50 character");
            }
            
            if(foundErr){
                // show -> cache/save errors into attribute of request --> transfer to error page
                request.setAttribute("CREATE_ERRORS", errors);
            }
            else{// no error
            //3. Controller call methods of Model
            //3.1 Controlller new Dao Object
                RegistrationDAO dao = new RegistrationDAO();
            //3.2 Controller call methods from DAO Object
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                boolean results = dao.createAccount(dto);
            //4 Controller processes results
                if(results)
                    url = LOGIN_PAGE; // insert is successfully
            }
            
        }
        catch(SQLException ex){
            String errMsg = ex.getMessage();
            log("CreateAccountServlet _ SQL:" + errMsg);
            if(errMsg.contains("duplicate")){
                errors.setUsernameIsExisted(username + " is EXISTED");
                // update scope
                request.setAttribute("CREATE_ERRORS", errors);
                log("CreateAccountServlet");
            }
        }
        catch(ClassNotFoundException ex){
            log("CreateAccountServlet _ Class Not Found:" + ex.getMessage());
        }
        finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
