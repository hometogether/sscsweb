<%-- 
    Document   : hashtag
    Created on : 4-apr-2016, 13.13.01
    Author     : Andrea22
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="images/AppIcon.ico" />
        <script src="js/jquery-1.11.0.min.js"></script>
        <script src="js/post.js"></script>
        <script src="js/popUpViewChat.js"></script>
        <script type="text/javascript" src="js/websocket.js"></script>

        <script src="js/wow.min.js"></script>
        <link href="css/bootstrap_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap.min_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap-theme_1.css" rel='stylesheet' type='text/css' />
        <link href="css/ProfileStyle.css" rel='stylesheet' type='text/css' />
        
        <link href="css/popUpChat.css" rel='stylesheet' type='text/css' />
        <link href="css/style_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap-dialog.css" rel='stylesheet' type='text/css' />
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
        
      
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="js/bootstrap-dialog.js"></script>
	<script src="js/jquery.ns-autogrow.min.js"></script> 
        <script type="text/javascript">
            
            $(document).ready(function() {
                if($('#like-list li').length > 3){
                   var e=$('#like-list li').length-1;
                   $('#like-list').hide();
                   $('#like-numb').append("Piace a:<a id='numb' style='font-size: 85%';> "+e+" persone</a>");
                   max=1;
                }
                
            });
            var xhr = new XMLHttpRequest();
            
            
            
            function goToEditPost(idPost){
                $("#textPost"+idPost).attr('contenteditable','true');
                placeCaretAtEnd( document.getElementById("textPost"+idPost) );
                
            }
            
            function editPost(idPost) {
                console.log("entro in editPost");
                var testo = $('#textPost'+idPost).text();
                if($('#textPost'+idPost)[0]!=="") {
                    xhr.open('POST', 'DiaryServlet');
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.onload = function () {
                        if (xhr.responseText.trim() === "0") {
                            
                            jQuery.noConflict();
                            $('#textPost'+idPost).text(testo);
                            $('#textPost'+idPost).attr('contenteditable','false');
                            console.log('post editato');
                            $('#textPost'+idPost).val(xhr.responseText.trim());

                        } else {
                            BootstrapDialog.warning('Impossibile modificare il Post');
                        }
                    };
                    xhr.send('action=editPost&idPost='+idPost+"&testo="+testo);
                    
                } else{
                    BootstrapDialog.warning('Non puoi pubblicare un post vuoto!');
                }
                
            }
            
            function removePost(idPost) {
                console.log("entro in removePost");
                xhr.open('POST', 'DiaryServlet');
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onload = function () {
                    if (xhr.responseText.trim() === "0") {
                        jQuery.noConflict();
                        $('#post'+idPost).remove();
                        console.log('post rimosso');

                    } else {
                        // GESTIRE ERRORE
                    }
                };
                 xhr.send('action=removePost&idPost='+idPost);
            }
            function addLike(idPost){
                xhr.open('POST', 'DiaryServlet');
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onload = function () {
                    if (xhr.responseText.trim() === "0") {
                        jQuery.noConflict();
                        if($('#like-list'+idPost+' li').length === 1){
                            $('#piace_a'+idPost).fadeIn();
                        }
                        $('#piace_a'+idPost).after("<li id='me"+idPost+"' style='padding: 0000;margin: 0000;font-size: 85%;'><a>te<span>,</span></a></li>");
                        $("#likebutton"+idPost).attr("onclick",'removeLike('+idPost+')');
                        $("#icona"+idPost).html("<B>Mi Piace</B>");
                        $("#icona"+idPost).attr("style" ,"color:rgba(228, 131, 18, 0.6)");
                        var e=$('#like-list'+idPost+' li').length-1;
                        console.log('e add:'+e);
                        console.log("idPost:"+idPost);
                        if($('#like-list'+idPost+' li').length === 4){
                            $('#like-list'+idPost).fadeOut();
                            $('#like-numb'+idPost).html("<li style='padding-left:2.5%;'>Piace a:<a id='numb"+idPost+"' style='font-size: 85%';> "+e+" persone</a></li>").show();

                        }else if($('#like-list'+idPost+' li').length > 4){
                            $('#numb'+idPost).text(" "+e+" persone");
                        }
                    } /*else {
                     // $('#googleForm').submit();
                     }*/

                };
                xhr.send('action=addLike&idPost=' + idPost);
                
            }
            
            function removeLike(idPost){
                
                xhr.open('POST', 'DiaryServlet');
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onload = function () {
                    if (xhr.responseText.trim() === "0") {
                        jQuery.noConflict();

                        $('#me'+idPost).remove();
                        $("#likebutton"+idPost).attr("onclick",'addLike('+idPost+')');
                        $("#icona"+idPost).html("Mi Piace");
                        $("#icona"+idPost).attr("style" ,"");
                       
                        var e=$('#like-list'+idPost+' li').length-1;
                        console.log('e remove:'+e);
                        console.log("idPost:"+idPost);
                        if($('#like-list'+idPost+' li').length < 4){
                            console.log("entro quiii");
                            $('#like-numb'+idPost).fadeOut();

                            $('#like-list'+idPost).fadeIn(); 
                        } else if($('#like-list'+idPost+' li').length === 4){
                            $('#like-list'+idPost).fadeOut();
                            $('#like-numb'+idPost).html("<li style='padding-left:2.5%;'>Piace a:<a id='numb"+idPost+"' style='font-size: 85%';> "+e+" persone</a></li>").fadeIn();

                        }else if($('#like-list'+idPost+' li').length > 4){
                            $('#numb'+idPost).text(" "+e+" persone");
                        }
                        if($('#like-list'+idPost+' li').length === 1){
                            $('#piace_a'+idPost).fadeOut();
                        }
                    } /*else {
                     // $('#googleForm').submit();
                     }*/

                };
                xhr.send('action=removeLike&idPost=' + idPost);    
                        
            }
            
            
            $(function(){
                $('.postArea').css('overflow', 'hidden').autogrow({vertical: true, horizontal: false});
              });
              
            function addComment(idPost){
                var testo = $('#commento_utente'+idPost).val();
                console.log("testo:"+testo);
                xhr.open('POST', 'DiaryServlet');
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onload = function () {
                    if (!(xhr.responseText.trim() === "0")) {
                        var idCommento = xhr.responseText.trim();
                        $('#commentContainer'+idPost).append("<div id='commento'"+idCommento+" class='row'><div class='col-md-12'style='background:whitesmoke'>"+
                                "<div class='row col-md-12 col-sm-12 col-lg-12' style='padding-top:2%;'>"+
                                '<span class="glyphicon glyphicon-pencil pull-right btn-sm"></span>'+
                                     "<button class='col-md-1 col-sm-1 col-lg-1 borderless-btn'><img src='${profilo.foto_profilo}' class='avatar profile-image-avatar' style='border: 0px solid; box-shadow: 0px 0px 5px #888; max-width: 35px;max-height: 35px;min-height: 35px;min-width: 35px;'/></button>"+
                                     "<div class='col-md-10'><div class='col-md-12'>"+

                                     "<textarea id='commentArea"+idCommento+"' class='postArea' readonly='readonly' style='width:100%;margin-top:0;'></textarea></div>"+
                                         "</div>"+
                                         "</div>"+
                                         
                                         "</div>");
                         var nome='${profilo.nome}';
                         var cognome='${profilo.cognome}';
                         /*var id='${profilo.id}';
                         console.log (nome);
                         console.log(cognome);*/
                         var comment=nome+' '+cognome+' - '+testo;
                         $('#commentArea'+ idCommento).append(comment);
                         $('#commentArea'+ idCommento).css('overflow', 'hidden').autogrow({vertical: true, horizontal: false});
                         console.log("idcommento:"+idCommento);
                         
                    } /*else {
                     // $('#googleForm').submit();
                     }*/

                };
                xhr.send('action=addComment&idPost='+idPost+'&testo='+testo);   
                
            }
            
            function goToEditComment(idComment){
                
                $("#commentArea"+idComment).attr('contenteditable','true');
                placeCaretAtEnd( document.getElementById("commentArea"+idComment) );
                
            }
            
            function placeCaretAtEnd(el) {
                el.focus();
                if (typeof window.getSelection != "undefined"
                        && typeof document.createRange != "undefined") {
                    var range = document.createRange();
                    range.selectNodeContents(el);
                    range.collapse(false);
                    var sel = window.getSelection();
                    sel.removeAllRanges();
                    sel.addRange(range);
                } else if (typeof document.body.createTextRange != "undefined") {
                    var textRange = document.body.createTextRange();
                    textRange.moveToElementText(el);
                    textRange.collapse(false);
                    textRange.select();
                }
            }
            
            function editComment(idCommento) {
                var testo = $('#commentArea'+idCommento).text();
                console.log("testo:"+testo);
                if($('#commentArea'+idCommento)[0]!=="") {
                    xhr.open('POST', 'DiaryServlet');
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.onload = function () {
                        if (xhr.responseText.trim() === "0") {
                            jQuery.noConflict();
                            $('#commentArea'+idCommento).text(testo);
                            $("#commentArea"+idCommento).attr('contenteditable','false');
                            console.log('commento editato');

                        } else {
                            BootstrapDialog.warning('Impossibile modificare il Post');
                        }
                    };
                    xhr.send('action=editComment&idCommento='+idCommento+"&testo="+testo);
                    
                } else{
                    BootstrapDialog.warning('Non puoi pubblicare un commento vuoto!');
                }
                
            }
            
            function removeComment(idCommento) {
                console.log("entro in removeComment");
                xhr.open('POST', 'DiaryServlet');
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onload = function () {
                    if (xhr.responseText.trim() === "0") {
                        jQuery.noConflict();
                        $('#commento'+idCommento).remove();
                        console.log('post rimosso');

                    } else {
                        // GESTIRE ERRORE
                    }
                };
                 xhr.send('action=removeComment&idCommento='+idCommento);
            }
            function keyDownComment (idPost){
                console.log("keyDownComment");
                console.log("valore textbox:"+$('#commento_utente'+idPost).val());
                if(event.keyCode == 13){
                    addComment(idPost);
                       
                }
            }
            function keyUpComment (idPost){
                console.log("keyUpComment");
                console.log("valore textbox:"+$('#commento_utente'+idPost).val());
                if(event.keyCode == 13){
                    $('#commento_utente'+idPost).val("");
                }
            }
            
            function keyDownEditComment (idCommento){
                console.log("keyDownEditComment");
                if(event.keyCode == 13){
                    editComment(idCommento);
                       
                }
            }
            
            function keyDownEditPost (idPost){
                console.log("keyDownEditPost");
                if(event.keyCode == 13){
                    editPost(idPost);
                       
                }
            }
            
            
                
        </script>        
        <title>Post Hashtag</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <%@include file="error.jsp" %>
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
                                          
                                          <!-- edit form column -->
                                          <div id="div" class="col-md-9 col-sm-9 col-xs-9 personal-info" style=" position: relative; ">
                                              
                                            <c:forEach var="post" items="${post}">
                                                
                                                  <div id="post${post.id}" class="col-md-12" style="margin-bottom: 0%;border: 1px solid whitesmoke;border-radius: 2px;">
                                                    <div class="col-md-1"></div>
                                                    <div class="col-md-10" style="background: white;  border-radius: 2px;box-shadow: 0px 0px 5px orange;margin-bottom:7%;">
                                                        <!--HEADER-->
                                                        
                                                        <div class="col-md-12 col-sm-12 col-lg-12" style="margin-top: 3%;">
                                                            <c:if test="${post.user.id == profilo.id}">

                                                            <div class="dropdown-post pull-right">
                                                                <span class="glyphicon glyphicon-chevron-down dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"></span>
                                                                <ul class="dropdown-menu ">
                                                                    <li><a href="javascript:goToEditPost(${post.id})"><span class="glyphicon glyphicon-edit"> </span> Modifica</a></li>
                                                                    <li><a href="javascript:removePost(${post.id})"><span class="glyphicon glyphicon-remove"> </span> Elimina</a></li>
                                                                </ul>
                                                            </div
                                                            </c:if>
                                                            <form action="RedirectServlet" role="form" method="get">    
                                                                <input type="hidden" name="action" value="goUserProfile">
                                                                <input type="hidden" name="idprofile" value="${post.user.id}">
                                                                <button class="col-md-2 col-sm-2 col-lg-2 borderless-btn"><img src="${post.user.foto_profilo}" class="avatar profile-image-avatar" style="border: 0px solid; box-shadow: 0px 0px 5px #888; max-width: 50px;max-height: 50px;min-height: 50px;min-width: 50px;"/></button>
                                                            </form>
                                                            <h4><a href="/HomeTogether-web/RedirectServlet?action=goUserProfile&idprofile=${post.user.id}">${post.user.nome} ${post.user.cognome}</a></h4>
                                                        </div>
                                                        <!--HEADER-->
                                                     
                                                        <!--COMMENT AREA-->
                                                        <div class="col-md-12 col-sm-12 col-lg-12">
                                                            
                                                            <div class="col-md-10 col-sm-10 col-lg-10">
                                                                <p id="textPost${post.id}" style="word-wrap:break-word;margin-top: 2%; margin-left: 2%;" onkeydown="keyDownEditPost(${post.id})"> ${post.testo}</p>
                                                                
                                                            </div>
                                                            <!--<textarea class="form-control" placeholder="blablalbalbba" readonly="readonly"></textarea> -->
                                                        </div>
                                                        <!--COMMENT AREA-->
                                                        <!--LISTA LIKE DA MOSTRARE SOLO SE CI SONO UNO O PIU LIKE-->
                                                        <div class="col-md-12 col-lg-12 col-sm-12">
                                                        <div class="col-md-12 col-lg-12 col-sm-12" >   
                                                            
                                                            <ul id="like-list${post.id}"class="list-inline">
                                                                
                                                                <li id="piace_a${post.id}" style="padding-left:2.5%;">Piace a:</li>
                                                                
                                                                <c:if test= "${fn:length(post.likes) == 0}" >
                                                                   <script type="text/javascript">
                                                                        var idPost='${post.id}';
                                                                        $('#piace_a'+idPost).fadeOut();
                                                                        
                                                                    </script>
                                                                   
                                                                </c:if>
                                                                
                                                                
                                                                <c:set var="found" value="0"/>    
                                                                <c:forEach var="like" items="${post.likes}">
                                                                <c:choose> 
                                                                <c:when test="${like.id==profilo.id}">
                                                                    <script type="text/javascript">
                                                                        var idPost='${post.id}';
                                                                        $('#piace_a'+idPost).after("<li id='me"+idPost+"' style='padding: 0000;margin: 0000;font-size: 85%;'><a>te<span>,</span></a></li>");
                                                                        
                                                                    </script>
                                                                    <c:set var="found" value="1"/>

                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <li style="padding: 0000;margin: 0000;font-size: 85%;">
                                                                        <a href="/HomeTogether-web/RedirectServlet?action=goUserProfile&idprofile=${like.id}">${like.nome} ${like.cognome}<span>,</span></a></li>
                                                                    </c:otherwise>
                                                                        </c:choose>   
                                                                </c:forEach>
                                                                
                                                            </ul>                                                                  
                                                            <ul id="like-numb${post.id}" class="list-inline"></ul>
                       
                                                        </div>
                                                        
                                                        </div>
                                                        <!--LISTA LIKE DA MOSTRARE SOLO SE CI SONO UNO O PIU LIKE-->
                                                        
                                                        <div class="col-md-12 col-lg-12 col-sm-12">
                                                        <div role="separator" class="col-md-12 divider" style="border-top: 1px solid lightgray;"></div>
                                                        <div class="col-md-12 col-sm-12 col-lg-12 " style="margin: 1% 0 1% 0;">
                                                            <div class="col-md-2 col-sm-2 col-lg-2">
                                                                <c:choose>   
                                                                    <c:when test="${found==0}">   
                                                                        <button id="likebutton${post.id}" class="btn borderless-btn " style="color: black;" onclick="addLike(${post.id});">
                                                                        <i class="glyphicon glyphicon-hand-up"></i> <a id="icona${post.id}">Mi Piace</a>
                                                                    </c:when> 
                                                                    <c:otherwise>   
                                                                        <button id="likebutton${post.id}" class="btn borderless-btn " style="color: black;" onclick="removeLike(${post.id});">
                                                                            <i class="glyphicon glyphicon-hand-up"></i> <a id="icona${post.id}" style="color:rgba(228, 131, 18, 0.6)"><B>Mi Piace</B></a>
                                                                    </c:otherwise>  
                                                                </c:choose>   
                                                                
                                                              </button>
                                                            </div>
                                                            
                                                            <div class="col-md-8 col-sm-8 col-lg-8">
                                                                <div style="text-align: center;">
                                                                    <textarea id="commento_utente${post.id}" placeholder="#TalkTogether" onkeydown="keyDownComment(${post.id})" onkeyup="keyUpComment(${post.id})" required="yes" class="postArea" style="width:100%;margin-top:0;border: 1px solid lightgray;"></textarea>
                                                                </div>
                                                            </div>
                                                            
                                                            
                                                            <div class="col-md-2 col-sm-2 col-lg-2">
                                                                <button class="btn borderless-btn col-md-2" style="color: black;">
                                                                    <i class=" glyphicon glyphicon-comment"></i> Commenti
                                                                </button>   
                                                            </div>
                                                            
                                                        </div>
                                                        </div>
                                                        
                                                        <div id="commentContainer${post.id}">
                                                            
                                                            <c:forEach var="commento" items="${post.commenti}">
                                                            <div id="commento${commento.id}" class='row'><div class='col-md-12'style='background:whitesmoke'>
                                                                <div class='row col-md-12 col-sm-12 col-lg-12' style='padding-top:2%;'>
                                                                     <c:if test="${commento.user.id == profilo.id}">
                                                                    <div class="popover-markup pull-right"> <span class="btn btn-secodary btn-sm glyphicon glyphicon-pencil trigger" data-toggle="popover"></span> 
                                                                    <div class="head hide">Lorem Ipsum</div>
                                                                    <div class="content hide">
                                                                        <ul class="list-unstyled">
                                                                            <li style="margin-bottom: 5px;"><a href="javascript:goToEditComment(${commento.id})"><span class="glyphicon glyphicon-edit"> </span> Modifica</a></li>
                                                                            <li><a href="javascript:removeComment(${commento.id})"><span class="glyphicon glyphicon-remove"> </span> Elimina</a></li>
                                                                        </ul>
                                                                    </div>
                                                                    <div class="footer hide">test</div>
                                                                </div>
                                                                     </c:if>
                                                                    <button class='col-md-1 col-sm-1 col-lg-1 borderless-btn'><img src='${commento.user.foto_profilo}' class='avatar profile-image-avatar' style='border: 0px solid; box-shadow: 0px 0px 5px #888; max-width: 35px;max-height: 35px;min-height: 35px;min-width: 35px;'/></button>
                                                                    <div class='col-md-10'>
                                                                        <div class='col-md-12'>
                                                                        <p><a href="URL" title="Description">${commento.user.nome} ${commento.user.cognome} </a></p>
                                                                        <p id="commentArea${commento.id}" onkeydown="keyDownEditComment(${commento.id})" style="word-wrap:break-word;"> ${commento.testo}</p>
                                                                        </div>
                                                                        <!--<div class='col-md-12'>
                                                                        <textarea id='commentArea${commento.id}' class='postArea' onkeydown="keyDownEditComment(${commento.id})" readonly='readonly' style='width:100%;margin-top:0;'>${commento.user.nome} ${commento.user.cognome} - ${commento.testo}</textarea>
                                                                        </div>-->
                                                                    </div>
                                                                </div>
                                                           </div></div>
                                                        <div class="col-md-1"></div>
                                                            </c:forEach>

                                                        </div>
                                                         </div>
                                                            </div>
                                               
                                            </c:forEach>
                                                        <script>
                                                                        $('.popover-markup>.trigger').popover({
                                                                            html: true,
                                                                            content: function () {
                                                                                return $(this).parent().find('.content').html();
                                                                            }
                                                                        });
                                                                        $('body').on('click', function (e) {
                                                                            $('[data-toggle="popover"]').each(function () {
                                                                                //the 'is' for buttons that trigger popups
                                                                                //the 'has' for icons within a button that triggers a popup
                                                                                if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {
                                                                                    $(this).popover('hide');
                                                                                }
                                                                            });
                                                                        });
                                                                    </script>                                            
                                            </div>
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

        
    </body>
</html>
