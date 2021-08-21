<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="menuBar.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="Style/CSS/mainPage.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eVot Pagina Principala</title>
    </head>
    <body>
        <div id="navigationMenu"></div>
        <div class="center">
            <div class="content-all">
                <form method="get" action="ViewElectionsServlet">
                    <div class="content">
                        <button  class="btn btn-secondary">View Results</button>
                    </div>
                </form>
                <form method="get" action="InsertDataServlet">
                    <div class="content">
                        <button  class="btn btn-secondary">Create election</button>
                    </div>
                </form>
                <form method="get" action="DeleteElectionViewServlet">
                    <div class="content">
                        <button  class="btn btn-secondary">Delete election</button>
                    </div>
                </form>
                <form method="get" action="UpdateElectionViewListServlet">
                    <div class="content">
                        <button class="btn btn-secondary">Update election</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
