<%-- 
    Document   : TablesInfo
    Created on : Jun 5, 2020, 2:26:23 AM
    Author     : mohamed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
        <!-- Bootstrap core CSS     -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/css/dataTables.bootstrap.min.css" rel="stylesheet" />

        <!-- Animation library for notifications   -->
        <link href="assets/css/animate.min.css" rel="stylesheet"/>

        <!--  Light Bootstrap Table core CSS    -->
        <link href="assets/css/light-bootstrap-dashboard.css?v=1.4.0" rel="stylesheet"/>
        <!--     Fonts and icons     -->
        <link href="assets/css/font-awesome.min.css" rel="stylesheet">
        <link href="assets/css/roboto.css" rel='stylesheet'>
        <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
        <link href="CSS/css.css" rel="stylesheet" />
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="assets/js/jquery.dataTables.min.js"></script>
        <script src="assets/js/dataTables.bootstrap.min.js"></script>
        
        <title>JSP Page 2</title>
    </head>
    <body id="page3">
        <div class="wrapper">
            <div class="sidebar" data-color="grey" data-image="assets/28154.jpg">
                <div class="sidebar-wrapper">
                    <div class="logo">
                        <a href="newjsp.jsp" class="simple-text">
                            SDI
                        </a>
                    </div>
                    <ul class="nav" >
                        <li>
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
                        <li class="active">
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
                            <a class="navbar-brand" href="TablesInfo.jsp">Tables Info</a>
                        </div>
                        <div class="collapse navbar-collapse">
                            
                        </div>
                    </div>
                </nav>
                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="card">
                                    <div class="header">
                                        <h4 class="title">This section is going to display details about used table</h4>
                                        <p class="category">Here is a subtitle for this table</p>
                                    </div>
                                    <div class="form-group">
                                        
                                    </div>
                                    <ul class="nav nav-tabs">
                                        <li id="Training_tab" onclick="app.DatasetDisplay(1)"><a href="#">Training Dataset</a></li>
                                        <li id="Codage_tab" onclick="app.DatasetDisplay(2)"><a href="#">Codage</a></li>
                                        <li id="Norm_tab" onclick="app.DatasetDisplay(3)"><a href="#">Normalisation</a></li>
                                    </ul>
                                    <div id="myDIV2" class="MainTable2" style="display: none">
                                        <table class="table table-striped table-bordered" style="width:100%" id="CodedTable">
                                            <thead>
                                                    <th>logged_in</th>
                                                    <th>dst_host_same_srv_rate</th>
                                                    <th>dst_host_serror_rate</th>
                                                    <th>same_srv_rate</th>
                                                    <th>dst_host_srv_serror_rate</th>
                                                    <th>dst_host_srv_count</th>
                                                    <th>dst_host_srv_rerror_rate</th>
                                                    <th>srv_rerror_rate</th>
                                                    <th>dst_host_same_src_port_rate</th>
                                                    <th>protocol_type</th>
                                                    <th>flag</th>
                                                    <th>service</th>
                                                    <th>count</th>
                                                    <th>diff_srv_rate</th>
                                                    <th>is_guest_login</th>
                                                    <th>root_shell</th>
                                                    <th>wrong_fragment</th>
                                                    <th>class</th>
                                                </thead>
                                                <tbody>
                                                    
                                                </tbody>
                                        </table>
                                    </div>
                                    <div class="MainTable2" id="myDIV" style="display: none">
                                        <table class="table table-striped table-bordered" style="width:100%" id="trainingTable">
                                            <thead>
                                                    <th>logged_in</th>
                                                    <th>dst_host_same_srv_rate</th>
                                                    <th>dst_host_serror_rate</th>
                                                    <th>same_srv_rate</th>
                                                    <th>dst_host_srv_serror_rate</th>
                                                    <th>dst_host_srv_count</th>
                                                    <th>dst_host_srv_rerror_rate</th>
                                                    <th>srv_rerror_rate</th>
                                                    <th>dst_host_same_src_port_rate</th>
                                                    <th>protocol_type</th>
                                                    <th>flag</th>
                                                    <th>service</th>
                                                    <th>count</th>
                                                    <th>diff_srv_rate</th>
                                                    <th>is_guest_login</th>
                                                    <th>root_shell</th>
                                                    <th>wrong_fragment</th>
                                                    <th>attaque_name</th>
                                                </thead>
                                                <tbody>
                                                    
                                                </tbody>
                                        </table>                                        
                                    </div>
                                    <div class="MainTable2" id="myDIV3" style="display: none">
                                        <table class="table table-striped table-bordered" style="width:100%" id="NormTable">
                                            <thead>
                                                    <th>logged_in</th>
                                                    <th>dst_host_same_srv_rate</th>
                                                    <th>dst_host_serror_rate</th>
                                                    <th>same_srv_rate</th>
                                                    <th>dst_host_srv_serror_rate</th>
                                                    <th>dst_host_srv_count</th>
                                                    <th>dst_host_srv_rerror_rate</th>
                                                    <th>srv_rerror_rate</th>
                                                    <th>dst_host_same_src_port_rate</th>
                                                    <th>protocol_type</th>
                                                    <th>flag</th>
                                                    <th>service</th>
                                                    <th>count</th>
                                                    <th>diff_srv_rate</th>
                                                    <th>is_guest_login</th>
                                                    <th>root_shell</th>
                                                    <th>wrong_fragment</th>
                                                    <th>class</th>
                                                </thead>
                                                <tbody>
                                                    
                                                </tbody>
                                        </table>                                       
                                    </div>
                                </div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--   Core JS Files   -->
        <!--script src="assets/js/jquery.3.2.1.min.js" type="text/javascript"></script-->
        
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
        <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.11/lodash.min.js"></script>
        

        <!--script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script-->
        <script src="JS/js2.js"></script>
        <script>
            $(window).bind("load", function(){  
                $.ajax({
                    type : 'GET',
                    url : 'Info',
                });
            });
            
        </script>
    </body>
</html>
