<%-- 
    Document   : Login Page
    Created on : June 09, 2021, 01:10:22 PM
    Author     : PhatNT
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="../css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        

         
        <form action="Login" method="post">
                
                <table border="0">
                    <tr>
                        <td><p class="dark-blue lblLoginForm bold">Login Form</p></td>
                        <td></td>
                    <tr>
                        <td class="light-blue sz-20">User Name:</td>
                        <td><input type="text" name="txtUserame" class="txtLogin " maxlength="20" value="${userName}" required /></td>
                    </tr>
                    <tr>
                        <td class="light-blue sz-20"> Password:</td>
                        <td><input type="password" name="txtPassword" maxlength="20" class="txtLogin "  required /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" class="submit-login" value="Sign in" /> 
                            <a class="light-blue none-decoration sz-16" href="Register">Register</a></td>
                    </tr>
                    
                </table>
                    <p class="red">${status}</p>
            </form>  
 
         
    </body>
</html>
