<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <link rel="stylesheet" href="Style/CSS/IndexStyle.css">
        <link rel="stylesheet" href="Style/CSS/bootstrap502.css">
        <script src="Style/JS/bootstrap502.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Platforma eVot</title>
    </head>
    <body>
        <div class="myclass">
            <div class="indent">
                <h1 style="font-weight: bold">Platforma eVot</h1>
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
