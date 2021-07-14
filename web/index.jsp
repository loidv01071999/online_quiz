<%-- 
    Document   : Index Page
    Created on : June 09, 2021, 01:10:22 PM
    Author     : PhatNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <link href="css/takeQuiz.css" rel="stylesheet" type="text/css"/>
        <link href="css/manage.css" rel="stylesheet" type="text/css"/>
        <title>Online Quiz</title>
    </head>
    <body>

        <div class="top-box"> </div>
        <div class="container">
            <div class="menu">
                <ul class="flex-box bold">
                    <c:if test="${not empty realSubmitTime}">
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Take Quiz</a></li>
                        <li><a href="#">Make Quiz</a></li>
                        <li><a href="#">Manage Quiz</a></li>
                        <li><a href="#">Log out</a></li>
                        </c:if>
                        <c:if test="${empty realSubmitTime}">
                        <li><a href="Login">Home</a></li>
                        <li><a href="TakeQuiz">Take Quiz</a></li>
                        <li><a href="MakeQuiz">Make Quiz</a></li>
                        <li><a href="ManageQuiz">Manage Quiz</a></li>
                            <c:if test="${not empty sessionScope.user}">
                            <li><a href="Logout">Log out</a></li>
                            </c:if>
                        </c:if>

                </ul>
            </div>
            <div class="content">
                <jsp:include page="${component}" />
            </div>
        </div>



    </body>
</html>
