/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.fct;

import java.io.IOException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amine
 */
public class navigate {
    
    public void toNewUrl(String url) throws IOException{
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect(url);
    }
    
    public void inSameUrl(String url){
         FacesContext fc = FacesContext.getCurrentInstance();
                NavigationHandler nh = fc.getApplication().getNavigationHandler();
                nh.handleNavigation(fc, null, "/"+url+"?faces-redirect=true");
    }
    
}
