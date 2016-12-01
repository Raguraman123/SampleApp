/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.websocket.clock;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author NithyaRaghu
 */
@ServerEndpoint("/clock")
public class WebsocketClock { 
 
  static ScheduledExecutorService timer = 
       Executors.newSingleThreadScheduledExecutor(); 
 
  private static Set<Session> allSessions; 
 
  SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");

  @OnOpen  
  public void showTime(Session session){
      allSessions = session.getOpenSessions();
 
      // start the scheduler on the very first connection
      // to call sendTimeToAll every second   
      if (allSessions.size()==1){   
        timer.scheduleAtFixedRate(sendTimeToAll(session),0,3,TimeUnit.SECONDS);    
      }
     }      
 
   private Runnable sendTimeToAll(final Session session){       
     Runnable runnable = new Runnable(){
        public void run(){
           allSessions = session.getOpenSessions();
            for (Session sess: allSessions){          
               try{   
                 sess.getBasicRemote().sendText("Local time: " + sdf.format(new Date()));
                 } catch (IOException ioe) {        
                     System.out.println(ioe.getMessage());         
                 }   
            } 
        }
     };
     return runnable;
  }

}