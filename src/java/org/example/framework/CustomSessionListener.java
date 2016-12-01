/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.framework;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author NithyaRaghu
 */
public class CustomSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        System.out.println(">>>>>>>>>>>> Session Created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        System.out.println(">>>>>>>>>>>> Session Destroyed");
    }
    
}
