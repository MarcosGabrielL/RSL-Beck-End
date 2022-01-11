/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.services;

import com.softsaj.redelivre.exception.UserNotFoundException;
import com.softsaj.redelivre.models.Post_Stories;
import com.softsaj.redelivre.repositories.Post_StoriesRepository;
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
public class Post_StoriesService {
      
      @Autowired
    private Post_StoriesRepository rp;
      
      public List<Post_Stories> findAll() {
        return rp.findAll();
    }
    
    //Get all post_texto by person
    public List<Post_Stories> findAllByIdPerson(String id) {
        return rp.findAllByIdPerson(id);
    }
      
     
     public Post_Stories findPost_StoriesById(Long id) {
        return rp.findPost_StoriesById(id)
                .orElseThrow(() -> new UserNotFoundException("Post_Stories by id " + id + " was not found"));
    }
     
     public Post_Stories addPost_Stories(Post_Stories cinefilo) {
        return rp.save(cinefilo);
    }
     
      public Post_Stories updatePost_Stories(Post_Stories cinefilo) {
        return rp.save(cinefilo);
    }
      
      public void deletePost_Stories(Long id){
        try{
          rp.deletePost_StoriesById(id);  
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    "Não foi possivel deletar o Post_Stories");
        }
    }
    
}
