/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
         $(window).scroll(function() {
                 if($(window).scrollTop() + $(window).height() === $(document).height()) {
                        var xhr = new XMLHttpRequest();
                        var utente = $("#ric_utente").val();
                        xhr.open("POST", "NavBarServlet", true);
                        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                        xhr.send('action=searchAjax&ric_utente=' + utente);
                        xhr.onload = function () {
                            var values=jQuery.parseJSON(xhr.responseText);
                            for(var i=0; i<5;i++){
                              $('#list').append('<div id="div'+i+'" class="col-md-12" style="padding: 0% 0% 0% 15%;border: 1px solid whitesmoke;border-radius: 2px;">'+
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
                                    '</div>'+
                                    
                                    
                                    '<c:if test="'+values[i].id+' != id}">'+
                                '<div class="col-md-6" style="text-align: right;">'+
                                    '<button id="followbuton'+values[i].id+'" type="button" class="btn btn-success" onClick=" '+
                                            '<c:set var="amici" value="0" />'+ 
                                            '<c:forEach var="following" items="${profilo.following}">'+
                                                '<c:if test="${following.id == '+values[i].id+'}">'+
                                                    '<c:set var="amici" value="1" />'+ 
                                                '</c:if>'+
                                            '</c:forEach>'+
                                            '<c:choose>'+
                                                '<c:when test="${amici==1}">'+
                                                   ' eliminafollow('+values[i].id+')">Stop Follow'+
                                                '</c:when>'+    
                                                '<c:otherwise>'+
                                                    'follow('+values[i].id+')">Follow'+
                                                '</c:otherwise>'+
                                            '</c:choose>'+




                                    '</button>'+
                                '</div>'+
                            '</c:if>'+
                            '<div class="col-md-12">'+
                                '<span>Vive a '+values[i].comune.nome+'</span>'+ 
                                '<p>Lavora presso '+values[i].occupazione+'</p>'+ 
                            '</div>'+
                        '</div>'+ 
                    '</div>'+

                '</div>'
                            );  
                            }
                            
                           console.log(values); 
                        };
                 }
         });
 });

