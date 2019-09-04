/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.controller;

import com.project.contactfinder.entities.Users;
import com.project.contactfinder.entities.Usersids;
import com.project.contactfinder.fct.Email;
import com.project.contactfinder.fct.Messages;
import com.project.contactfinder.fct.codification;
import com.project.contactfinder.fct.ipAdresse;
import com.project.contactfinder.fct.navigate;
import com.project.contactfinder.fct.str;
import com.project.contactfinder.service.UsersService;
import com.project.contactfinder.service.UsersidsService;
import java.io.IOException;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Amine
 */
@ManagedBean
@ViewScoped
public class usersController {
    
    Messages msg;
    ipAdresse ipa;
    Email em;
    
    private String entreprise;
    private String mail;
    private String phone;
    private String zone;
    
    @ManagedProperty("#{usersService}")
	private UsersService usrSrv;
    
    @ManagedProperty("#{usersidsService}")
	private UsersidsService idSrv;
    
    
     @PostConstruct
    private void Init(){
        msg = new Messages();
        ipa = new ipAdresse();
        em = new Email();
         try {
             zone = ipa.getClientZone();
             System.out.println(zone);
         } catch (IOException e) {
             e.printStackTrace();
             zone = "inconnu";
         }
        
        
    }
    
    
    public void insertUser(){
        
        
        Usersids ident = new Usersids();
        
        Users usr = new Users();
        usr.setIdUser(new codification().cd_prs(mail));
        usr.setEntreprise(new str().nameForm(entreprise));
        usr.setDates(new Date());
        usr.setMail(mail);
        usr.setPhone(phone);
        usr.setPays(zone);
                                              
        
        
        ident.setIdUser(new codification().cd_prs(mail));
        ident.setPsw(new codification().md5(new codification().cd_prs(mail)));
        
        if(usrSrv.save(usr) && idSrv.save(ident)){
           
            msg.message(0, "Insertion: \nEffectuée avec succée.\nVérifier votre boite mail.", "");
            
            String text = "Bonjour "+usr.getEntreprise()+" ,\nLe système ContactsFinder vous souhaite la bienvenu ci-dessous vos identifiant: \n"
                    +      "\tidentifiant   d'accés  : \t"+usr.getMail()+"\n"
                    +      "\tmot de passe par defaut: \t"+ident.getIdUser()+"\n\n"
                    +     "\tMeilleures salutation,\n"
                    +     "Contacts finder.";
            
            System.out.println(usr.getMail());
             em.sendMail(usr.getMail(), "Bienvenu sur ContactsFinder", text);
             
               new navigate().inSameUrl("application/connexion.xhtml");
             
        }else{
            msg.message(1, "Erreur: Insertion non effectuée !! ", "Insertion non effectuée !!");
        }
        
    }
    
    
 
    
  //-------------------------------------------

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UsersService getUsrSrv() {
        return usrSrv;
    }

    public void setUsrSrv(UsersService usrSrv) {
        this.usrSrv = usrSrv;
    }

    public UsersidsService getIdSrv() {
        return idSrv;
    }

    public void setIdSrv(UsersidsService idSrv) {
        this.idSrv = idSrv;
    }


  
}
