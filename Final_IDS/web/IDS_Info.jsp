<%-- 
    Document   : IDS_Info
    Created on : Jun 27, 2020, 6:19:50 PM
    Author     : mohamed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
        <!-- Bootstrap core CSS     -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!--  Light Bootstrap Table core CSS    -->
        <link href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css" rel="stylesheet" />
        <link href="CSS/master.css" rel="stylesheet" />
        
        
        <script src="assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
	      <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
        
        <title>IDS</title>
    </head>
    <body>
        <body id="page1">
        <div class="wrapper">
            <div class="sidebar" data-color="grey" data-image="assets/28154.jpg">
                <div class="sidebar-wrapper">
                    <div class="logo">
                        <a href="newjsp.jsp" class="simple-text">SDI</a>
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
                        <li>
                            <a href="TablesInfo.jsp">
                                <i class="pe-7s-note2"></i>
                                <p>Tables Info</p>
                            </a>
                        </li>
                        <li class="active">
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
                            <a class="navbar-brand" href="IDS_Info.jsp">IDS Info</a>
                        </div>
                        <div class="collapse navbar-collapse">
                        </div>
                    </div>
                </nav>
                <div class="content">
                  <section id="team" class="pb-5">
                      <h5 class="section-title h1">OUR TEAM</h5>
                      <div class="row">
                        <!-- Team member -->
                        <div class="col-xs-12 col-sm-12 col-md-6">
                          <div class="image-flip">
                            <div class="mainflip flip-0">
                              <div class="frontside">
                                <div class="card">
                                  <div class="card-body text-center">
                                    <p><img class=" img-fluid" src="CSS/avatar.png" alt="card image"></p>
                                    <h4 class="card-title">Hamaida Mohamed Amine</h4>
                                    <p class="card-text">Master 2 (RID)</p>
                                  </div>
                                </div>
                              </div>
                              <div class="backside">
                                <div class="card">
                                  <div class="card-body text-center mt-4">
                                    <h4 class="card-title">Hamaida</h4>
                                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                    <ul class="list-inline">
                                        <li class="list-inline-item">
                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                <i class="fa fa-facebook"></i>
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                <i class="fa fa-twitter"></i>
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                <i class="fa fa-skype"></i>
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                <i class="fa fa-google"></i>
                                            </a>
                                        </li>
                                    </ul>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <!-- /Team member -->
                        <!-- Team member -->
                        <div class="col-xs-12 col-sm-12 col-md-6">
                          <div class="image-flip">
                            <div class="mainflip flip-0">
                              <div class="frontside">
                                <div class="card">
                                  <div class="card-body text-center">
                                    <p><img class=" img-fluid" src="CSS/avatar.png" alt="card image"></p>
                                    <h4 class="card-title">Mohamed Chaabani Abdellah</h4>
                                    <p class="card-text">Master 2 (RID)</p>
                                  </div>
                                </div>
                              </div>
                              <div class="backside">
                                <div class="card">
                                  <div class="card-body text-center mt-4">
                                    <h4 class="card-title">Chaabani</h4>
                                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                    <ul class="list-inline">
                                        <li class="list-inline-item">
                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                <i class="fa fa-facebook"></i>
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                <i class="fa fa-twitter"></i>
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                <i class="fa fa-skype"></i>
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a class="social-icon text-xs-center" target="_blank" href="https://www.fiverr.com/share/qb8D02">
                                                <i class="fa fa-google"></i>
                                            </a>
                                        </li>
                                    </ul>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <!-- Team member -->
                        <!-- about -->
                        <div id="about">
                          <h1 class="section-title">About us</h1>
                          <p class="text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                        </div>
                      </div>
                  </section>
                </div>
            </div>
        </div>
    </body>
</html>
