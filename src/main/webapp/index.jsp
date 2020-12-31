<%-- 
    Document   : index
    Created on : Dec 31, 2020, 3:36:43 PM
    Author     : Andrei
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="CSS/IndexStyle.css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <div class="myclass">
            <div class="indent">
                <h1 style="font-weight: bold">eVot platform login</h1>
            </div>
            <div class="wrapper fadeInDown">
                <div id="formContent">
                    <form method="post" action="LoginServlet">
                        <input type="text" id="login" class="fadeIn second" name="username" placeholder="username">
                        <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
                        <input type="submit" class="fadeIn fourth" value="Log In">
                        <h1 style="color:red;font-size:90%">${response}</h1>
                    </form>

                </div>
            </div>
        </div>

    </body>
</html>

