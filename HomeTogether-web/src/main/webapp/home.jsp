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
        
        
        
        
        <!--CSS-->
        
        <title>HOME Page</title>
    </head>
    <body>
        <%@include file="navbar.jsp" %>
        <br/><br/><br/><br/>
        
        <div class="col-md-2">
            <div class="white-element">
                <h3>${profilo.nome}<span> </span>${profilo.cognome}</h3>
                <p>${profilo.comune.nome}<span>, </span>${profilo.comune.provincia.regione.nome}</p>
            </div>
        </div>
            <div class="col-md-8 white-element" style="margin-left: 3%;">
            <h1>
                <strong>
                    <span style="color:#f1c40f;">#Find</span>
                </strong>
                <span style="color : lightgrey">Together</span> 
            </h1>
            <form action="MatchServlet" method="post" id="formatchservlet" >
            <div class="input-group triple-input">
               
                    <input type="hidden" name="action" value="searchUtente" /> 

                    <input type="text" id="regione" name="regione" placeholder="Regione" class="form-control" onkeyup="autocompileRe()"  /> 
                    <input type="text" id="provincia" name="provincia" placeholder="Provincia" class="form-control" onkeyup="autocompilePro()" /> 
                    <input type="text" id="localita" name="localita" placeholder="Comune" class="form-control" onkeyup="autocompile()"/> 
                    <span class="input-group-btn">
                        <button class="btn btn-default " type="submit"><i class="glyphicon glyphicon-search"></i>Vai!</button>
                    </span>
              
            </div>
                 </form>
          </div>
        <div class="col-md-2" ></div>
    </body>
</html>
