<%-- 
    Document   : newjsp
    Created on : May 25, 2020, 5:58:02 PM
    Author     : mohamed
--%>

<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
        name='viewport' />
    <!-- Bootstrap core CSS     -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Animation library for notifications   -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

    <!--     Fonts and icons     -->
    <link href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css"
        rel="stylesheet" />
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/roboto.css" rel='stylesheet'>
    <link href="CSS/css.css" rel="stylesheet" />
    <title>IDS</title>
</head>

<body id="page1">
    <div class="wrapper">
        <div class="sidebar" data-color="grey" data-image="assets/28154.jpg">
            <div class="sidebar-wrapper">
                <div class="logo">
                    <a href="newjsp.jsp" class="simple-text">SDI</a>
                </div>
                <ul class="nav">
                    <li class="active">
                        <a href="newjsp.jsp">
                            <i class="pe-7s-graph"></i>
                            <p>Overview</p>
                        </a>
                    </li>
                    <li>
                        <a href="traficdetail.jsp">
                            <i class="pe-7s-network"></i>
                            <p>Trafic Details</p>
                        </a>
                    </li>
                    <li>
                        <a href="TablesInfo.jsp">
                            <i class="pe-7s-note2"></i>
                            <p>Tables Info</p>
                        </a>
                    </li>
                    <li>
                        <a href="IDS_Info.jsp">
                            <i class="pe-7s-edit"></i>
                            <p>IDS Info</p>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="main-panel">
            <nav class="navbar navbar-default navbar-fixed">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="newjsp.jsp">Overview</a>
                    </div>
                    <div class="collapse navbar-collapse"></div>
                </div>
            </nav>
            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-5">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">Real time chart</h4>
                                    <p class="category">Attaques vs Normal</p>
                                </div>
                                <div class="content">
                                    <div id="chartPreferences" class="ct-chart ct-perfect-fourth">
                                        <canvas id="myChart"></canvas>
                                    </div>
                                    <div class="footer">
                                        <div class="stats" id="totalP">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">Packet prediction</h4>
                                    <p class="category"></p>
                                </div>
                                <div class="content">
                                    <div id="chartPreferences" class="ct-chart ct-perfect-fourth">
                                        <canvas id="myChart2"></canvas>
                                    </div>
                                    <div class="footer">
                                        <div class="stats">
                                            <hr>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive table-full-width talaa">
                                <h3 class="title">Assessment measure</h3>
                                <ul class="list-group">
                                    <li id="time" class="list-group-item">execution time : 00:00:00.000</li>
                                    <li id="acc" class="list-group-item">Accuracy : 96%</li>
                                    <li id="VN" class="list-group-item">True negative : 50</li>
                                    <li id="FP" class="list-group-item">False positive : 3</li>
                                    <li id="FN" class="list-group-item">False negative : 1</li>
                                    <li id="VP" class="list-group-item">True positive : 46</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--   Core JS Files   -->
    <script src="assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
    <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

    <!--  Charts Plugin -->
    <script src="assets/js/chartist.min.js"></script>


    <!--script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script-->
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.11/lodash.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="JS/js2.js"></script>

    <script>
        $(window).bind("load", function () {
            $.ajax({
                type: 'GET',
                url: 'NewServlet',
            });
        });
    </script>
</body>

</html>