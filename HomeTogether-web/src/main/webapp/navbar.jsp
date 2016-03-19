<%-- 
    Document   : navbar
    Created on : 16-feb-2016, 10.52.34
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <script type="text/javascript" src="js/utenti.js"></script>    
    
    <body>
        <% HttpSession s=request.getSession();%>
        <!--HEADER-->
        <div class="header col-md-12">
            <!--NAVBAR-->
            <nav class="navbar navbar-default top-navbar">
                <div class="container-fluid">
                    <div class="navbar-header">
                    <!--Button visualizzato al collpase della navbar-->
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                    </button>
                    <form role="form" method="get" action="RedirectServlet">
                        <input type="hidden" name="action" value="goHome">
                        <button type="submit" class="navbar-brand borderless-btn logo-sx" style="position: relative;left: 15%;"><img alt="Brand" src="images/logoRettangolare.png"></button>
                    </form>
                    </div>
                    <!--COLLAPSE NAVBAR-->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <!--LISTA ELEMENTI DELLA NAVBAR-->
                        <!--<ul class="nav navbar-nav nav-top-list">
                            <li><a href="#">Link 1</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">One more separated link</a></li>
                                </ul>
                            </li>
                        </ul>-->
                        <!--BARRA DI RICERCA-->
                        <form class="navbar-form navbar-left nav-top-list search-bar-form" role="form" method="get" action="NavBarServlet">
                            <div class="input-group col-md-12" style="text-align: center;">
                                <input type="hidden" name="action" value="searchUtente">
                                <input type="text" class="form-control" placeholder="Cerca persone" id="ric_utente" name="ric_utente" required="yes">
                                <div class="input-group-btn" style="text-align: left">
                                    <button class="btn btn-default" type="submit" ><i class="glyphicon glyphicon-search"></i></button>
                                </div>
                            </div>
                        </form>
                        <ul class="nav navbar-nav nav-top-list-rigth">
                            <li class="dropdown">
                                <a id="dLabel" role="button" data-toggle="dropdown" data-target="#" href="/page.html">
                                <i class="glyphicon glyphicon-bell"></i>
                              </a>

                              <ul class="dropdown-menu dropdown-menu-notifications notifications dropdown-menu-right" role="menu" aria-labelledby="dLabel">

                                <div class="notification-heading"><h4 class="menu-title">Notifications</h4><h4 class="menu-title pull-right">View all<i class="glyphicon glyphicon-circle-arrow-right"></i></h4>
                                </div>
                                <li class="divider"></li>
                               <div class="notifications-wrapper">
                                 <a class="content" href="#">

                                   <div class="notification-item">
                                    <h4 class="item-title">Evaluation Deadline 1 · day ago</h4>
                                    <p class="item-info">Marketing 101, Video Assignment</p>
                                  </div>

                                </a>
                                 <a class="content" href="#">
                                  <div class="notification-item">
                                    <h4 class="item-title">Evaluation Deadline 1 · day ago</h4>
                                    <p class="item-info">Marketing 101, Video Assignment</p>
                                  </div>
                                </a>
                                 <a class="content" href="#">
                                  <div class="notification-item">
                                    <h4 class="item-title">Evaluation Deadline 1 • day ago</h4>
                                    <p class="item-info">Marketing 101, Video Assignment</p>
                                  </div>
                                </a>
                                 <a class="content" href="#">
                                  <div class="notification-item">
                                    <h4 class="item-title">Evaluation Deadline 1 • day ago</h4>
                                    <p class="item-info">Marketing 101, Video Assignment</p>
                                  </div>

                                </a>
                                 <a class="content" href="#">
                                  <div class="notification-item">
                                    <h4 class="item-title">Evaluation Deadline 1 • day ago</h4>
                                    <p class="item-info">Marketing 101, Video Assignment</p>
                                  </div>
                                </a>
                                 <a class="content" href="#">
                                  <div class="notification-item">
                                    <h4 class="item-title">Evaluation Deadline 1 • day ago</h4>
                                    <p class="item-info">Marketing 101, Video Assignment</p>
                                  </div>
                                </a>

                               </div>
                                <li class="divider"></li>
                                <div class="notification-footer"><h4 class="menu-title">View all<i class="glyphicon glyphicon-circle-arrow-right"></i></h4></div>
                              </ul>
                            </li>
                            <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="background: transparent; padding-top: 7%;">
                                <img src="<%=(String)s.getAttribute("foto")%>" class="profile-image img-circle"><b class="caret"></b></a>

                                <ul class="dropdown-menu">
                                <li><form role="form" action="RedirectServlet" method="get"><input type="hidden" name="action" value="goProfile"><button type="submit" class=" btn-link borderless-btn" style="color: black; padding-left: 7%"><i class="glyphicon glyphicon-user"></i><span> </span>Profilo</button></form></li>
                                <li><button type="submit" class="btn-link borderless-btn" style="color: black; padding-left: 7%;"><i class="glyphicon glyphicon-cog"></i><span> </span>Impostazioni</button></li>
                                <li role="separator" class="divider"></li>
                                <li><form role="form" action="RedirectServlet" method="post"><input type="hidden" name="action" value="logOut"><button type="submit" class="btn-link borderless-btn" style="color: black; padding-left: 7%"><i class="glyphicon glyphicon-log-out"></i><span> </span>Log out</button></form></li>
                              </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!--NAVBAR-->
        </div>
    </body>
</html>
