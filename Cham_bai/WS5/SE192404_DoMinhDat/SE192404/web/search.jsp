<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>


<%-- <%@page import="pe.model.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <!--your code here-->
        <h1>Search Page</h1>
        <form action="MainController">
            Search Value <input type ="text" name = "txtSearchValue" value = "${param.txtSearchValue}"/> <br/>
            <input type="submit" value = "Search" name="action"/>
        </form>
            <jstl:set var="searchValue" value="${param.txtSearchValue}"/> 
            <jstl:if test="${not empty searchValue}"> 
                <jstl:set var="result" value="${requestScope.SEARCH_RESULT}"/>
                <jstl:if test="${not empty result}"><!-- found -->
                    <table border="1">
                        
                        <thead>
                            <tr>
                            <th>No. </th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                        </tr>
                        </thead>
                        <tbody>
                            <jstl:forEach items="${result}" var = "dto" varStatus = "counter">
                                <tr>
                                <td>
                                    ${counter.count}
                                    .</td>
                                <td>
                                    ${dto.username}
                                </td>
                                <td>
                                    ${dto.password}</td>
                                <td>
                                    ${dto.fullName}</td>
                                <td>
                                    ${dto.role}</td>
                                </tr>
                            </jstl:forEach>
                        </tbody>
                    </table>
                </jstl:if>
                <jstl:if test="${empty result}"><!-- not found -->
                    <h2>
                        <font color ="red">
                            No record is matched!!!
                        </font>
                    </h2>
</jstl:if>
            </jstl:if>
        <%--
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null){
                List<RegistrationDTO> result = (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                if (result != null){
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
                                int count  = 0;
                                for (RegistrationDTO dto : result){
                                    %>
                                        <tr>
                                            <td>
                                                <%= ++count %>
                                            .</td>
                                            <td>
                                                <%= dto.getUsername() %>
                                            </td>
                                            <td>
                                                <%= dto.getPassword() %>
                                            </td>
                                            <td>
                                                <%= dto.getFullName() %>
                                            </td>
                                            <td>
                                                <%= dto.isRole() %>
                                            </td>
                                        </tr>
                                    <%
                                }
                            %>
                        </tbody>
                    </table>

                    <%
                }
                else{
                    %>
                        <h2>
                            <font color = "red">
                                No record is matched!!!!
                            </font>
                        </h2>
                    <%
                }   
            }// no first time
        %>--%>
        
    </body>
</html>