<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="Style/CSS/bootstrap340.css">
        <link rel="stylesheet" href="CSS/DefaultStyle.css">
        <script src="Style/JS/jquery340.js"></script>
        <script src="Style/JS/bootstrap340.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eVot Meniul Principal</title>
    </head>
    <div id="navigationMenu"></div>
    <div class="center">
        <div class="indent">
            <h2> Menu </h2>
        </div>
        <form method="post" action="ViewElectionsServlet">

            <div class="container-fluid  margin">
                <button  class="themeBtn2">View Results</button>
            </div>
        </form>
        <form method="post" action="DeleteElectionViewServlet">
            <div class="container-fluid  margin">
                <button  class="themeBtn2">Delete election</button>
            </div>
        </form>
        <form method="get" action="InsertDataServlet">
            <div class="container-fluid  margin">
                <button    class="themeBtn2">Create election</button>
            </div>
        </form>
        <form method="get" action="UpdateRedirectServlet">
            <div class="container-fluid  margin">
                <button    class="themeBtn2">Update election</button>
            </div>
        </form>
    </div>
</body>
<script>
    $(document).ready(function () {
        $('#navigationMenu').load('menuBar.jsp');
    });
</script>
</html>
