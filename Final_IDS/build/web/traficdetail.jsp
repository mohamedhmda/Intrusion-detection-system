<%-- 
    Document   : traficdetail
    Created on : Jun 4, 2020, 2:31:45 PM
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
    <link href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdn.datatables.net/select/1.3.0/css/select.dataTables.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />

    <!--     Fonts and icons     -->
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/roboto.css" rel='stylesheet'>
    <link href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css"
        rel="stylesheet" />
    <link href="CSS/css.css" rel="stylesheet" />


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/select/1.3.0/js/select.dataTables.min.js"></script>

    <title>JSP Page 2</title>
</head>

<body id="page2">
    <div class="wrapper">
        <div class="sidebar" data-color="grey" data-image="assets/28154.jpg">
            <div class="sidebar-wrapper">
                <div class="logo">
                    <a href="newjsp.jsp" class="simple-text">
                        SDI
                    </a>
                </div>
                <ul class="nav">
                    <li>
                        <a href="newjsp.jsp">
                            <i class="pe-7s-graph"></i>
                            <p>Overview</p>
                        </a>
                    </li>
                    <li class="active">
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
                        <a class="navbar-brand" href="traficdetail.jsp">TraficDet</a>
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
                                    <h4 class="title">Upcoming paquet</h4>
                                    <hr>
                                </div>
                                <div class="content">
                                    <div class="MainTable">
                                        <table onclick="app.myFunction2(event)"
                                            class="table table-striped table-bordered" style="width:100%" id="table">
                                            <thead>
                                                <th>id</th>
                                                <th>time</th>
                                                <th>ip_source</th>
                                                <th>port_source</th>
                                                <th>ip_destination</th>
                                                <th>port_source</th>
                                                <th>prediction</th>
                                                <th>prediction_lvl2</th>
                                            </thead>
                                            <tbody>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div id="div1" class="MainTable table-responsive table-full-width">
                                <h7 class="title">Packet details</h7>
                                <ul id="Info" class="list-group">
                                    <li class="list-group-item">logged_in</li>
                                    <li class="list-group-item">dst_host_same_srv_rate</li>
                                    <li class="list-group-item">dst_host_serror_rate</li>
                                    <li class="list-group-item">same_srv_rate</li>
                                    <li class="list-group-item">dst_host_srv_serror_rate</li>
                                    <li class="list-group-item">dst_host_srv_count</li>
                                    <li class="list-group-item">dst_host_srv_rerror_rate</li>
                                    <li class="list-group-item">srv_rerror_rate</li>
                                    <li class="list-group-item">dst_host_same_src_port_rate</li>
                                    <li class="list-group-item">protocol_type</li>
                                    <li class="list-group-item">flag</li>
                                    <li class="list-group-item">service</li>
                                    <li class="list-group-item">count</li>
                                    <li class="list-group-item">diff_srv_rate</li>
                                    <li class="list-group-item">is_guest_login</li>
                                    <li class="list-group-item">root_shell</li>
                                    <li class="list-group-item">wrong_fragment</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>




    <!--   Core JS Files   -->
    <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

    <!--  Charts Plugin -->
    <script src="assets/js/chartist.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.11/lodash.min.js"></script>

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