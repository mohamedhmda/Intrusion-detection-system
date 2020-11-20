<%-- 
    Document   : TablesInfo
    Created on : Jun 5, 2020, 2:26:23 AM
    Author     : mohamed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
        name='viewport' />
    <!-- Bootstrap core CSS     -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap.min.css" rel="stylesheet" />

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
                <ul class="nav">
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
                                    <li id="Training_tab" onclick="app.DatasetDisplay(1)"><a href="#">Training
                                            Dataset</a></li>
                                    <li id="Codage_tab" onclick="app.DatasetDisplay(2)"><a href="#">Codage</a></li>
                                    <li id="Norm_tab" onclick="app.DatasetDisplay(3)"><a href="#">Normalisation</a></li>
                                </ul>
                                <div id="myDIV2" class="MainTable2" style="display: none">
                                    <table class="table table-striped table-bordered" style="width:100%"
                                        id="CodedTable">
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
                                    <table class="table table-striped table-bordered" style="width:100%"
                                        id="trainingTable">
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

    <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

    <!--  Charts Plugin -->
    <script src="assets/js/chartist.min.js"></script>

    <script src="JS/js2.js"></script>
    
    <script>
        $(window).bind("load", function () {
            $.ajax({
                type: 'GET',
                url: 'Info',
            });
        });
    </script>
</body>

</html>