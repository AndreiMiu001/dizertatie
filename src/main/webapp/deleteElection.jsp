<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="menuBar.jsp" />
<!DOCTYPE html>
<html>
    <head>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
       
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <h2>My Customers</h2>

        <input type="text" id="myInput2" onkeyup="myFunction2()" placeholder="Search for names.." title="Type in a name">

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
            table.setAttribute('id', "electionTable")
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


            document.getElementsByTagName('body')[0].appendChild(divContainer);
        </script>

        <script>
            function myFunction2() {
                var input, filter, table, tr, td, i, txtValue, tBody;
                input = document.getElementById("myInput2");
                filter = input.value.toUpperCase();
                table = document.getElementById("electionTable");
                debugger;
                tBody = table.getElementsByTagName("tbody");
                tr = tBody[0].getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[1];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }
        </script>
    </body>
</html>
