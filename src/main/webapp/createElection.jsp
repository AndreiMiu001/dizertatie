<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="menuBarUpdated.jsp" />


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Style/CSS/datepicker3.css">       
        <link rel="stylesheet" href="CSS/DefaultStyle.css"> 
        <script src="Style/JS/datepicker.js"></script>         
        <style>
            .row {

                width: 50%;
            }
            .row2{
                width:35%;
            }
            .row3{
                width:10%;
            }
            .mypdg {
                padding: 5px;
            }
            .center {
                margin: auto;
                width: 50%;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <div id="navigationMenu"></div>
        <form method="post" action="CreateElectionServlet">
            <div class ="center">
                <div>
                    <div class="container">
                        <div class="mypdg">
                            <h1>Create election</h1>
                        </div>
                        <h3>Tip Alegere</h3>
                        <div class="row2">
                            <select name="category" id="category">
                                <c:forEach items="${listCategory}" var="category">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="row2">
                            <br><br>
                            <h4>Localitate</h4> <input class="form-control" type="text" name="localitate" id="localitateInput"/>
                            <h4>Judet</h4> <input class="form-control" type="text" name="judet" id="judetInput" autocomplete="off"/>
                            <br><br>
                        </div>
                        <h3>Nume alegeri</h3>
                        <div class="row2">
                            <input type="text" class="form-control" name="electionName" value="${electionName}" autocomplete="off"/>
                        </div>
                        <h5 style="color:red;font-size:90%">${electionNameNull}</h5>
                    </div>
                    <div class="container">
                        <h3>Numar intrari (partide/candidati)</h3>
                        <div class="row3">
                            <input type="text" class="form-control" name="candidatesNumber" value="${candidatesNumber}" autocomplete="off"/>
                        </div>
                        <h5 style="color:red;font-size:90%">${candidatesNumberNull}</h5>
                    </div>
                    <div class="container">
                        <h3>Data inceperii</h3>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-xs-5 date">
                                    <div class="input-group input-append date" id="datePicker1">
                                        <input type="text" class="form-control" name="electionDateStart" value="${electionDateStart}" autocomplete="off"/>
                                        <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h5 style="color:red;font-size:90%">${electionDateStartNull}</h5>
                    </div>
                    <div class="container">
                        <h3>Data finalizarii</h3>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-xs-5 date">
                                    <div class="input-group input-append date" id="datePicker2">
                                        <input type="text" class="form-control" name="electionDateEnd" value="${electionDateEnd}" autocomplete="off"/>
                                        <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h5 style="color:red;font-size:90%">${electionDateEndNull}</h5>
                    </div>
                    <div class="container">
                        <div class="row">
                            <h2>  
                                <button type="submit" name="Create" value="Create" class="btn btn-labeled btn-success">
                                    <span class="btn-label"></span>    Next step </button>
                                <br /></h2>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script>
            var nameField = document.getElementById("category");
            var localitateInput = document.getElementById("localitateInput");
            var judetInput = document.getElementById("judetInput");
            nameField.addEventListener("change", function () {
                //Update this to your logic...
                if (nameField.value === "1") {
                    localitateInput.disabled = true;
                    judetInput.disabled = true;
                    localitateInput.value = '';
                    judetInput.value = '';
                } else if (nameField.value === "2") {
                    localitateInput.disabled = true;
                    judetInput.disabled = false;
                    localitateInput.value = '';
                } else {
                    localitateInput.disabled = false;
                    judetInput.disabled = false;
                }
            });
        </script>
        <script>
            $(document).ready(function () {
                $('#datePicker1,#datePicker2')
                        .datepicker({
                            format: 'yyyy/mm/dd'
                        })
                        .on('changeDate', function (e) {
                            // Revalidate the date field
                            $('#eventForm').formValidation('revalidateField', 'date');
                        });

                $('#eventForm').formValidation({
                    framework: 'bootstrap',
                    icon: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        name: {
                            validators: {
                                notEmpty: {
                                    message: 'The name is required'
                                }
                            }
                        },
                        date: {
                            validators: {
                                notEmpty: {
                                    message: 'The date is required'
                                },
                                date: {
                                    format: 'YYYY/MM/DD',
                                    message: 'The date is not a valid'
                                }
                            }
                        }
                    }
                });
            });
        </script>
    </body>
</html>
