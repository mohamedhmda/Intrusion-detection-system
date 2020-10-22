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
        <meta http-equiv="Content-Type" content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
        <!-- Bootstrap core CSS     -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />

        <!-- Animation library for notifications   -->
        <link href="assets/css/animate.min.css" rel="stylesheet"/>

        <!--  Light Bootstrap Table core CSS    -->
        <link href="assets/css/light-bootstrap-dashboard.css?v=1.4.0" rel="stylesheet"/>
        <!--     Fonts and icons     -->
        <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
        <link href="assets/css/font-awesome.min.css" rel="stylesheet">
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
                    <ul class="nav" >
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
        
        <!--  Notifications Plugin    -->
        <script src="assets/js/bootstrap-notify.js"></script>
        <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
	<script src="assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>
        <!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
	<script src="assets/js/demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
        <!--script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script-->
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.11/lodash.min.js"></script>
        <script src="assets/js/jquery.dataTables.min.js"></script>
        <script src="JS/js2.js"></script>
        
        <script>
            $(window).bind("load", function(){
                $.ajax({
                    type : 'GET',
                    url : 'NewServlet',
                });
            });
        </script>
    </body>
</html>
