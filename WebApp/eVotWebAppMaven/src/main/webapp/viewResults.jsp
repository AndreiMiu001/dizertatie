<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="menuBarUpdated.jsp" />

<html>
    <head>
        <!--Load the AJAX API-->

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="Style/JS/ChartSrc/chartloader.js"></script>
                <link rel="stylesheet" href="Style/CSS/updateElectionsViewList.css"/> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Rezultate</title>
    </head>
    <body>
        <div id="navigationMenu"></div>
        <div id="chart_div"></div>
                <script type="text/javascript">
            // Load the Visualization API and the corechart package.
            google.charts.load('current', {'packages': ['corechart']});
            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawChart);
            var electionJson = <%=request.getAttribute("electionsResultsJson")%>;
            var votesPrecentageJson = <%=request.getAttribute("votesPercentageJson")%>;

            // Callback that creates and populates a data table,
            // instantiates the pie chart, passes in the data and
            // draws it.
            var length = Object.keys(electionJson["mCandidatesArray"]).length;

            function drawChart() {
                // Create the data table.
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Candidat');
                data.addColumn('number', 'Voturi');
                for (var i = 0; i < length; i++) {
                    var name = electionJson["mCandidatesArray"][i]["mCandidateName"];
                    var value = electionJson["mCandidatesArray"][i]["mVotesCount"];
                    data.addRow([name, value]);
                }

                // Set chart options
                var options = {'title': electionJson["mElectionName"],
                    'width': 1500,
                    'height': 700};
                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
                chart.draw(data, options);
            }

            var divMyClass = document.createElement("div");
            divMyClass.setAttribute('class', "container content-all");

            var table = document.createElement("table");
            table.setAttribute('class', "table");

            var thread = document.createElement("thead");
            thread.setAttribute('class', "table-dark");

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
            var thPercentage = document.createElement("th");
            thPercentage.setAttribute('scope', "col");
            var textPercentage = document.createTextNode("Votes[%]");
            thPercentage.appendChild(textPercentage);


            tr1.appendChild(th1);
            tr1.appendChild(th2);
            tr1.appendChild(th3);
            tr1.appendChild(thPercentage);

            thread.appendChild(tr1);
            table.appendChild(thread);

            var tbody = document.createElement("tbody");
            for (var i = 1; i <= length; i++)
            {
                var tr2 = document.createElement("tr");

                var thTmp = document.createElement("th");
                thTmp.setAttribute('scope', "row");

                var tTmp = document.createTextNode(i);
                thTmp.appendChild(tTmp);

                var tdTmp1 = document.createElement("td");

                tTmp = document.createTextNode(electionJson["mCandidatesArray"][i - 1]["mCandidateName"]);
                tdTmp1.appendChild(tTmp);

                var tdTmp2 = document.createElement("td");

                tTmp = document.createTextNode(electionJson["mCandidatesArray"][i - 1]["mVotesCount"]);
                tdTmp2.appendChild(tTmp);
                
                var tdPercentage = document.createElement("td");
                tPrecentageTmp = document.createTextNode(votesPrecentageJson[i - 1]);
                tdPercentage.appendChild(tPrecentageTmp);

                tr2.appendChild(thTmp);
                tr2.appendChild(tdTmp1);
                tr2.appendChild(tdTmp2);
                tr2.appendChild(tdPercentage);

                tbody.appendChild(tr2);
            }

            table.appendChild(tbody);
            divMyClass.appendChild(table);

            document.getElementsByTagName('body')[0].appendChild(divMyClass);
            
        </script>
    </body>
</html>
