<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .central{

            max-width: 500px;
            margin: auto;

            padding: 4px;
        }
        .mybtn {
            padding-top:40px;
            padding-bottom: 50px;
        }
        h3 {
            padding:10px;
            padding-top: 25px;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
    </head>
    <body>
        <div id="navigationMenu"></div>

        <% int candidatesNumber = (Integer) request.getAttribute("candidatesNumber");
            ArrayList<String> nameError = new ArrayList<>();
            ArrayList<String> nameValue = new ArrayList<>();
            ArrayList<String> descriptionValue = new ArrayList<>();

            for (int i = 1; i <= candidatesNumber; i++) {
                String nameNullStr = (String) request.getAttribute("candidateNameNull" + i);
                String nameValueStr = (String) request.getAttribute("candidatesNameValue" + i);
                String descriptionValueStr = (String) request.getAttribute("candidatesDescriptionValue" + i);
                if (nameNullStr == null) {
                    nameNullStr = "\"\"";
                }
                nameError.add(nameNullStr);

                if (nameValueStr == null) {
                    nameValueStr = "\"\"";
                }
                nameValue.add(nameValueStr);

                if (descriptionValueStr == null) {
                    descriptionValueStr = "\"\"";
                }
                descriptionValue.add(descriptionValueStr);
            }
        %>
        <script>
            var len =<%= candidatesNumber%>;
            var nameErr =<%= nameError%>;
            var nameValue =<%= nameValue%>;
            var descriptionValue =<%= descriptionValue%>;


            var f = document.createElement("form");
            f.setAttribute('method', "post");
            f.setAttribute('action', "UpdateElectionServlet2");

            for (var i = 1; i <= len; i++) {
                var div5 = document.createElement("div");
                var div4 = document.createElement("div");
                var h4 = document.createElement("H6");
                var t4 = document.createTextNode(nameErr[i - 1]);
                h4.setAttribute('style', "color:red;font-size:90%");
                h4.appendChild(t4);
                div4.appendChild(h4);
                div5.appendChild(div4);
                var h = document.createElement("H3");
                var t = document.createTextNode("Candidate number " + i);
                h.appendChild(t);
                var h2 = document.createElement("H5");
                var t2 = document.createTextNode("Candidate name");
                h2.appendChild(t2);
                var h3 = document.createElement("H5");
                var t3 = document.createTextNode("Candidate description");
                h3.appendChild(t3);

                var candName = document.createElement("input"); //input element, text
                candName.setAttribute('type', "text");
                candName.setAttribute('name', "candidate" + i);
                candName.setAttribute('class', 'form-control');
                candName.setAttribute('value', nameValue[i - 1]);
                var candDesc = document.createElement("input"); //input element, text
                candDesc.setAttribute('type', "text");
                candDesc.setAttribute('name', "description" + i);
                candDesc.setAttribute('class', 'form-control');
                candDesc.setAttribute('value', descriptionValue[i - 1]);

                f.appendChild(h);
                f.appendChild(h2);
                f.appendChild(candName);
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
            finalDiv.setAttribute('class', "central");
            finalDiv.appendChild(f);

            document.getElementsByTagName('body')[0].appendChild(finalDiv);
        </script>
        <script>
            $(document).ready(function () {
                $('#navigationMenu').load('menuBar.jsp');
            });
        </script>
    </body>
</html>