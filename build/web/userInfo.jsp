<%-- 
    Document   : User information Page
    Created on : June 09, 2021, 01:10:22 PM
    Author     : PhatNT
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 class="light-blue">Welcome <span class="red">${sessionScope.user.username}</span></h1>
    </body>
</html>
