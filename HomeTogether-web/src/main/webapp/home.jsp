<%-- 
    Document   : home
    Created on : 15-feb-2016, 11.19.00
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/popUpViewChat.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <link href="css/popUpChat.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap.min_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap-theme_1.css" rel='stylesheet' type='text/css' />
        <link href="css/ProfileStyle.css" rel='stylesheet' type='text/css' />
        <link href="css/style_1.css" rel='stylesheet' type='text/css' />
        <link href="css/the-big-picture.css" rel='stylesheet' type='text/css' />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script type="text/javascript" src="js/comuni_match.js"></script>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        
        
        
        
        <!--CSS-->
        
        <title>HOME Page</title>
    </head>
    
    <body>
        <%@include file="navbar.jsp" %>
        <%@include file="error.jsp" %>
        <script type="text/javascript" src="js/websocket.js"></script>
        <br/><br/><br/><br/>
        
        <div class="col-md-3">
            <div class="white-element">
                <h3>${profilo.nome}<span> </span>${profilo.cognome}</h3>
                <p>${profilo.comune.nome}<span>, </span>${profilo.comune.provincia.regione.nome}</p>
            </div>
            <br>
            <ul class="list-group">
                <li class="list-group-item list-group-item-info colored">
                  Amici
                </li>

                <c:forEach items="${profilo.following}" var="utente">
                    <li class="list-group-item ">
                        <form action="RedirectServlet" role="form" method="get">    
                            <input type="hidden" name="action" value="goUserProfile">
                            <input type="hidden" name="idprofile" value="${utente.id}">
                                <button class="borderless-btn btn-link pull-xs-right" style="color:graytext"><span class="pull-xs-right"><img src="${utente.foto_profilo}" class="avatar img-circle" style="box-shadow: 0px 0px 2px orangered; " height='40px' width='40px'/></span>
                                ${utente.nome} ${utente.cognome} </button><span id="chat${utente.id}"  class="addClass pull-right glyphicon glyphicon-comment" onclick="register_popup('${utente.id}', '${utente.nome}','${utente.foto_profilo}');"></span>
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </div>
            <div class="col-md-8" style="margin-left: 3%;">
               <div class="white-element">
                    <h1>
                        <strong>
                            <span style="color:#f1c40f;">#Find</span>
                        </strong>
                        <span style="color : lightgrey">Together</span> 
                    </h1>
                    <form action="MatchServlet" method="get" id="formatchservlet" >
                        <div class="input-group triple-input">

                                <input type="hidden" name="action" value="searchUtente" /> 

                                <input type="text" id="regione" name="regione" placeholder="Regione" class="form-control" onkeyup="autocompileRe()"  /> 
                                <input type="text" id="provincia" name="provincia" placeholder="Provincia" class="form-control" onkeyup="autocompilePro()" /> 
                                <input type="text" id="localita" name="localita" placeholder="Comune" class="form-control" onkeyup="autocompile()"/> 
                                <span class="input-group-btn">
                                    <button class="btn btn-warning " type="submit"><i class="glyphicon glyphicon-search"></i>Vai!</button>
                                </span>
                        </div>
                    </form>
                  </div>
                <br>
                <div class="panel-group">
                                <c:forEach var="following" items="${profilo.following}">
                                    <c:forEach var="diario" items="${following.diari}">
                                        <div class="panel panel-info">
                                          <div class="panel-heading colored">
                                              ${diario.nome}
                                          </div>

                                          <div class="panel-body">

                                              Creato il ${diario.data_inizio}

                                          </div>
                                          <div class="panel-body">

                                              Partecipanti al diario:
                                              <c:forEach var="partecipante" items="${diario.partecipanti}">
                                                  <a href="/HomeTogether-web/RedirectServlet?action=goUserProfile&idprofile=${partecipante.id}">${partecipante.nome} ${partecipante.cognome}</a>


                                              </c:forEach>

                                          </div>        
                                          <div class="panel-footer">
                                              <form action="DiaryServlet" method="get">
                                                  <input type="hidden" name="action" value="goToDiary">
                                                  <input type="hidden" name="idDiario" value="${diario.id}">   
                                                  <button type="submit" class="btn btn-warning form-group" >Entra</button>
                                              </form>
                                          </div>

                                      </div>
                                      <br>
                                    </c:forEach>
                                </c:forEach>


                            </div>
            </div>
            
        <div class="col-md-2" ></div>
    </body>
</html>
