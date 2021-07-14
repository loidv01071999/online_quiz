<%-- 
    Document   : Make Quiz Page
    Created on : June 09, 2021, 01:10:22 PM
    Author     : PhatNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make Quiz Page</title>
    </head>
    <body>
        <c:if test="${empty noPermiss}">
            <form action="MakeQuiz" method="post">
                <table class="make-quiz">

                    <tr>
                        <td ><label class="light-blue">Question: </label></td>
                        <td>
                            <textarea class="textarea-question" name="question">${question}</textarea>
                        </td>
                    </tr>

                    <c:forEach var="i" begin="0" end="3" step="1" >
                        <tr>
                            <td><label class="light-blue">Option ${i+1}: </label></td>
                            <td>
                                <textarea class="textarea-option"  name="answer">${answer[i]}</textarea>
                            </td>
                        </tr>
                    </c:forEach>

                    <tr>

                        <td><label class="light-blue">Answer(s): </label></td>
                        <td class="choose-answer">
                            <input type="checkbox" name="trueAnswer" value="0"  ${choice0!=null?'checked':''}/> <label class="light-blue">Option 1</label> 
                            <input type="checkbox" name="trueAnswer" value="1"  ${choice1!=null?'checked':''}/> <label class="light-blue">Option 2</label> 
                            <input type="checkbox" name="trueAnswer" value="2"  ${choice2!=null?'checked':''}/> <label class="light-blue">Option 3</label> 
                            <input type="checkbox" name="trueAnswer" value="3"  ${choice3!=null?'checked':''}/> <label class="light-blue">Option 4</label> 
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="Save"/> <p class="red">${notice}</p>
                        </td>
                    </tr>

                </table>
            </form>
            <p id="test" ></p>
        </c:if>
        <c:if test="${noPermiss}">
            <h3 class="red">You do not have access</h3>
        </c:if>
    </body>

</html>
