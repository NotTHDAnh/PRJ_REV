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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.model.registration.RegistrationDAO;

/**
 *
 * @author pct20
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String SEARCH_PAGE = "search.html";
    private final String INVALID_PAGE = "invalid.html";

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = INVALID_PAGE;
        //1. get all the user's information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try  {
            //2. controller calls Model
            //2.1 controller new DAO object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 controller call methods from DAO Object
            boolean result = dao.checkLogin(username, password);
            //3 controller processes results
            if(result){
                url = SEARCH_PAGE;
            }// username and password matched
        }catch(SQLException ex){
            log("LoginServlet _ SQL " + ex.getMessage());
        } catch(ClassNotFoundException ex){
            log("LoginServlet _ Class Not Found " + ex.getMessage());
        }finally{
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response); // nhin thay thang  cuoi cung nhun van nho thang dau tien
            
            response.sendRedirect(url); // nhin thay thang cuoi cung
            out.close();
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
