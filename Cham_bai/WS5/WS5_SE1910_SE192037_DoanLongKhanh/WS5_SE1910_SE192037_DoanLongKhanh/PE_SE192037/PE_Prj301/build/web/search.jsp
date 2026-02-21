<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%--  <%@page import="pe.model.registration.RegistrationDTO"%>
<%@page import="java.util.List"%> // jsp directive --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search Page</h1>
        <form action="MainController">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /><br> 
            <input type="submit" value="Search" name = "action"/>
            </form><br>
      
            <c:set var="searchValue" value="${param.txtSearchValue}" />
            <c:if test="${not empty searchValue }">
                <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
                <c:if test="${not empty result}"> <!<!-- found -->
                    <table border="1">
                        <thead>
                            <tr>
                                
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full Name</th>
                                <th>Role</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${result}" var="dto" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    .</td>
                                    <td>
                                        ${dto.username}
                                    </td>
                                    <td>
                                        ${dto.password}
                                    </td>
                                    <td>
                                        ${dto.fullname}
                                    </td>
                                    <td>
                                        ${dto.role}
                                    </td>
                                </tr>
                            </c:forEach>    
                        </tbody>
                    </table>

                </c:if>
                <c:if test="${empty result}"><!<!-- not found -->
                    <h2>
                        <font color="red">
                        No record is matched!!!
                        </font> 
                    </h2>
                </c:if>
            </c:if>
      <%--  <form action="MainController">
            Search Value <input type="text" name="txtSearchValue" value="<%=request.getParameter("txtSearchValue")%>" /> <br> 
            <input type="submit" value="Search" name = "action"/> 
        </form><br>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                //ket qua search dang nam o attribute cua scope
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) { // found
        %>
        <table border =" 1">
            <thead>
                <tr>
                    <th>No. </th>
                    <th>Username </th>
                    <th>Password </th>
                    <th>Role </th>
                </tr>
            </thead>
            <body>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                %>
            <tr>
                <td>
                    <%= ++count%>
                </td>
                <td>
                    <%=dto.getUsername()%>
                </td>
                <td>
                    <%= dto.getPassword()%>
                </td>
                <td>
                <%=dto.getFullname()%>
                
                </td>
                <td>
                    <%= dto.getFullname()%> 
                </td>
            </tr>
            <% }// end tranverse dto
            %>
    </body>

</table> 
<%
} else { //not found
%>
<font color ="red ">
No record is matched
<%
        }
    }
%> --%>
    </body>
</html>
