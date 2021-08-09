<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>    
        <link rel="stylesheet" href="Style/CSS/bootstrap502.css">
        <script src="Style/JS/bootstrap502.js"></script>
        <script src="Style/JS/jquery340.js"></script>
        <link rel="stylesheet" href="Style/CSS/menuBar.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bar de meniu</title>
    </head>
    <body> 
        <nav class="navbar navbar-dark bg-dark">
            <div class="container-fluid" >
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class="navbar-brand" href="/eVotWebAppMaven/HomeRedirectServlet">Menu</a>
                <div class="nav-link-align">
                    <a class="nav-link disabled" href="#" aria-disabled="true">${user.getUsername()}</a>
                    <a class="nav-link" href="/eVotWebAppMaven/LogoutServlet">Logout</a>
                </div>
                <div class="collapse navbar-collapse" id="navbarText">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active"  href="/eVotWebAppMaven/ViewElectionsServlet">View Elections and Results</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"  href="/eVotWebAppMaven/InsertDataServlet">Create Election</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"  href="/eVotWebAppMaven/DeleteElectionViewServlet">Delete Elections</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"  href="/eVotWebAppMaven/UpdateElectionServlet">Update Elections</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>
