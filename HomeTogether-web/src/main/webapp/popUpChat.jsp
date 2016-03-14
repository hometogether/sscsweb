<%-- 
    Document   : popUpChat
    Created on : 13-mar-2016, 17.35.40
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script src="js/jquery.ns-autogrow.min.js"></script>
    <script>
        $(function(){
            $('.postArea').css('overflow', 'hidden').autogrow({vertical: true, horizontal: false});
          });
    </script>
    <div class="popup-box chat-popup qnimate" id="qnimate">
            <div class="popup-head">
                <div class="popup-head-left pull-left">
                    <img src="${profilo.foto_profilo}" alt="profile_img">
                        <a>${profilo.nome}</a>
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

                        <button data-widget="remove" id="removeClass" class=" removeClass chat-header-button pull-right" type="button"><i class="glyphicon glyphicon-off"></i></button>
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
                <textarea id="status_message" placeholder="Type a message..." name="message"></textarea>
                <div class="btn-footer">
                    <button class="bg_none"><i class="glyphicon glyphicon-film"></i> </button>
                    <button class="bg_none"><i class="glyphicon glyphicon-camera"></i> </button>
                    <button class="bg_none"><i class="glyphicon glyphicon-paperclip"></i> </button>
                    <button class="bg_none pull-right" onclick="sendMessage();"><i class="glyphicon glyphicon-thumbs-up"></i> </button>
                </div>
            </div>
	  </div>
</html>
