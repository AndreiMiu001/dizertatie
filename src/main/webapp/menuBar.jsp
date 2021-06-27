<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>    
        <link rel="stylesheet" href="Style/CSS/bootstrap340.css">
        <script src="Style/JS/jquery340.css"></script>
        <script src="Style/JS/bootstrap340.css"></script>
        <title>Bar de meniu</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#" id="homePage">Home</a>
                </div>
                <ul class="nav navbar-nav">
                    <li ><a href="#" id="viewEl" >View Elections and Results</a></li>
                    <li ><a href="#" id="createEl">Create Election</a></li>
                    <li ><a href="#" id="delEl">Delete Elections</a></li>
                    <li ><a href="#" id="upEl">Update Elections</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" id="loggout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>    </body>

    <script>
        $("#viewEl").click(function (event) {
            event.preventDefault(); // don't "execute" the link

            var $form = $("<form action='ViewElectionsServlet' method='post'>" +
                    "<input type='hidden' name='foo' value='bar' />" +
                    "<input type='submit' />" +
                    "</form>").appendTo($('body'));
            $form.submit();
            $form.remove();
        });
    </script>
    <script>
        $("#delEl").click(function (event) {
            event.preventDefault(); // don't "execute" the link

            var $form = $("<form action='DeleteElectionServlet1' method='post'>" +
                    "<input type='hidden' name='foo' value='bar' />" +
                    "<input type='submit' />" +
                    "</form>").appendTo($('body'));
            $form.submit();
            $form.remove();
        });
    </script>
    <script>
        $("#homePage").click(function (event) {
            event.preventDefault(); // don't "execute" the link

            var $form = $("<form action='HomeRedirectServlet' method='get'>" +
                    "<input type='hidden' name='foo' value='bar' />" +
                    "<input type='submit' />" +
                    "</form>").appendTo($('body'));
            $form.submit();
            $form.remove();
        });
    </script>
    <script>
        $("#createEl").click(function (event) {
            event.preventDefault(); // don't "execute" the link

            var $form = $("<form action='InsertDataServlet' method='get'>" +
                    "<input type='hidden' name='foo' value='bar' />" +
                    "<input type='submit' />" +
                    "</form>").appendTo($('body'));
            $form.submit();
            $form.remove();
        });
    </script>
    <script>
        $("#upEl").click(function (event) {
            event.preventDefault(); // don't "execute" the link

            var $form = $("<form action='UpdateRedirectServlet' method='get'>" +
                    "<input type='hidden' name='foo' value='bar' />" +
                    "<input type='submit' />" +
                    "</form>").appendTo($('body'));
            $form.submit();
            $form.remove();
        });
    </script>
    <script>
        $("#loggout").click(function (event) {
            event.preventDefault(); // don't "execute" the link

            var $form = $("<form action='LogoutServlet' method='post'>" +
                    "<input type='hidden' name='foo' value='bar' />" +
                    "<input type='submit' />" +
                    "</form>").appendTo($('body'));
            $form.submit();
            $form.remove();
        });
    </script>
</html>
