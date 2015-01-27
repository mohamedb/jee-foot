/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.servelet;

import javax.servlet.http.HttpSession;

/**
 * Cette classe se charge de mettre dans la session
 * le message de la notification correspondant!
 *
 * @author Mohamed
 */
public final class Notification {
    HttpSession session;
    String message="";
    String type="success";
    public Notification(HttpSession session, String msg,String type){
        this.session=session;
        this.message=msg;
        this.type=type;
        setNotification();
        
    }
    
    public void setNotification(){
        session.setAttribute("notification", this);
    }
    
    /*
    Vider la session !
    */
    public Notification init(){
        this.message="";
        this.type="";
        this.session.setAttribute("notification", null);
        return this;
    }
    
    public HttpSession getSession() {
        return session;
    }

    public String getMessage() {
        String msg = message;
        init();
        return msg;
    }

    public String getType() {
        return type;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
