<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="menuBarUpdated.jsp" />

<html>
    <head>
        <!--Load the AJAX API-->

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="Style/JS/ChartSrc/chartloader.js"></script>

        <script type="text/javascript">
            // Load the Visualization API and the corechart package.
            google.charts.load('current', {'packages': ['corechart']});
            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawChart);
            var electionJson = <%=request.getAttribute("electionsResultsJson")%>;
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
                    'width': 1000,
                    'height': 900};
                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
                chart.draw(data, options);
            }
        </script>
        <title>Rezultate</title>
    </head>
    <body>
        <div id="navigationMenu"></div>
        <div id="chart_div"></div>
    </body>
</html>
