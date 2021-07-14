<%-- 
    Document   : Manager Quiz Page
    Created on : June 09, 2021, 01:10:22 PM
    Author     : PhatNT
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/manage.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${empty errorManage and not empty listResult and empty noPermiss}">

            <p class="light-blue sz-20">Number of question: <span class="blue">${numberOfQuestion}</span>  </p>


            <table class="mg-top-35">
                <tr>
                    <td class="blue ">Question</td>
                    <td class="blue  ">DateCreated</td>
                </tr>
                <c:forEach var="question" items="${listResult}">
                    <tr>
                        <td class="light-blue question-manage">${question.question}</td>
                        <td class="light-blue">${question.getDateFormat()}</td>
                    </tr>
                </c:forEach>

            </table>
            <div class="paging">
                <c:if test="${totalPage<1}">
                    <h3>Not Found !!</h3>
                </c:if>
                <c:if test="${totalPage>1}">
                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <a class="${i==currentPage?"active":""}" href="ManageQuiz?page=${i}">${i}</a>
                    </c:forEach>
                </c:if>
            </div>

        </c:if>
        <c:if test="${errorManage ne null}">
            <h1 class="red">Exception: ${errorManage} </h1>
        </c:if>
        <c:if test="${noPermiss}">
            <h3 class="red">You do not have access</h3>
        </c:if>
        <c:if test="${noResultMessage ne null}">
            <h3 class="red">${noResultMessage}</h3>
        </c:if>
    </body>
    <script src="js/manage.js">
    </script>
</html>
