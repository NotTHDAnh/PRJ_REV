<%-- 
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%--<%@page import="pe.model.registration.RegistrationDTO"%>
@page import="java.util.List"%>--%>
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
            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="action" />
        </form><br/>
        <%
            Cookie[] cookie = request.getCookies();
            if(cookie != null){
                Cookie newesstCookie = cookie[cookie.length - 1];
                String username = newesstCookie.getName();
                String password = newesstCookie.getValue();
                %>
                <font color ="red">
                    welcome <%= username %>
                </font>
                <%
            }
        %>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}"><%-- Found --%>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>UserName</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                           <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="Counter">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>
                                ${Counter.count}
                            </td>
                            <td>
                                ${dto.username}
                                <input type="hidden" name="txtUserName" value="${dto.username}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" value="${dto.password}" />
                            </td>
                            <td>
                                ${dto.fullName}
                            </td>
                            <td>
                                <input type="checkbox" name="chckAdmin" value="ON"
                                       <c:if test = "${dto.role}">
                                           checked="checked"
                                       </c:if>>
                            </td>
                            <td>
                                <c:url var="deleteLink" value="MainController">
                                    <c:param name="action" value="delete" />
                                    <c:param name="pk" value="${dto.username}" />
                                    <c:param name="lastSearchValue" value="${searchValue}" />
                                </c:url>
                                <a href="${deleteLink}">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="action" />
                                <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                            </td>
                        </tr>
                        </form>

                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <h2>
                    <font color ="red">
                    No record is matched!!!
                    </font>
                </h2>`
            </c:if>
        </c:if>
        <%-- 
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if(searchValue != null){
                List<RegistrationDTO> result = (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                if(result != null){//found
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>UserName</th>
                                <th>Password</th>
                                <th>Full Name</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;

                                for(RegistrationDTO dto : result){
                                %>
                            <tr>
                                <td>
                                    <%= ++count %>
                                </td>
                                <td>
                                    <%= dto.getUsername()%>
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
                                }// end traversal
                            %>
                        </tbody>
                    </table>

                    <%
                } else { // not found
                    %>
                    <h2>
                        <font color ="red">
                            No record is matched!!!
                        </font>
                    </h2>
                    <%
                }
            }// end if not first times
        %>--%>
    </body>
</html>
