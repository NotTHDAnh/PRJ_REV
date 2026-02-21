/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.model.registration.RegistrationDAO;
import pe.model.registration.RegistrationDTO;

/**
 *
 * @author Duyphan
 */
// WebServlet thay  thế cho 2 thẻ servlet và servlet - mapping . name : thay thế cho servlet - name . urlPatterns thay thế cho url pattern trong servlet-mapping
// ít thay đổi thì dùng. hay thay đổi  thì dùng xml 
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {
    private final String SEARCH_PAGE = "search.html";
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
        PrintWriter out = response.getWriter();
        String url = SEARCH_PAGE;
                // 1.get all user's information
                String searchValue = request.getParameter("txtSearchValue");
        try {
            if(searchValue.trim().length() > 0) {
          // 2. Controller call methods của Models
          //2.1 Controller sẽ khởi tạo (initializes) DAO Object ( new operator )
          RegistrationDAO dao = new RegistrationDAO();
          //2.2 Controller gọi các methods của DAO Object
          dao.searchLastname(searchValue);
          // 3. Controller xử lí (processes) kết quả
          List<RegistrationDTO> result = dao.getAccounts();
            if(result != null) { // found
                log("STT \t Username \t Passwordd \t Full name \t Role");
                int count = 0;
                for(RegistrationDTO dto : result) {
                log(""
                        + ++ count 
                        + " \t\t "
                                + dto.getUsername()
                                + " \t\t "
                                        + dto.getPassword()
                                        + " \t "
                                                + dto.getFullName()
                                                + " \t\t "
                                                        + dto.isRole());
                } // each DTO in result List
            } else { // not found
                log("No record is matched !!!");
            }
          } 
             } catch (SQLException ex) {
            log("SearchLastnameServlet _ SQL " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("SearchLastnameServlet _ Class Not Found " + ex.getMessage());
            } finally {
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
