/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.service;

import com.project.contactfinder.entities.Usersids;
import com.project.contactfinder.fct.Util;
import com.project.contactfinder.repositories.InterfaceService;
import com.project.contactfinder.repositories.UsersidsRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Amine
 */
@Service
public class UsersidsService implements InterfaceService<Usersids>{

    
    @Autowired
    private UsersidsRepository UR;
    
    @Override
    public List<Usersids> findAll() {
        return UR.findAll();
    }

    @Override
    public boolean save(Usersids an) {
        
        try {
            UR.save(an);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    @Override
    public boolean delete(Usersids an) {
         try {
            UR.delete(an);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    
    public Usersids findById(String id) {
        return UR.findOne(id);
    }
    
     public Usersids clt(){
        HttpSession hs = Util.getSession();
        String ids = (String) hs.getAttribute("mail");
        return findById(ids);
    }
    
}
