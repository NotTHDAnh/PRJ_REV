<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page import="java.util.List"%>
<%@page import="pe.model.registration.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <!--your code here-->
        <form action="MainController">
            Search Value <input type="text" name="txtSearchValue"
                                value="<%= request.getParameter("txtSearchValue") %>" /> <br/>
            <input type="submit" value="Search" name="action" />
        </form><br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue!=null){
                List<RegistrationDTO> result = 
                            (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                if(result != null){ //found
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count = 0;
                            for(RegistrationDTO dto : result){
                                %>
                            <tr>
                            <td><%= ++count %>.</td>
                            <td><%= dto.getUsername() %></td>
                            <td><%= dto.getPassword() %></td>
                            <td><%= dto.getPassword() %></td>
                            <td><%= dto.isRole() %></td>
                            </tr>
                            <%
                            } //end traverse dto
                        %>
                    </tbody>
                </table>

                <%
                } else {//not found
                    %>
                        <h2>
                            <font color="red"> No record is matched!!! </font>
                        </h2>
                    <%
                }
            }//end if not first times
        %>
    </body>
</html>
