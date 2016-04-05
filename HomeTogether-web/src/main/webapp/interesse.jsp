<%-- 
    Document   : interesse
    Created on : 29-feb-2016, 22.36.27
    Author     : Andrea22
--%>
<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="it">
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--CSS-->
        <link href="css/bootstrap_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap_1.min.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap-theme_1.css" rel='stylesheet' type='text/css' />
        <link href="css/ProfileStyle.css" rel='stylesheet' type='text/css' />
        <link href="css/style_1.css" rel='stylesheet' type='text/css' />

        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/following.js"></script>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <!--CSS-->
        
        

        <title>Interesse</title>
        
    </head>
    
    <body>
        <%@include file="navbar.jsp" %>
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
                                            
                                          </div>
                                          <div  class="col-md-9 col-sm-6 col-xs-8" style="position: relative; left: 8px">
                                              <span id='nomewiki'><B>${nome}</B></span><BR>
                                              <span id='wiki'>${xml}</span>
                                                <script type="text/javascript">
                                                   $('#wiki').each(function(){
                                                       var $this = $(this);
                                                       var t = $this.text();
                                                       $this.html(t.replace('&lt','<').replace('&gt', '>'));
                                                   });
                                                </script>
                                              
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
