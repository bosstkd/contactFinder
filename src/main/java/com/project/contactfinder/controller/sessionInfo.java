/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.controller;

import com.project.contactfinder.entities.Usersids;
import com.project.contactfinder.service.UsersidsService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Amine
 */

@ManagedBean
public class sessionInfo {
    
  @ManagedProperty("#{usersidsService}")
	private UsersidsService idSrv;  
  
  protected boolean existClt(){
   Usersids clt = new Usersids();
 try {
        clt = idSrv.clt();
     } catch (Exception e) {
         return false;
     }
      return clt != null;
 }
  
   public String returnToCon() {
    // System.out.println("session exist : "+existEnt());
     if(!existClt()){
          return "/application/connexion.xhtml?faces-redirect=true";
     }else{
          return "";
     }
}
//-------------------------------------------
    public UsersidsService getIdSrv() {
        return idSrv;
    }

    public void setIdSrv(UsersidsService idSrv) {
        this.idSrv = idSrv;
    }
    
    
}
