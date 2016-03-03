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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    </head>
    <body>
        <h1>Collaborative Whiteboard App</h1>
        
        <div id="output"></div>
        
        <script type="text/javascript" src="js/websocket.js"></script>
        <textarea id="messageTextArea" readonly="readonly" rows="10" cols="45"></textarea><br/>
        <input type="text" id="messageText" size="50"/><input type="button"  value="Send" onclick="sendMessage();"/>
    </body>
</html>
