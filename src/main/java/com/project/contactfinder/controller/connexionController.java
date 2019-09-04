/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.controller;

import com.project.contactfinder.entities.Usersids;
import com.project.contactfinder.fct.Messages;
import com.project.contactfinder.fct.Util;
import com.project.contactfinder.fct.codification;
import com.project.contactfinder.service.UsersidsService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amine
 */
@ManagedBean
@SessionScoped
public class connexionController {
    
    private String mail;
    private String psw;

    
 @ManagedProperty("#{usersidsService}")
	private UsersidsService idSrv;
    
    private boolean verifClt(String mail, String psw){
        
        Usersids uid = idSrv.findById(new codification().cd_prs(mail));
            if(uid != null)
              return uid.getPsw().equals(new codification().md5(psw));
            else
              return false;
    }
    
    public String doLogin(){
       FacesContext context = FacesContext.getCurrentInstance();
  
     if(!verifClt(mail, psw)){
           new Messages().message(1, "Veuillez vérifier les informations d'identification svp !!", "");
        }else {
          HttpSession hs = Util.getSession();
            hs.setAttribute("mail", mail);
            hs.setAttribute("psw", psw);
            return "pages/accueil.xhtml";
        }
            return "";         
    }
    
     public String doLogout(){
            HttpSession hs = Util.getSession();
            hs.invalidate();
            return "/application/connexion.xhtml?faces-redirect=true";
    }

    
  //-----------------------------------------  
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public UsersidsService getIdSrv() {
        return idSrv;
    }

    public void setIdSrv(UsersidsService idSrv) {
        this.idSrv = idSrv;
    }
    
    
    
    
}
