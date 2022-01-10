/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.services;

import com.softsaj.redelivre.exception.UserNotFoundException;
import com.softsaj.redelivre.models.Post_Textao;
import com.softsaj.redelivre.repositories.Post_TextaoRepository;
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
public class post_TextaoService {
      
      @Autowired
    private Post_TextaoRepository rp;
      
      public List<Post_Textao> findAll() {
        return rp.findAll();
    }
    
    //Get all post_texto by person
    public List<Post_Textao> findAllByIdPerson(String id) {
        return rp.findAllByIdPerson(id);
    }
      
     
     public Post_Textao findPost_TextaoById(Long id) {
        return rp.findPost_TextaoById(id)
                .orElseThrow(() -> new UserNotFoundException("Post_Textao by id " + id + " was not found"));
    }
     
     public Post_Textao addPost_Textao(Post_Textao cinefilo) {
        return rp.save(cinefilo);
    }
     
      public Post_Textao updatePost_Textao(Post_Textao cinefilo) {
        return rp.save(cinefilo);
    }
      
      public void deletePost_Textao(Long id){
        try{
          rp.deletePost_TextaoById(id);  
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    "Não foi possivel deletar o Post_Textao");
        }
    }
    
}
