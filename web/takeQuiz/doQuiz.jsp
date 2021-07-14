<%-- 
    Document   : Do Quiz Page
    Created on : June 09, 2021, 01:10:22 PM
    Author     : PhatNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/takeQuiz.js"></script>
    </head>
    <body>
        <div class="light-blue sz-20">Welcome <span class="blue">${sessionScope.user.username}</span>  </div>
        <input type="hidden" id="real-time-exam" value="${sessionScope.realSubmitTime}"/>
        <div class="light-blue sz-20 time-couter">Time remaining 
            <span class="red" id="showTime" ></span>  
        </div>

        <c:if test="${not empty quesList}">
            <form class="mg-top-35" action="TakeQuiz" method="post" id="doquiz">

                <c:forEach var="quest" items="${sessionScope.quesList}">
                    <div class="question" hidden="true"> 
                    
                        <input type="hidden" name="quenstionId" value="${quest.id }" />

                        <div class="light-blue" >${quest.question }</div>
                        <c:forEach var="answer" items="${quest.answerList }">
                            <input type="hidden" id="answerId" value="${answer.id }" />
                            <input id="answer-${answer.id }" type="checkbox" 
                                   name="answer-for-${quest.id }" 
                                   value="${answer.id }" onclick="clickAnswer()"
                                   />
                            <label for="answer-${answer.id }" class="light-blue">${answer.answer }</label>
                            <br>
                        </c:forEach>
                    </div>
                </c:forEach>

                <input class="btn-submit" type="button" value="Next" onclick="next();" />

            </form>
        </c:if>

    </body>
    <script src="js/takeQuiz.js" type="text/javascript">
    </script>
    <script src="js/saveAnswer.js" type="text/javascript">
    </script>
</html>

