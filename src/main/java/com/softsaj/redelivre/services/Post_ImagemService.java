/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.services;

import com.softsaj.redelivre.exception.UserNotFoundException;
import com.softsaj.redelivre.models.Post_Imagem;
import com.softsaj.redelivre.repositories.Post_ImagemRepository;
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
public class Post_ImagemService {
    
       @Autowired
    private Post_ImagemRepository rp;
      
      public List<Post_Imagem> findAll() {
        return rp.findAll();
    }
    
    //Get all post_texto by person
    public List<Post_Imagem> findAllByIdPerson(String id) {
        return rp.findAllByIdPerson(id);
    }
      
     
     public Post_Imagem findPost_ImagemById(Long id) {
        return rp.findPost_ImagemById(id)
                .orElseThrow(() -> new UserNotFoundException("Post_Imagem by id " + id + " was not found"));
    }
     
     public Post_Imagem addPost_Imagem(Post_Imagem cinefilo) {
        return rp.save(cinefilo);
    }
     
      public Post_Imagem updatePost_Imagem(Post_Imagem cinefilo) {
        return rp.save(cinefilo);
    }
      
      public void deletePost_Imagem(Long id){
        try{
          rp.deletePost_ImagemById(id);  
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    "Não foi possivel deletar o Post_Imagem");
        }
    }
}
