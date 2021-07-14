<%-- 
    Document   : Register Page
    Created on : June 09, 2021, 01:10:22 PM
    Author     : PhatNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <title>JSP Page</title>
    </head>
    <body>
        <h3 class="dark-blue">Registration Form</h3>

        <c:if test="${ empty errorRegister}" >
            <form action="Register" method="post">
                <table border="0">

                    <tr>
                        <td class="light-blue">User Name:</td>
                        <td><input type="text" name="txtName" value="${userName}" maxlength="20" required /></td>
                    </tr>
                    <tr>
                        <td class="light-blue"> Password:</td>
                        <td><input type="password" name="txtPassword"  maxlength="20" required /></td>
                    </tr>
                    <tr>
                    <input type="hidden" id="type" value="${type}" />
                    <td class="light-blue">User type:</td>
                    <td>
                        <select class="select-type" name="user-type" >
                            <option value="Teacher" ${isTeacher!=null?'selected"':''}>Teacher</option>
                            <option value="Student" ${isStudent!=null?'selected':''}>Student</option>
                        </select>
                    </td>
                    </tr>
                    <tr>
                        <td class="light-blue">Email:</td>
                        <td><input type="email" name="txtEmail" maxlength="30" value="${email}" required /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Register" /></td>
                    
                    </tr>
                </table>
                    <p class="red">${status}</p>
            </form>   
        </c:if>
    </body>

</html>
