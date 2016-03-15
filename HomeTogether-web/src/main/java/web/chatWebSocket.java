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
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Antonio
 */
@ServerEndpoint(value="/chatendpoint", configurator = GetHttpSessionConfigurator.class)
public class chatWebSocket {
    private Session wsSession;
    private HttpSession httpSession;
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void onMessage(String message,Session peer) throws IOException{
        //mandando il primo messaggio si prende l'username dalla sessione altrimenti inseriso in sessione il primo messaggio come username
        String username=(String) peer.getUserProperties().get("username");
        String messageText= message.split(":")[0];
        Long receiveId= Long.parseLong(message.split(":")[1]);
        Long id= (Long) peer.getUserProperties().get("id");
        if(username==null){
            peer.getUserProperties().put("username", httpSession.getAttribute("nome"));
           // peer.getBasicRemote().sendText(buildJsonData("System","you connected as "+ peer.getUserProperties()));
        }else{
            Iterator<Session> iterator= peers.iterator();
            Session s;
            while (iterator.hasNext()) {
                s= iterator.next();
                Long it=(Long)s.getUserProperties().get("id");
                if(it.equals(receiveId) || it.equals(id) ){
                    
                    s.getBasicRemote().sendText(buildJsonData(username,messageText,id));
                }
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.wsSession = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        System.out.println(httpSession.getAttribute("nome"));
        wsSession.getUserProperties().put("username", httpSession.getAttribute("nome"));
        wsSession.getUserProperties().put("id", httpSession.getAttribute("id"));
        peers.add(wsSession); 
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }
    private String buildJsonData(String username,String message,Long id){
        JsonObject jsonObject= Json.createObjectBuilder().add("message",username+":"+message+":"+id).build();
        StringWriter stringWriter = new StringWriter();
        try(JsonWriter jsonWriter= Json.createWriter(stringWriter)){jsonWriter.write(jsonObject);}
       return stringWriter.toString(); 
    }
    
}
