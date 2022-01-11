/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.services;

import com.softsaj.redelivre.exception.UserNotFoundException;
import com.softsaj.redelivre.models.Post_Enquete;
import com.softsaj.redelivre.repositories.Post_EnqueteRepository;
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
public class Post_EnqueteService {
      
      @Autowired
    private Post_EnqueteRepository rp;
      
      public List<Post_Enquete> findAll() {
        return rp.findAll();
    }
    
    //Get all post_texto by person
    public List<Post_Enquete> findAllByIdPerson(String id) {
        return rp.findAllByIdPerson(id);
    }
      
     
     public Post_Enquete findPost_EnqueteById(Long id) {
        return rp.findPost_EnqueteById(id)
                .orElseThrow(() -> new UserNotFoundException("Post_Enquete by id " + id + " was not found"));
    }
     
     public Post_Enquete addPost_Enquete(Post_Enquete cinefilo) {
        return rp.save(cinefilo);
    }
     
      public Post_Enquete updatePost_Enquete(Post_Enquete cinefilo) {
        return rp.save(cinefilo);
    }
      
      public void deletePost_Enquete(Long id){
        try{
          rp.deletePost_EnqueteById(id);  
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    "Não foi possivel deletar o Post_Enquete");
        }
    }
    
}
