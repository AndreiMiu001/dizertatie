<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .myclass
        {
            margin: auto;
            width: 45%;
            padding: 10px;

        }
        .divPadding
        {
            padding: 6px;
        }
    </style>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id="navigationMenu"></div>

        <%
            ArrayList<String> electionNames = (ArrayList) request.getAttribute("electionNamesArray");
            ArrayList<Integer> electionIds = (ArrayList) request.getAttribute("electionIdsArray");
            int electionNo = 0;
            if (electionIds != null) {
                electionNo = electionIds.size();
            }
        %>
        <script>
            var len =<%= electionNo%>;
            var electionNames = <%= electionNames%>;
            var electionIds = <%= electionIds%>;
            var div1 = document.createElement("div");
            div1.setAttribute('class', "myclass");
            var divt = document.createElement("div");
            divt.setAttribute('class', "panel-heading");
            divt.setAttribute('style', "border-radius: 0px");
            var h1 = document.createElement("h4");
            var t1 = document.createTextNode("Choose which election to delete");
            h1.setAttribute('style', "font-size: 25px; font-weight: bold; text-align: center;");
            h1.setAttribute('class', "panel-title");
            var divp = document.createElement("div");
            divp.setAttribute('style', "border-width: 4px");
            divp.setAttribute('class', "panel panel-primary");
            h1.appendChild(t1);
            divt.appendChild(h1);
            divp.appendChild(divt);
            // TO BE ADDED
            var div2 = document.createElement("div");
            div2.setAttribute('class', "list-group");

            for (var i = 1; i <= len; i++) {
                var f = document.createElement("form");
                f.setAttribute('method', "post");
                f.setAttribute('action', "DeleteElectionServlet2");

                var in1 = document.createElement("input");
                in1.setAttribute('type', "hidden");
                in1.setAttribute('name', "hiddenButton");
                in1.setAttribute('value', electionIds[i - 1]);

                var btn = document.createElement("button");
                btn.setAttribute('type', "submit");
                btn.setAttribute('class', "list-group-item list-group-item-action");
                btn.setAttribute('name', "btn");
                btn.setAttribute('style', "text-align: center;");
                btn.setAttribute('value', electionIds[i - 1]);
                btn.setAttribute('onclick', "{document.frm.hdnbt.value = this.value; document.frm.submit();}");
                var t = document.createTextNode(electionNames[i - 1]);
                btn.appendChild(t);


                f.appendChild(in1);
                f.appendChild(btn);
                div2.appendChild(f);

            }
            divp.appendChild(div2);
            div1.appendChild(divp);
            document.getElementsByTagName('body')[0].appendChild(div1);
        </script>
        <script>
            $(document).ready(function () {
                $('#navigationMenu').load('menuBar.jsp');
            });
        </script>
    </body>
</html>
