/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Antonio
 */
@ServerEndpoint("/chatendpoint")
public class chatWebSocket {

    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    @OnMessage
    public void onMessage(String message,Session peer) throws IOException{
        //mandando il primo messaggio si prende l'username dalla sessione altrimenti inseriso in sessione il primo messaggio come username
        String username=(String) peer.getUserProperties().get("username");
        if(username==null){
            peer.getUserProperties().put("username", message);
            peer.getBasicRemote().sendText(buildJsonData("System","you are not connected as "+ message));
        }else{
            Iterator<Session> iterator= peers.iterator();
            while (iterator.hasNext()) {
                //if(iterator.next().getId().equals("userId"))
                iterator.next().getBasicRemote().sendText(buildJsonData(username,message));
            }
        }
    }

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer); 
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }
    private String buildJsonData(String username,String message){
        JsonObject jsonObject= Json.createObjectBuilder().add("message",username+":"+message).build();
        StringWriter stringWriter = new StringWriter();
        try(JsonWriter jsonWriter= Json.createWriter(stringWriter)){jsonWriter.write(jsonObject);}
       return stringWriter.toString(); 
    }
    
}
