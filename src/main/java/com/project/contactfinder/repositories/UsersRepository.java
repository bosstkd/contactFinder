/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.contactfinder.repositories;

import com.project.contactfinder.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Amine
 */
public interface UsersRepository extends JpaRepository<Users, String>{
    
}
