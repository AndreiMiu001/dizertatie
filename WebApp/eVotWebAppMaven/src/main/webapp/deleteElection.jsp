<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="menuBar.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="Style/CSS/default.css"/> 
        <link rel="stylesheet" href="Style/CSS/updateElectionsViewList.css"/> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="scripts/searchFunctionDelete.js"></script> 
        <title>Sterge candidat</title>
    </head>
    <body>
        <div id="content-all" class="content-all">
            <h2 class="content">Election list to delete</h2>
            <input class="content form-control" type="text" id="searchBar" onkeyup="searchElectionsDelete()" placeholder="Search for elections" title="Type in a name">
        </div>
        <script>
            var electionsArrayJson = ${electionsArrayJson};
            var electionsArrayLenght = Object.keys(electionsArrayJson).length;
            var divContainer = document.createElement("div");
            divContainer.setAttribute('class', "container");
            var divRow = document.createElement("div");
            divRow.setAttribute('class', "row");
            var divCol = document.createElement("div");
            var table = document.createElement("table");
            table.setAttribute('class', "table");
            table.setAttribute('id', "electionTable");
            var thead = document.createElement("thead");
            thead.setAttribute('class', "table-dark");

            const tableHeaders = ["Select election", "Election name"];
            var trHeaders = document.createElement("tr");

            for (var i = 0; i < tableHeaders.length; i++) {
                var th = document.createElement("th");
                th.setAttribute('scope', "col");
                var text = document.createTextNode(tableHeaders[i]);
                th.appendChild(text);
                trHeaders.appendChild(th);
            }

            var tBody = document.createElement("tbody");
            for (var i = 0; i < electionsArrayLenght; i++) {
                var trContent = document.createElement("tr");
                var tdCheckbox = document.createElement("td");
                var divCheckbox = document.createElement("div");
                divCheckbox.setAttribute('class', "custom-control custom-checkbox");
                var inputCheckbox = document.createElement("input");
                inputCheckbox.setAttribute('value', electionsArrayJson[i]["mIdElection"]);
                inputCheckbox.setAttribute('name', "idCheckbox");
                inputCheckbox.setAttribute("type", "checkbox");
                inputCheckbox.setAttribute("class", "custom-control-input");
                inputCheckbox.setAttribute("id", "customCheck" + i);
                var labelCheckbox = document.createElement("label");
                var textCheckbox = document.createTextNode(i + 1);


                labelCheckbox.appendChild(textCheckbox);
                divCheckbox.appendChild(labelCheckbox);
                divCheckbox.appendChild(inputCheckbox);
                tdCheckbox.appendChild(divCheckbox);

                var tdElectionName = document.createElement("td");
                var textElectionName = document.createTextNode(electionsArrayJson[i]["mElectionName"]);
                tdElectionName.appendChild(textElectionName);

                trContent.appendChild(tdCheckbox);
                trContent.appendChild(tdElectionName);
                tBody.appendChild(trContent);
            }

            thead.appendChild(trHeaders);
            divContainer.appendChild(divRow);
            divRow.appendChild(divCol);
            divCol.appendChild(table);
            table.appendChild(thead);
            table.appendChild(tBody);
            var form = document.createElement("form");
            form.setAttribute('method', "post");
            form.setAttribute('action', "DeleteElectionIdsServlet");
            form.appendChild(divContainer);
            var sendBtn = document.createElement("button");
            sendBtn.setAttribute("class", "btn btn-danger content");
            var textDelete = document.createTextNode("Delete selected");
            sendBtn.appendChild(textDelete);
            form.appendChild(sendBtn);

            var contentDiv = document.getElementById("content-all");
            contentDiv.appendChild(form);
        </script>
    </body>
</html>
