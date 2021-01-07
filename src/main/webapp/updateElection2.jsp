<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            padding-top:20px;

        }
        .center {
            margin: auto;
            width: 50%;
            padding: 10px;
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

        <form method="post" action="UpdateElectionServlet1">
            <div class ="center">
                <div>
                    <div class="container">
                        <div class="mypdg">
                            <h1></h1>
                            <h1>Update election</h1>

                            <h1></h1>
                        </div>
                        <h3>Election Name</h3>

                        <div class="row2">
                            <h5 style="color:blue">Current name is ${electionName}</h5>
                            <input type="text" class="form-control" name="electionName" value="${electionName}"/>
                        </div>
                        <h5 style="color:red;font-size:90%">${electionNameNull}</h5>
                    </div>
                    <div class="container">
                        <h3>Number of candidates</h3>
                        <h5 style="color:blue">Current candidates number is ${candidatesNumber}</h5>
                        <div class="row3">
                            <input type="text" class="form-control" name="candidatesNumber" value="${candidatesNumber}"/>
                        </div>
                        <h5 style="color:red;font-size:90%">${candidatesNumberNull}</h5>
                    </div>
                    <div class="container">
                        <h3>Starting date</h3>
                        <h5 style="color:blue">Current starting date is ${electionDateStart}</h5>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-xs-5 date">
                                    <div class="input-group input-append date" id="datePicker1">
                                        <input type="text" class="form-control" name="electionDateStart" value="${electionDateStart}"/>
                                        <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <h3>Ending date</h3>
                        <h5 style="color:blue">Current ending date is ${electionDateEnd}</h5>
                        <div class="row">
                            <div class="form-group">
                                <div class="col-xs-5 date">
                                    <div class="input-group input-append date" id="datePicker2">
                                        <input type="text" class="form-control" name="electionDateEnd" value="${electionDateEnd}"/>
                                        <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <h5 style="color:red;font-size:90%">${electionDateEndNull}</h5>
                    </div>
                    <div class="container">
                        <div class="row">
                            <h2>  <div class="col-md-12">
                                    <button type="submit" name="Create" value="Create" class="btn btn-labeled btn-success">
                                        <span class="btn-label"></span>    Next step </button>
                                <br /></h2>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script>
            $(document).ready(function () {
                $('#datePicker1,#datePicker2')
                        .datepicker({
                            format: 'mm/dd/yyyy'
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
                                    format: 'MM/DD/YYYY',
                                    message: 'The date is not a valid'
                                }
                            }
                        }
                    }
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $('#navigationMenu').load('menuBar.jsp');
            });
        </script>
    </body>
</html>
