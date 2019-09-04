/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.service;

import com.project.contactfinder.entities.Recherche;
import com.project.contactfinder.repositories.InterfaceService;
import com.project.contactfinder.repositories.RechercheRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Amine
 */
@Service
public class RechercheService implements InterfaceService<Recherche>{

    @Autowired
    private RechercheRepository RR;
    
    @Override
    public List<Recherche> findAll() {
        return RR.findAll();
    }

    @Override
    public boolean save(Recherche an) {
        try {
            RR.save(an);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Recherche an) {
        try {
            RR.delete(an);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public Recherche findById(String id){
        return RR.findOne(id);
    }
    
}
