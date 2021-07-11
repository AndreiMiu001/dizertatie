<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>    
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            .navbar>.container, .navbar>.container-fluid, .navbar>.container-lg, .navbar>.container-md, .navbar>.container-sm, .navbar>.container-xl, .navbar>.container-xxl {
                justify-content: start;
            }
            .navbar-brand {
                padding-left: 1.5rem;
                font-size: 1.6rem;
                padding-top: .5rem;
                padding-bottom: .5rem;
            }
            navbar-nav, .nav-link active, .nav-item {
                text-indent: 1.8rem;
            }
            .nav-link:hover {
                color:#abc;
            }
            .nav-link {
                color:#fff
            }
            .nav-link-align {
                display: inline-flex;
                margin-left: auto;
                margin-right: 0;
            }
        </style>
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
