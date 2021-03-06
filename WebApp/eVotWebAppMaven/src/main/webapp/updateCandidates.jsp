<%@page import="java.util.ArrayList"%>
<jsp:include page="menuBar.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Style/CSS/DefaultStyle.css"> 
        <link rel="stylesheet" href="Style/CSS/insertCandidates.css"> 
        <link rel="stylesheet" href="Style/CSS/errorMessage.css"> 
        <title>eVot adauga candidati</title>
    </head>
    <body onload="init(candidatesArrayJson);">
        <div id="navigationMenu"></div>
        <script>
            var candidatesArrayJson = ${candidatesArrayJson};
            var candidatesArrayLenght = ${candidatesNumber};
            var nameErrList = ${nameErrorList};

            var f = document.createElement("form");
            f.setAttribute('method', "post");
            f.setAttribute('action', "UpdateElectionServlet");

            for (var i = 0; i < candidatesArrayLenght; i++) {
                debugger;
                var div5 = document.createElement("div");
                var h = document.createElement("H3");
                var t = document.createTextNode("Candidate number " + (i + 1));
                h.appendChild(t);
                var h2 = document.createElement("H5");
                var t2 = document.createTextNode("Candidate name");
                h2.appendChild(t2);
                var nameErrH = document.createElement("H5");
                nameErrH.setAttribute('class', "error");
                var nameErrText = document.createTextNode(nameErrList[i]);
                nameErrH.appendChild(nameErrText);
                var h3 = document.createElement("H5");
                var t3 = document.createTextNode("Candidate description");
                h3.appendChild(t3);
                var candName = document.createElement("input"); //input element, text
                candName.setAttribute("autocomplete", "off");
                candName.setAttribute('type', "text");
                candName.setAttribute('name', "candidateName" + i);
                candName.setAttribute('class', 'form-control central');
                candName.setAttribute('value', candidatesArrayJson[i]["mCandidateName"]);
                var candDesc = document.createElement("input"); //input element, text
                candDesc.setAttribute("placeholder", "Optional");
                candDesc.setAttribute("autocomplete", "off");
                candDesc.setAttribute('type', "text");
                candDesc.setAttribute('name', "candidateDescription" + i);
                candDesc.setAttribute('class', 'form-control central');
                candDesc.setAttribute('value', candidatesArrayJson[i]["mDescription"]);

                f.appendChild(h);
                f.appendChild(h2);
                f.appendChild(candName);
                f.appendChild(nameErrH);
                f.appendChild(div5);
                f.appendChild(h3);
                f.appendChild(candDesc);
            }
            var div = document.createElement("div");
            div.setAttribute("class", "mybtn");
            var btn = document.createElement("button");
            btn.setAttribute("type", "submit");
            btn.setAttribute("name", "Create");
            btn.setAttribute("value", "Create");
            btn.setAttribute("class", "btn btn-labeled btn-success");
            var span = document.createElement("span");
            var txt = document.createTextNode("Finish election update");
            span.setAttribute("class", "btn-label");
            span.appendChild(txt);
            btn.appendChild(span);
            div.appendChild(btn);
            f.appendChild(div);

            var finalDiv = document.createElement("div");
            finalDiv.setAttribute('class', "content");
            finalDiv.appendChild(f);

            document.getElementsByTagName('body')[0].appendChild(finalDiv);
        </script>
    </body>
</html>