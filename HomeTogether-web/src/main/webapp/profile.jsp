<%-- 
    Document   : profile
    Created on : 29-gen-2016, 15.34.19
    Author     : Antonio
--%>
<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="it">
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--CSS-->
        <link href="css/popUpChat.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap.min_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap-theme_1.css" rel='stylesheet' type='text/css' />
        <link href="css/ProfileStyle.css" rel='stylesheet' type='text/css' />
        <link href="css/style_1.css" rel='stylesheet' type='text/css' />

        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/following.js"></script>
        <script src="js/popUpViewChat.js"></script>
        
        

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        
        <!--CSS-->
        
        

        <title>Profile Page</title>
        <!--MODAL PER SHOW IMMAGINE PROFILO-->
        <%@include file="modal-immagineProfilo.jsp"%>
        <!--MODAL PER LA MODIFICA DELLE INFO UTENTE-->
        <%@include file="modal-modInfo.jsp"%>
        <!--MODAL PER LA MODIFICA DEGLI INTERESSI-->
        <%@include file="modal-interessi.jsp"%>
        <!--MODAL PER LA MODIFICA DELLE LINGUE-->
        <%@include file="modal-lingue.jsp"%>
        <!--MODAL PER LA VISUALIZZAZIONE DEI FOLLOWING-->
        <%@include file="modal-following.jsp"%>
    </head>
    
    <body>
        <%@include file="navbar.jsp" %>
        <script type="text/javascript" src="js/websocket.js"></script>
        
        <!--HEADER-->

        <!--CONTENT-->
        <div class="content col-md-12">
            <div class="banner-orange">
                <div class="container">
                    <div class="banner-bottom">
                        <div class="container-fluid">
                            <section class="container">
                                <div class="container-page">
                                    <div class="col-md-12">
                                        <div class="row">
                                          <!-- left column -->
                                          <br><br><br><br>
                                          <div class="col-md-3 col-sm-4 col-xs-4" style="background: #fff">
                                            <br>
                                            <div class="text-center img_container">
                                                <c:if test="${profilo.id == id}">
                                                <div class="the-buttons col-md-3 col-sm-4 col-xs-4">
                                                    <form action="ProfileServlet" method="post" id="upload" enctype="multipart/form-data">
                                                        <i class="glyphicon glyphicon-camera" id="white-camera"></i>
                                                        <span class="btn-file">
                                                            <input type="file" name="nomeFile" onchange="upload.submit();"/>
                                                            <button class="borderless-btn">Cambia immagine profilo</button>
                                                        </span>
                                                        <input id="action" type="hidden" value="add_profile_image" name="action" class="form-control"/>
                                                    </form>
                                                </div>
                                                </c:if>
                                                
                                                <a href="#" data-toggle="modal" data-target="#avatar-modal"><img src="${profilo.foto_profilo}" class="avatar profile-image-avatar" alt="avatar"/></a>
                                            </div>
   
                                              <br>
                                              <div class="panel panel-info">
                                                <div class="panel-heading colored">
                                                             Su di te
                                                        </div>

                                                        <div class="panel-body">
                                                            Vive a <span>${profilo.comune.nome}</span>
                                                        </div>

                                                        <div class="panel-body">
                                                            Lavora presso <span>${profilo.occupazione}</span>
                                                        </div>

                                                        <div class="panel-body">
                                                            Nato il <span>${profilo.data_nascita}</span>
                                                        </div>

                                                        <div class="panel-footer" style="padding:3% 0 3% 0; ">
                                                            <div class="btn-group" role="group"> 
                                                                <c:if test="${profilo.id == id}">
                                                                <button class="col-md-4 btn btn-secodary borderless-btn" style="color: black;padding-left: 2px;" data-toggle="modal" data-target="#mod-info-modal">
                                                                    <i class="glyphicon glyphicon-info-sign"></i> Informazioni
                                                                </button>
                                                                <button class="col-md-4 btn btn-secodary borderless-btn" style="color: black;" data-toggle="modal" data-target="#following-modal">
                                                                    <i class="  glyphicon glyphicon-user"></i>Following
                                                                </button>
                                                                </c:if>
                                                                <button class="col-md-4 btn btn-secodary borderless-btn" style="color: black;" data-toggle="modal" data-target="#mod-interessi">
                                                                    <i class="glyphicon glyphicon-music"></i>Interessi
                                                                </button>
                                                                <button class="col-md-4 btn btn-secodary borderless-btn" style="color: black;" data-toggle="modal" data-target="#mod-lingue">
                                                                    <i class="glyphicon glyphicon-globe"></i>Lingue
                                                                </button>
                                                            </div>
                                                            
                                                        </div>
                                                        
                                                        
                                                    </div>

                                                    <ul class="list-group">
                                                        <li class="list-group-item list-group-item-info colored">
                                                          Amici Facebook
                                                        </li>
                                                        <c:forEach items="${profilo.following}" var="utente">
                                                            <li class="list-group-item ">
                                                                <form action="RedirectServlet" role="form" method="get">    
                                                                    <input type="hidden" name="action" value="goUserProfile">
                                                                    <input type="hidden" name="idprofile" value="${utente.id}">
                                                                        <button class="borderless-btn btn-link pull-xs-right" style="color:graytext"><span class="pull-xs-right"><img src="${utente.foto_profilo}" class="avatar img-circle" style="box-shadow: 0px 0px 2px orangered; " height='40px' width='40px'/></span>
                                                                        ${utente.nome} ${utente.cognome} </button><span  class="addClass pull-right glyphicon glyphicon-comment" onclick="register_popup('${utente.id}', '${utente.nome}');"></span>
                                                                </form>
                                                            </li>
                                                        </c:forEach>
                                                        <li class="list-group-item">
                                                          <span class="pull-xs-right"><img src="http://lorempixel.com/200/200/people/9/" class="avatar img-circle" alt="avatar" height='40px' width='40px'></span>
                                                          Cras justo odio<span class="addClass pull-right glyphicon glyphicon-comment" onclick="register_popup('qidea', 'QIdea');"></span>
       
                                                        </li>
                                                        <li class="list-group-item">
                                                          <span class="label label-default label-pill pull-xs-right">2</span>
                                                          Dapibus ac facilisis in
                                                        </li>
                                                        <li class="list-group-item">
                                                          <span class="label label-default label-pill pull-xs-right">1</span>
                                                          Morbi leo risus
                                                        </li>
                                                    </ul>
                                          </div>
                                          <div  class="col-md-9 col-sm-6 col-xs-8" style="position: relative; left: 8px">
                                              
                                              <a style="color:black"><h1>${profilo.nome}<span> </span>${profilo.cognome}</h1></a>
                                              <c:if test="${profilo.id != id}"> 
                                                
                                                        <form action="ProfileServlet" method="post" id="upload" enctype="multipart/form-data">
                                                               
                                                            <c:if test="${profilo.id != id}">
                                                                <button id="followbuton${profilo.id}" type="button" class="btn btn-success" onClick=" 
                                                                <c:choose>
                                                                    <c:when test="${amici==1}">
                                                                        
                                                                        eliminafollow(${profilo.id})">Stop Follow
                                                                    </c:when>    
                                                                    <c:otherwise>
                                                                        follow(${profilo.id})">Follow
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                </button>
                                                            </c:if>
                                                            
                                                        </form>
                                                            
                                                        
                                                
                                              </c:if>
                                              
                                              <h3>Torino</h3>
                                          </div>
                                          <!-- edit form column -->
                                          <div class="col-md-9 col-sm-7 col-xs-8 personal-info" style="background: #fff; position: relative; left: 8px;">

                                            <!--<div class="alert alert-info alert-dismissable">
                                              <a class="panel-close close" data-dismiss="alert">×</a> 
                                              <i class="fa fa-coffee"></i>
                                              This is an <strong>.alert</strong>. Use this to show important messages to the user.
                                            </div>-->
                                              <br>
                                              <div class="panel-group">
                                                  <c:forEach var="diario" items="${profilo.diari}">
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
                                                                <button type="submit" class="btn btn-primary form-group" >Entra</button>
                                                            </form>
                                                        </div>

                                                    </div>
                                                    <br>
                                                      
                                                  </c:forEach>
                                                    
                                                    
                                              </div>

                                          </div>
                                        </div>
                                      </div>
                                </div>
                            </section>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
        <!--CONTENT-->

        <!--FOOTER-->
        
        <div class="footer col-md-12">
            
                <%@include file="popUpChat.jsp"%>
           
            
        </div>
        <!--FOOTER-->
    </body>
    
</html>
