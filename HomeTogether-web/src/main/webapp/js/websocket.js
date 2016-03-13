/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri="ws://" + document.location.host +"/HomeTogether-web/chatendpoint";
var websocket = new WebSocket(wsUri);
var i=0;

var output = document.getElementById("output");
websocket.onopen = function(evt) { onOpen(evt); };

function writeToScreen(message) {
    //output.innerHTML += message + "<br>";
    console.log(message);
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}
websocket.onmessage= function processMessage(message){
                var d = new Date();
                
                var month = d.getMonth()+1;
                var day = d.getDate();
                var hours=d.getHours();
                var minutes= d.getMinutes();
                var time= hours +":"+ minutes;
                var data = d.getFullYear() + '/' +
                (month<10 ? '0' : '') + month + '/' +
                (day<10 ? '0' : '') + day;
                var jsonData= JSON.parse(message.data);
                $('#qnimate').addClass('popup-box-on');
            
                console.log(jsonData.message.split(":")[0]);
                if(jsonData!==null){ //messageTextArea.value += jsonData.message+'\n';
                    var txt=jsonData.message.split(":")[1];
                    var name=jsonData.message.split(":")[0];
                    $('#messageArea').append('<div id="newelement'+i+'"class="chat-box-single-line">'+
                      '<abbr class="timestamp">'+data+'</abbr>'+
                    '</div><div class="direct-chat-info clearfix">'+
                            '<span class="direct-chat-name pull-left"><a>'+name+'</a></span>'+
                        '</div>'+
                        '<img alt="" src="" class="direct-chat-img">'+
                        '<div class="direct-chat-text">'+
                            '<p>'+txt+'</p>'+
                        '</div>'+
                        '<div class="direct-chat-info clearfix">'+
                            '<span class="direct-chat-timestamp pull-right">'+time+'</span>'+
                        '</div>');
                
                //$(".popup-messages").animate({ scrollTop: $('#newelement'+i).offset().top });
                ++i;}
            };
            function sendMessage(){
                websocket.send(status_message.value);
                //websocket.send(messageText.value);messageText.value="";
                status_message.value="";
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
