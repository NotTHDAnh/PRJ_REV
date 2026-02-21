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


public class LoginServlet extends HttpServlet {
    private final String SEARCH_PAGE="search.html";
    private final String INVALID_PAGE="invalid.html";
    
    //Controller
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Response Object
         PrintWriter out = response.getWriter();
         String url=INVALID_PAGE; //dedault always false
         //I.Get all user's information
         String username=request.getParameter("txtUsername");
         String password=request.getParameter("txtPassword");
         
        try{

            //II. Controller call methos of Method
            //II.2: Controller initializes DAO Object (new)
            RegistrationDAO dao =new RegistrationDAO();
            //II.2: Controller calls methods of DAO
            boolean result=dao.checkLogin(username, password); //try catch
            //III. Controller process result
            if(result){
                url=SEARCH_PAGE; //done step 18/ Here stay at Controller
            } 
            
        }catch(SQLException e){
            log("LoginServlet _ SQL "+e.getMessage());
        } catch(ClassNotFoundException e){
            log("LoginServlet _ Class Not Found"+ e.getMessage());//ghi file nay vao trong server
        }
        finally{
            RequestDispatcher rd= request.getRequestDispatcher(url);
            rd.forward(request, response);
            //response.sendRedirect(url); --> Loi kho ra MainController ma ra search.html
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
