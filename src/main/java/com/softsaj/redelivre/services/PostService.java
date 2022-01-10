/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.services;

import com.softsaj.redelivre.exception.UserNotFoundException;
import com.softsaj.redelivre.models.Post;
import com.softsaj.redelivre.repositories.PostRepository;
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
public class PostService {
      
    @Autowired
    private PostRepository rp;
    
    public List<Post> findAll() {
        return rp.findAll();
    }
    
    //Get all post by person
    public List<Post> findAllByIdPerson(String id) {
        return rp.findAllByIdPerson(id);
    }
    
    //Get all post by tipo
    public List<Post> findAllByTipo(String tipo) {
        return rp.findAllByTipo(tipo);
    }
    
    //Get all post by tipo and person
    public List<Post> findAllByPersonAndTipo(String tipo, String person) {
        return rp.findAllByPersonAndTipo(tipo, person);
    }
     
     public Post findById(Long id) {
        return rp.findPostById(id)
                .orElseThrow(() -> new UserNotFoundException("Post by id " + id + " was not found"));
    }
     
     public Post addPost(Post post) {
        return rp.save(post);
    }
     
      public Post updatePost(Post cinefilo) {
        return rp.save(cinefilo);
    }
      
      public void deletePost(Long id){
        try{
          rp.deletePostById(id);  
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    "Não foi possivel deletar o Post");
        }
    }
}
