/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.model.prjdtb.RegistrationDao;
import pe.model.prjdtb.RegistrationDto;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchlastnameServlet", urlPatterns = {"/SearchlastnameServlet"})
public class SearchlastnameServlet extends HttpServlet {
        private static final String SEARCHPAGE="search.html";


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
        String url = SEARCHPAGE;
        PrintWriter out = response.getWriter(); 
        String text = request.getParameter("texSearch");
        try {
            if(!(text.trim().isEmpty())){
                RegistrationDao dao = new RegistrationDao();
                dao.searchlastname(text);
                List<RegistrationDto> result  = dao.getAccounts();
                if(result != null){
                    log("STT\t Username \t Password \t lastname \t isadmin");
                    int count=0;
                    for(RegistrationDto dto :result ){
                        log(++count
                                +"\t"
                                +dto.getUsername()
                                    +"\t"
                                    +dto.getPassword()
                                       +"\t" 
                                            +dto.getLastname()
                                            +"\t"
                                                +dto.isRole());

                    }
                }else{
                    log("ko tiem thay");
                }
            }
        
        }catch (SQLException ex) {
            log("SearchlastnameServlet SQL Exception", ex);
        
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