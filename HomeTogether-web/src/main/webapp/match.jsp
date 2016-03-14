<%-- 
    Document   : match
    Created on : 14-mar-2016, 19.36.25
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
       
        <link href="css/bootstrap_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap.min_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap-theme_1.css" rel='stylesheet' type='text/css' />
        <link href="css/ProfileStyle.css" rel='stylesheet' type='text/css' />
        <link href="css/style_1.css" rel='stylesheet' type='text/css' />
        <link href="css/the-big-picture.css" rel='stylesheet' type='text/css' />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script type="text/javascript" src="js/comuni.js"></script>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <br/><br/><br/><br/>
        <div class="container col-md-2" >
                
                
        </div>
        <div class="col-md-8" id="list" rel="1">
        <c:forEach var="match" items="${match}" varStatus="commentLoop">
                    <div id="div${commentLoop.index}" class="col-md-12" style="padding: 0% 0% 0% 15%;border: 1px solid whitesmoke;border-radius: 2px;">
                    <div class="col-md-10" style="background: white;  border-radius: 2px; padding: 2% 2% 2% 0%;box-shadow: 0px 0px 1px #888;">
                        <div class="col-md-3">
                            <form action="RedirectServlet" role="form" method="get">    
                                <input type="hidden" name="action" value="goUserProfile">
                                <input type="hidden" name="idprofile" value="${match.profilo.id}">
                                <button class="borderless-btn"><img src="${match.profilo.foto_profilo}" class="avatar profile-image-avatar" style="border: 0px solid; box-shadow: 0px 0px 5px #888;"/></button>
                            </form>
                        </div>
                        <div class="col-md-9">
                            <div class="col-md-6">
                                <h3>${match.profilo.nome}<span> </span>${match.profilo.cognome}</h3>
                                <span>Vive a ${match.profilo.comune.nome} </span> 
                                <p>Lavora presso ${match.profilo.occupazione}</p>
                            </div>
                            <div class="col-md-6">
                                <h4>Compatibilità</h4>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="70"
                                        aria-valuemin="0" aria-valuemax="100" style="width:${match.match_lingue*100}%">
                                          ${match.match_lingue*100} % lingue parlate
                                    </div>
                                </div>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="70"
                                        aria-valuemin="0" aria-valuemax="100" style="width:${match.match_interessi*100}%">
                                          ${match.match_interessi*100} % interessi
                                    </div>
                                </div>
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="70"
                                        aria-valuemin="0" aria-valuemax="100" style="width:${match.match_totale*100}%">
                                          ${match.match_totale*100} % generale
                                    </div>
                                </div>
                            </div> 
                        </div>
                        <div class="col-md-12">
                            <button class="btn btn-warning pull-right">Invia richiesta</button>
                        </div>
                    </div>

                </div>  
                </c:forEach>
            </div>
        <div class="col-md-2" >
                
                
        </div>
    </body>
</html>
