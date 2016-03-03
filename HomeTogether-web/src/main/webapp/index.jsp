<%-- 
    Document   : newindex
    Created on : 1-mar-2016, 17.40.25
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="full">
    <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Hometogether</title>

    <!-- Bootstrap Core CSS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    <!-- Custom CSS -->
    <link rel="icon" href="http://example.com/favicon.png">
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
    <link rel="stylesheet" type="text/css" href="css/simpletextrotator.css" />
    <link href="css/style.css" rel='stylesheet' type='text/css' />
    <link href="css/ProfileStyle.css" rel='stylesheet' type='text/css' />
    <link href="css/the-big-picture.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Exo:100,200,300,400,500,600,700,800,900,100italic,200italic,300italic,400italic,500italic,600italic,700italic,800italic,900italic' rel='stylesheet' type='text/css'>
    
    <!--CUSTOM script-->
    
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <script type="text/javascript" src="js/googleLogin.js"></script>
    <script type="text/javascript" src="js/comuni.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link href='http://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Philosopher' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/style_1.css" />
    <link rel="stylesheet" href="css/font-awesome.min.css"/>
    <!--autocomplete-->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    

    <!-- jQuery -->
  
    <script type="text/javascript" src="js/jquery.simple-text-rotator.js"></script>
    
    
    <script>
            
            $(document).ready(function(){
                    $("h1 .rotate").textrotator({
                      animation: "flipUp", 
                      speed: 1750
                    });
      
		});

    </script>
    

</head>

<body>
    <!--FACEBOOK SDK-->  
        <div id="fb-root"></div>
        <script src="js/Facebook.js"></script>
        <!--END FACEBOOK LOGIN-->
        
        <!--EMAIL DI REGISTRAZIONE GIA' PRESENTE-->
        <script type="text/javascript">
            function sameEmail() {
                var xhttp = new XMLHttpRequest();
                var res = xhttp.responseText;
                var same = "same";
                if (res === same) {

                }
            }
        </script>
        <!--MODAL LOGIN E REGISTRAZIONE-->
        <%@include file="modal-login.jsp"%>
    <div class="header col-md-12">
      <div class="row">  
    
    <!-- Navigation -->
    <nav class="navbar navbar-default top-navbar">
                <div class="container-fluid">
                    <div class="navbar-header navbar-left">
                    <!--Button visualizzato al collpase della navbar-->
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                    </button>
                        <a class="navbar-brand logo-sx" href="#" style="position: relative;left: 15%;"><img alt="Brand" src="images/logoRettangolare.png"></a>
                    </div>
                    <!--COLLAPSE NAVBAR-->
                    <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
                        <!--LISTA ELEMENTI DELLA NAVBAR-->
                        <ul class="nav navbar-nav nav-top-list">
                            <li><a href="#iscriviti" class="scroll" id="iscriviti-link">ISCRIVITI</a><span> </span></li>
                            <li><a href="#LoginModal" data-toggle="modal" data-target="#login-modal" id="login-link">LOGIN</a><span> </span></li>
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
                        </ul>
                        
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <!-- Page Content -->
    <div class="container">
        <div class="row" style="margin-top: 5%;">
            <div class="col-md-6 feature-main">
                <div class="col-lg-12" style="padding: 15px">
                    <h1>
                        <strong>
                            <span class="rotate" style="color:#f1c40f;">Find, Live, Share, Home</span>
                        </strong>
                        <span style="color : white">Together</span> 
                    </h1>

                </div>
                <p>
                    Via da casa per qualche giorno? Problema di alloggio per breve periodo?
                </p>
                <p>
                    HOMETOGETHER è la soluzione! Chatta con i tuoi amici e scopri chi di loro ha voglia di condividere casa con te.
                </p>
                <p>
                    Potrete scrivere tutte le esperienze che vivrete, sul vostro HomeDiary e condivederle con gli altri.
                </p>
                <p>
                    Cosa aspetti? Iscriviti e inizia subito l'avventura!
                </p>

            </div>
            <div class="col-md-6 feature-main" id="iscriviti">
                <form id="registrationform" action="RegistrationServlet" method="post">
                    <h3>Iscriviti</h3>
                    <br>
                    <input type="hidden" name="action" value="registration">
                    <div class="form-group col-lg-6">
                        <!--<label>Password</label>-->
                        <input type="text" name="nome" class="form-control" id="nome" value="" placeholder="Nome">
                    </div>

                    <div class="form-group col-lg-6">
                        <!--<label>Password</label>-->
                        <input type="text" name="cognome" class="form-control" id="cognome" placeholder="Cognome">
                    </div>

                    <div class="form-group col-lg-12">
                        <!--<label>Username</label>-->
                        <input type="email" name="email" class="form-control" id="email" placeholder="Email">
                    </div>

                    <div class="form-group col-lg-12">
                        <!--<label>Repeat Email Address</label>-->
                        <input type="email" name="r_email" class="form-control" id="r_email" placeholder="Ripeti email">
                    </div>

                    <div class="form-group col-lg-12" id="div_password">
                        <!--<label>Password</label>-->
                        <input type="password" name="password" class="form-control" id="password" value="" placeholder="Nuova password">
                    </div>

                    <div class="form-group col-lg-12" id="div_r_password">
                        <!--<label>Password</label>-->
                        <input type="password" name="r_password" class="form-control" id="r_password" placeholder="Reinserisci la password">
                    </div>

                    <div class="form-group col-lg-12" id="div_password">
                        <input type="text" name="localita" class="form-control" id="localita" placeholder="Dove vivi?" onkeyup="autocompile()">
                    </div>

                    <input type="hidden" name="idSocial" class="form-control" id="idSocial" >
                    <input type="hidden" name="foto_profilo" class="form-control" id="foto_profilo" >
                    <input type="hidden" name="tipo_registrazione" class="form-control" id="tipo_registrazione" value="0">

                    <h4 style="color: white">Data di nascita</h4>
                    <br>
                    <div class="form-group col-lg-12">
                        <input type="date" name="data_nascita" class="form-control" id="data_nascita">  

                    </div>



                    <div data-type="radio" data-name="gender_wrapper">
                        <div class="form-group col-lg-3">
                            <input type="radio" name="sesso" id="sessof" value="1">
                            <label style="color: white">Donna</label>
                        </div>
                        <div class="form-group col-lg-3">
                            <input type="radio" name="sesso" id="sessom" value="2">
                            <label style="color: white">Uomo</label>
                        </div>
                        <div class="form-group col-lg-3">
                            <br>
                        </div>
                        <div class="form-group col-lg-3">
                            <br>
                        </div>
                    </div>
                    <div class="form-group col-lg-12">
                        <button type="button" class="btn btn-primary form-group col-lg-4" style="font-size: 150%; background-color: red; " onClick="checkComune()">Iscriviti</button>
                    </div>
                </form>

            </div>
        </div>
        <!-- /.row -->
    </div>
    <form id="socialForm" action="RedirectServlet"  role="form" method="post">
            <input type="hidden" name="action" value="loginSocial">
    </form>
    <!-- /.container -->
</body>
</html>
