/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri="ws://" + document.location.host +"/HomeTogether-web/chatendpoint";
var websocket = new WebSocket(wsUri);


var output = document.getElementById("output");
websocket.onopen = function(evt) { onOpen(evt); };

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}
websocket.onmessage= function processMessage(message){
                var jsonData= JSON.parse(message.data);
                if(jsonData!==null) messageTextArea.value += jsonData.message+'\n';
            };
            function sendMessage(){
                websocket.send(messageText.value);
                messageText.value="";
            }
            function close(){
                websocket.close();
            }


websocket.onerror = function(evt){
   onError(evt); 
};

function onError(evt){
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
};
