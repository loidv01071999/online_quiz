<%-- 
    Document   : Take Quiz Page
    Created on : June 09, 2021, 01:10:22 PM
    Author     : PhatNT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Quiz Page</title>
    </head>
    <body>
        <p class="light-blue sz-20">Welcome <span class="blue">${sessionScope.user.username}</span>  </p>

        <form action="TakeQuiz" method="get">

            <table border="0">

                <tr>
                    <td class="labelColor light-blue sz-20">Enter number of questions:</td>
                    <td></td>
                </tr>
                <tr>
                    <td> <input  class="txtNumberQuestion" type="text" name="numberQuestion" /> </td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input class="submit-request" type="submit" value="Start"/></td>
                </tr>

            </table>
            <p class="red">${errorTakeQuiz}</p>
        </form>   

    </body>
</html>
