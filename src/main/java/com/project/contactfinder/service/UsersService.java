/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.service;

import com.project.contactfinder.entities.Users;
import com.project.contactfinder.repositories.InterfaceService;
import com.project.contactfinder.repositories.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Amine
 */
@Service
public class UsersService implements InterfaceService<Users>{
    
    
     @Autowired
    private UsersRepository AR;
    
    
    public List<Users> findAll(){
        return AR.findAll();
    }
    
    public boolean save(Users an){
        try {
            AR.save(an);
        } catch (Exception e) {
           e.printStackTrace();
           return false;
        }
          return true;
    }
    
      public boolean delete(Users an){
        try {
            AR.delete(an);
        } catch (Exception e) {
           e.printStackTrace();
           return false;
        }
          return true;
    }

    
    public Users findById(String id) {
            return AR.findOne(id);
    }
    
    
}
