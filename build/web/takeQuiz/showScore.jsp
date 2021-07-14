<%-- 
    Document   : Display score page
    Created on : June 09, 2021, 01:10:22 PM
    Author     : PhatNT
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="light-blue sz-20" >Your score          
            <c:if  test="${score ge 5 }">
                <span class="blue">${sessionScope.score} (${sessionScope.percent}%) - Passed</span>
            </c:if>
            <c:if test="${score lt 5 }">
                <span class="red">${sessionScope.score} (${sessionScope.percent}%) - Not Pass</span>
            </c:if>
        </div>
        <div class="light-blue sz-20 mg-top-35" >Take another test <button type="button" ><a class="start" href="TakeQuiz">Start</a></button></div>
    </body>

    <script src="js/showScore.js">

    </script>

</html>
