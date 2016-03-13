<%-- 
    Document   : chatprova
    Created on : 3-mar-2016, 10.06.24
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="js/popUpChat.js"></script>
        <link href="css/popUpChat.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap.min_1.css" rel='stylesheet' type='text/css' />
        <link href="css/bootstrap-theme_1.css" rel='stylesheet' type='text/css' />
    </head>
    <body>
        <h1>Collaborative Whiteboard App</h1>
        
        <div id="output"></div>
        
        <script type="text/javascript" src="js/websocket.js"></script>
        
       <textarea id="messageTextArea" readonly="readonly" rows="10" cols="45"></textarea><br/>
        <span id="addClass" class="pull-right glyphicon glyphicon-comment"></span>
        <input type="text" id="messageText" size="50"/><input type="button"  value="Send" onclick="sendMessage();"/>
        
        <div class="popup-box chat-popup" id="qnimate">
            <div class="popup-head">
                <div class="popup-head-left pull-left">
                    <img src="http://bootsnipp.com/img/avatars/bcf1c0d13e5500875fdd5a7e8ad9752ee16e7462.jpg" alt="profile_img">
                    <a>Gurdeep Osahan</a>
                </div>
                    <div class="popup-head-right pull-right">
                        <div class="btn-group">
                            <button class="chat-header-button" data-toggle="dropdown" type="button" aria-expanded="false">
                                <i class="glyphicon glyphicon-cog"></i>
                            </button>
                            
                                <ul role="menu" class="dropdown-menu pull-right">
                                      <li><a href="#">Media</a></li>
                                      <li><a href="#">Block</a></li>
                                      <li><a href="#">Clear Chat</a></li>
                                      <li><a href="#">Email Chat</a></li>
                                </ul>
                        </div>

                        <button data-widget="remove" id="removeClass" class="chat-header-button pull-right" type="button"><i class="glyphicon glyphicon-off"></i></button>
                    </div>
            </div>
            <div class="popup-messages">
                <div class="direct-chat-messages">
                    <div id="output"></div>
                    <!-- Message. Default to the left -->
                    <div id="messageArea" class="direct-chat-msg doted-border">
                        
                    </div>
              <!-- /.direct-chat-msg -->
                </div>
            </div>
            <div class="popup-messages-footer">
                <textarea id="status_message" placeholder="Type a message..." rows="10" cols="40" name="message"></textarea>
                <div class="btn-footer">
                    <button class="bg_none"><i class="glyphicon glyphicon-film"></i> </button>
                    <button class="bg_none"><i class="glyphicon glyphicon-camera"></i> </button>
                    <button class="bg_none"><i class="glyphicon glyphicon-paperclip"></i> </button>
                    <button class="bg_none pull-right" onclick="sendMessage();"><i class="glyphicon glyphicon-thumbs-up"></i> </button>
                </div>
            </div>
	  </div>
    </body>
</html>
