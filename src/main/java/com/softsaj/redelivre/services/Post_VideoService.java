/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.services;

import com.softsaj.redelivre.exception.UserNotFoundException;
import com.softsaj.redelivre.models.Post_Video;
import com.softsaj.redelivre.repositories.Post_VideoRepository;
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
public class Post_VideoService {
      
      @Autowired
    private Post_VideoRepository rp;
      
      public List<Post_Video> findAll() {
        return rp.findAll();
    }
    
    //Get all post_texto by person
    public List<Post_Video> findAllByIdPerson(String id) {
        return rp.findAllByIdPerson(id);
    }
      
     
     public Post_Video findPost_VideoById(Long id) {
        return rp.findPost_VideoById(id)
                .orElseThrow(() -> new UserNotFoundException("Post_Video by id " + id + " was not found"));
    }
     
     public Post_Video addPost_Video(Post_Video cinefilo) {
        return rp.save(cinefilo);
    }
     
      public Post_Video updatePost_Video(Post_Video cinefilo) {
        return rp.save(cinefilo);
    }
      
      public void deletePost_Video(Long id){
        try{
          rp.deletePost_VideoById(id);  
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    "Não foi possivel deletar o Post_Video");
        }
    }
    
}
