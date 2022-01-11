/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.services;

import com.softsaj.redelivre.exception.UserNotFoundException;
import com.softsaj.redelivre.models.Post_Reel;
import com.softsaj.redelivre.repositories.Post_ReelRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author Marcos
 */
  @Service
public class Post_ReelService {
      
      @Autowired
    private Post_ReelRepository rp;
      
      public List<Post_Reel> findAll() {
        return rp.findAll();
    }
    
    //Get all post_texto by person
    public List<Post_Reel> findAllByIdPerson(String id) {
        return rp.findAllByIdPerson(id);
    }
      
     
     public Post_Reel findPost_ReelById(Long id) {
        return rp.findPost_ReelById(id)
                .orElseThrow(() -> new UserNotFoundException("Post_Reel by id " + id + " was not found"));
    }
     
     public Post_Reel addPost_Reel(Post_Reel cinefilo) {
        return rp.save(cinefilo);
    }
     
      public Post_Reel updatePost_Reel(Post_Reel cinefilo) {
        return rp.save(cinefilo);
    }
      
      public void deletePost_Reel(Long id){
        try{
          rp.deletePost_ReelById(id);  
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    "Não foi possivel deletar o Post_Reel");
        }
    }
    
}
