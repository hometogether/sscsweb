<%-- 
    Document   : diary
    Created on : 29-feb-2016, 21.48.47
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery-1.11.0.min.js"></script>
        
        <script src="js/wow.min.js"></script>
        <link href="css/bootstrap_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap.min_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap-theme_1.css" rel='stylesheet' type='text/css' />
        <link href="css/ProfileStyle.css" rel='stylesheet' type='text/css' />
        
        <link href="css/style_1.css" rel='stylesheet' type='text/css' />
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
        
      
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="js/jquery.ns-autogrow.min.js"></script> 
        <script type="text/javascript">
            var max=0;
            $(document).ready(function() {
                if($('#like-list li').length > 3){
                   var e=$('#like-list li').length-1;
                   $('#like-list').hide();
                   $('#like-numb').append("Piace a:<a id='numb' style='font-size: 85%';> "+e+" persone</a>");
                   max=1;
                }
                
            });
            function addLike(){
                console.log($('#like-list li').length);
                $('#like-list').append("<li style='padding: 0000;margin: 0000;font-size: 85%;'><a>Nuovo utente<span>,</span></a></li>");
                console.log($('#like-list li').length);
                var e=$('#like-list li').length-1;
                if($('#like-list li').length > 3 && max===0){
                    $('#like-list').fadeOut();
                    $('#like-numb').append("Piace a:<a id='numb' style='font-size: 85%';> "+e+" persone</a>").fadeIn();
                    max=1;
                }else if($('#like-list li').length > 3&& max===1){
                    $('#numb').text(" "+e+" persone");
                }
            }
            $(function(){
                $('.postArea').css('overflow', 'hidden').autogrow({vertical: true, horizontal: false});
              });
        </script>
        <title>Diary</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
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
                                                                
                                                                </c:if>
                                                                <button class="col-md-4 btn btn-secodary borderless-btn" style="color: black;" data-toggle="modal" data-target="#mod-interessi">
                                                                    <i class="glyphicon glyphicon-music"></i>Interessi
                                                                </button>
                                                            </div>
                                                            
                                                        </div>
                                                        
                                                        
                                                    </div>

                                                    <ul class="list-group">
                                                        <li class="list-group-item list-group-item-info colored">
                                                          Amici Facebook
                                                        </li>
                                                        <li class="list-group-item">
                                                          <span class="pull-xs-right"><img src="http://lorempixel.com/200/200/people/9/" class="avatar img-circle" alt="avatar" height='40px' width='40px'></span>
                                                          Cras justo odio
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
                                          
                                          <!-- edit form column -->
                                          <div class="col-md-9 col-sm-9 col-xs-9 personal-info" style=" position: relative; ">
                                              <div id="div${commentLoop.index}" class="col-md-12" style="margin-bottom: 3%;border: 1px solid whitesmoke;border-radius: 2px;">
                                                  <div class="col-md-1"></div>
                                                  <div class="col-md-10" style="background: white;  border-radius: 2px;">
                                                    <form action="DiaryServlet" method="post" id="upload" enctype="multipart/form-data">
                                                      <input type="hidden" name="action" value="submitPost">
                                                      <input type="hidden" name="idDiario" value="${diario.id}">
                                                      <div class="col-md-12" style="margin-top: 3%;">
                                                         <button class="col-md-2 col-sm-2 col-lg-2 borderless-btn"><img src="${profilo.foto_profilo}" class="avatar profile-image-avatar" style="box-shadow: 0px 0px 5px #888; max-width: 50px;max-height: 50px;min-height: 50px;min-width: 50px;"/></button>
                                                         <textarea name="text" class="col-md-10 col-sm-10 col-lg-10 postArea" autofocus="autofocus"  placeholder="#SHARETOGETHER"></textarea>
                                                      </div>
                                                         <div class="col-md-12 col-lg-12 col-sm-12">
                                                            <div class="col-md-12" style="border-top: 1px solid lightgray; margin-bottom: 1%;margin-top: 4%; "></div>
                                                            <div class="col-md-12" style="margin-bottom: 1%;">
                                                                <button class="btn btn-primary pull-right" style="background:linear-gradient(to bottom, orange 0%, orangered 70%, red 100%);color:whitesmoke;">Pubblica</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                  </div>
                                                  <div class="col-md-1"></div>    
                                              </div>
                                            <div class="col-md-2 col-sm-2 col-lg-2"></div>
                                            <c:forEach var="post" items="${diario.post}">
                                                  <div id="div${commentLoop.index}" class="col-md-12" style="margin-bottom: 7%;border: 1px solid whitesmoke;border-radius: 2px;">
                                                    <div class="col-md-1"></div>
                                                    <div class="col-md-10" style="background: white;  border-radius: 2px;box-shadow: 0px 0px 5px orange;">
                                                        <!--HEADER-->
                                                        
                                                        <div class="col-md-12 col-sm-12 col-lg-12" style="margin-top: 3%;">
                                                            <button class="col-md-2 col-sm-2 col-lg-2 borderless-btn"><img src="${post.user.foto_profilo}" class="avatar profile-image-avatar" style="border: 0px solid; box-shadow: 0px 0px 5px #888; max-width: 50px;max-height: 50px;min-height: 50px;min-width: 50px;"/></button>
                                                            <h4>${post.user.nome} ${post.user.cognome}</h4>
                                                        </div>
                                                        <!--HEADER-->
                                                     
                                                        <!--COMMENT AREA-->
                                                        <div class="col-md-12 col-sm-12 col-lg-12">
                                                            
                                                            <div class="col-md-10 col-sm-10 col-lg-10"><h4 style="padding-left:2.5%;">${post.testo}</h4></div>
                                                            <!--<textarea class="form-control" placeholder="blablalbalbba" readonly="readonly"></textarea> -->
                                                        </div>
                                                        <!--COMMENT AREA-->
                                                        <!--LISTA LIKE DA MOSTRARE SOLO SE CI SONO UNO O PIU LIKE-->
                                                        <div class="col-md-12 col-lg-12 col-sm-12">
                                                        <div class="col-md-12 col-lg-12 col-sm-12" id="like-numb">   
                                                            
                                                            <ul id="like-list"class="list-inline">
                                                                <li style="padding-left:2.5%;">Piace a:</li>
                                                                <c:forEach var="like" items="${post.likes}">
                                                                    <li style="padding: 0000;margin: 0000;font-size: 85%;">
                                                                    <a>${like.nome} ${like.cognome}<span>,</span></a></li>
                                                                </c:forEach>
                                                                
                                                            </ul>                          
                                                        </div>
                                                        
                                                        </div>
                                                        <!--LISTA LIKE DA MOSTRARE SOLO SE CI SONO UNO O PIU LIKE-->
                                                        <div class="col-md-12 col-lg-12 col-sm-12">
                                                        <div role="separator" class="col-md-12 divider" style="border-top: 1px solid lightgray;"></div>
                                                        <div class="col-md-12 col-sm-12 col-lg-12 " style="margin: 1% 0 1% 0;">
                                                            <div class="col-md-2 col-sm-2 col-lg-2">
                                                                <button class="btn borderless-btn " style="color: black;" onclick="addLike();">
                                                                <i class="glyphicon glyphicon-hand-up"></i> Mi Piace
                                                              </button>
                                                            </div>
                                                            
                                                            <div class="col-md-8 col-sm-8 col-lg-8">
                                                                <div class="input-group" style="text-align: center;">                     
                                                                    <input type="text" class="form-control" placeholder="scrivi un commento" id="ric_utente" name="ric_utente" required="yes">
                                                                    <div class="input-group-btn" style="text-align: left">
                                                                        <button class="btn btn-info" type="submit" style="background: orange;" ><i class="glyphicon glyphicon-send"></i></button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            
                                                            
                                                            <div class="col-md-2 col-sm-2 col-lg-2">
                                                                <button class="btn borderless-btn col-md-2" style="color: black;">
                                                                    <i class=" glyphicon glyphicon-comment"></i> Commenti
                                                                </button>   
                                                            </div>
                                                            
                                                        </div>
                                                        </div>
                                                  </div>
                                                <div class="col-md-1"></div>
                                              </div>
                                            </c:forEach>
                                              <div class="col-md-2 col-sm-2 col-lg-2"></div>
                                              
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
            <h1>FOOTER</h1>
        </div>
        <!--FOOTER-->
    </body>
</html>