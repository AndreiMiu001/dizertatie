<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .myclass
        {
            margin: auto;
            width: 60%;
            padding: 10px;
        }
        .thead-dark th{color:inherit;border-color:#dee2e6}
        .thead-dark th{color:#fff;background-color:#212529;border-color:#32383e}
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="Style/CSS/bootstrap3400.css">
    <script src="Style/JS/jquery340.js"></script>
    <script src="Style/JS/bootstrap340.js"></script>
    <head>
    </head>
    <body>
        <div id="navigationMenu"></div>

        <%

            ArrayList<String> candidatesList = (ArrayList) request.getAttribute("candidatesList");
            ArrayList<Integer> votesList = (ArrayList) request.getAttribute("votesList");
            int candidatesNo = 0;
            if (votesList != null) {
                candidatesNo = votesList.size();
            }
        %>
        <script>
            var len = <%= candidatesNo%>;
            var candidates = <%= candidatesList%>;
            var votes = <%= votesList%>;

            var divMyClass = document.createElement("div");
            divMyClass.setAttribute('class', "myclass");

            var table = document.createElement("table");
            table.setAttribute('class', "table");

            var thread = document.createElement("thead");
            thread.setAttribute('class', "thead-dark");

            var tr1 = document.createElement("tr");

            var th1 = document.createElement("th");
            th1.setAttribute('scope', "col");
            var t1 = document.createTextNode("No.");
            th1.appendChild(t1);

            var th2 = document.createElement("th");
            th2.setAttribute('scope', "col");
            var t2 = document.createTextNode("Candidate Name");
            th2.appendChild(t2);

            var th3 = document.createElement("th");
            th3.setAttribute('scope', "col");
            var t3 = document.createTextNode("Total votes");
            th3.appendChild(t3);

            tr1.appendChild(th1);
            tr1.appendChild(th2);
            tr1.appendChild(th3);

            thread.appendChild(tr1);
            table.appendChild(thread);

            var tbody = document.createElement("tbody");
            for (var i = 1; i <= len; i++)
            {
                var tr2 = document.createElement("tr");

                var thTmp = document.createElement("th");
                thTmp.setAttribute('scope', "row");

                var tTmp = document.createTextNode(i);
                thTmp.appendChild(tTmp);

                var tdTmp1 = document.createElement("td");

                tTmp = document.createTextNode(candidates[i - 1]);
                tdTmp1.appendChild(tTmp);

                var tdTmp2 = document.createElement("td");

                tTmp = document.createTextNode(votes[i - 1]);
                tdTmp2.appendChild(tTmp);

                tr2.appendChild(thTmp);
                tr2.appendChild(tdTmp1);
                tr2.appendChild(tdTmp2);

                tbody.appendChild(tr2);
            }

            table.appendChild(tbody);
            divMyClass.appendChild(table);

            document.getElementsByTagName('body')[0].appendChild(divMyClass);

        </script>
        <script>
            $(document).ready(function () {
                $('#navigationMenu').load('menuBar.jsp');
            });
        </script>
    </body>
</html>
