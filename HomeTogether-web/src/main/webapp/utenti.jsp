<%-- 
    Document   : utenti
    Created on : 17-feb-2016, 11.14.49
    Author     : Antonio
--%>

<%@page import="utility.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap_1.min.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap-theme_1.css" rel='stylesheet' type='text/css' />
        <link href="css/ProfileStyle.css" rel='stylesheet' type='text/css' />
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="js/following.js"></script>
        <!--<script src="js/search.js"></script>-->
        <script type="text/javascript">
        var offset = <%=Constants.LIMIT%>;;
        var res="";
        var bool = true;
        $(document).ready(function() {       
         $(window).scroll(function() {
                 if($(window).scrollTop() + $(window).height() === $(document).height() && bool===true) {
                        bool=false;
                        $('#list').append("<div class='col-md-12' id='loader' style='padding: 0% 0% 0% 15%;border: 1px solid whitesmoke;border-radius: 2px;'><div class='col-md-10'style='text-align: center;background: rgba(228, 131, 18, 0.1);  border-radius: 2px; padding: 0% 0% 0% 0%;box-shadow: 0px 0px 1px #888;'><img src='images/ajax-loader.gif'/></div></div>").fadeIn("slow");
                        $("html, body").animate({ scrollTop: $('#loader').offset().top }, 1500);
                        var xhr = new XMLHttpRequest();
                        var utente = '${ric_utente}';
                        xhr.open("POST", "NavBarServlet", true);
                        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                        xhr.send('action=searchAjax&ric_utente=' + utente+'&offset='+offset);
                        xhr.onload = function () {
                            var values=jQuery.parseJSON(xhr.responseText);
                            var id = '${id}';
                            var amici=0; 
                            if(values===null){
                                    $('#loader').fadeOut("slow").remove();
                                    $(window).unbind('scroll');
                            }else{
                                for(var i=0; i<values.length;i++){
                               res+=('<div id="div'+values[i].id+'" class="col-md-12" style="padding: 0% 0% 0% 15%;border: 1px solid whitesmoke;border-radius: 2px;">'+
                              '<div class="col-md-10" style="background: white;  border-radius: 2px; padding: 2% 2% 2% 0%;box-shadow: 0px 0px 1px #888;">'+
                                  '<div class="col-md-3">'+
                                      '<form action="RedirectServlet" role="form" method="get">'+    
                                          '<input type="hidden" name="action" value="goUserProfile">'+
                                          '<input type="hidden" name="idprofile" value="'+values[i].id+'">'+
                                          '<button class="borderless-btn"><img src="'+values[i].foto_profilo+'" class="avatar profile-image-avatar" style="border: 0px solid; box-shadow: 0px 0px 5px #888;"/></button>'+
                                      '</form>'+
                                  '</div>'+
                                  '<div class="col-md-9">'+
                                      '<div class="col-md-6">'+
                                          '<h3>'+values[i].nome+'<span> </span>'+values[i].cognome+'</h3>'+
                                      '</div>');
                                      if (values[i].id != id){
                                          res+='<div class="col-md-6" style="text-align: right;">'+
                                              '<button id="followbuton'+values[i].id+'" type="button" class="btn btn-success" onClick=" ';

                                       var idUtente;
                                       var idFollowing;
                                       <c:set var="idUtente" value= 'values[i].id' />
                                         idUtente=<c:out value='${idUtente}'/>;
                                      <c:forEach var="following" items="${profilo.following}">

                                          <c:set var="idFollowing" value='${following.id}' />
                                          idFollowing=('${idFollowing}');   

                                          if(idUtente==idFollowing){
                                              amici=1;
                                          }


                                      </c:forEach>

                                      if (amici ===1){
                                          res+=' eliminafollow('+values[i].id+')">Stop Follow';
                                      } else {
                                          res+='follow('+values[i].id+')">Follow';
                                      }
                                      amici=0;
                                      res+= 

                                      '</button>'+
                                  '</div>';


                                      }
                                      res+='<div class="col-md-12">';
                                      if (values[i].comune!=null){
                                          res+='<span>Vive a '+values[i].comune.nome+'</span>';
                                      }

                                  res+= '<p>Lavora presso '+values[i].occupazione+'</p>'+ 
                              '</div>'+
                          '</div>'+ 
                      '</div>'+

                  '</div>';           


                                  }
                                
                                
                                $('#list').delay(1000).queue(function (next) {
                                    $('#loader').fadeOut("slow").remove();
                                    $('#list').append(res).fadeIn("slow");
                                    res="";
                                    bool=true;
                                    next();
                                });
                                
                                    
                            }      
                        };
                        offset+=5;
                 }
         });
 });
        </script>

        <title>Utenti trovati</title>
    </head>
    <body>
        
        <%@include file="navbar.jsp" %>
        <div class="content col-md-12 ">
            <br><br><br>
            <div class="container col-md-2" style="background: yellow" >
                <h3>DESTRA</h3>
                
            </div>
            <div class="col-md-8" id="list" rel="1">
                
                <br>
                <c:forEach var="utente" items="${utente}" varStatus="commentLoop">
                    <div id="div${commentLoop.index}" class="col-md-12" style="padding: 0% 0% 0% 15%;border: 1px solid whitesmoke;border-radius: 2px;">
                    <div class="col-md-10" style="background: white;  border-radius: 2px; padding: 2% 2% 2% 0%;box-shadow: 0px 0px 1px #888;">
                        <div class="col-md-3">
                            <form action="RedirectServlet" role="form" method="get">    
                                <input type="hidden" name="action" value="goUserProfile">
                                <input type="hidden" name="idprofile" value="${utente.id}">
                                <button class="borderless-btn"><img src="${utente.foto_profilo}" class="avatar profile-image-avatar" style="border: 0px solid; box-shadow: 0px 0px 5px #888;"/></button>
                            </form>
                        </div>
                        <div class="col-md-9">
                            <div class="col-md-6">
                                <h3>${utente.nome}<span> </span>${utente.cognome}</h3>
                            </div>
                            <c:if test="${utente.id != id}">
                                <div class="col-md-6" style="text-align: right;">
                                    <button id="followbuton${utente.id}" type="button" class="btn btn-success" onClick=" 
                                            <c:set var="amici" value="0" /> 
                                            <c:forEach var="following" items="${profilo.following}">
                                                <c:if test="${following.id == utente.id}">
                                                    <c:set var="amici" value="1" /> 
                                                </c:if>
                                            </c:forEach>
                                            <c:choose>
                                                <c:when test="${amici==1}">
                                                    eliminafollow(${utente.id})">Stop Follow
                                                </c:when>    
                                                <c:otherwise>
                                                    follow(${utente.id})">Follow
                                                </c:otherwise>
                                            </c:choose>




                                    </button>
                                </div>
                            </c:if>
                            <div class="col-md-12">
                                <span>Vive a ${utente.comune.nome}</span> 
                                <p>Lavora presso ${utente.occupazione}</p> 
                            </div>
                        </div> 
                    </div>

                </div>  
                </c:forEach>
            </div>
            <div class="col-md-2" style="background: blue">
                <h3>SINISTRA</h3>
            </div>
        </div>
    </body>
</html>
