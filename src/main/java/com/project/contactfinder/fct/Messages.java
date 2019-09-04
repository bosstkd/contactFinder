/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.fct;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 *
 * @author administrateur
 */
public class Messages{


  
  public void message(int type, String Part1, String Part2) {
      FacesContext context = FacesContext.getCurrentInstance();
       FacesMessage.Severity TS = FacesMessage.SEVERITY_INFO;
         String TStr = "Information";
      switch (type){
         case 0: TS = FacesMessage.SEVERITY_INFO;
              TStr = "Information";
              break;
         case 1:  TS = FacesMessage.SEVERITY_WARN;
              TStr = "Attention";
              break;   
         case 2:  TS = FacesMessage.SEVERITY_ERROR;
              TStr = "Erreur";
              break;  
         default:      
             TS = FacesMessage.SEVERITY_FATAL;
             TStr = "Fatal";
      }
      
      context.addMessage(TStr, new FacesMessage(TS,Part1, Part2));
    }


  
}
