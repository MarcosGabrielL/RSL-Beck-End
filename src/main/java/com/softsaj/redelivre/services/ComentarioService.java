/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.services;

import com.softsaj.redelivre.exception.UserNotFoundException;
import com.softsaj.redelivre.models.Comentario;
import com.softsaj.redelivre.repositories.ComentarioRepository;
import java.util.List;
import java.util.Optional;
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
public class ComentarioService {
      
      @Autowired
    private ComentarioRepository rp;
      
      //Getting lista de comentarios by post
     public  List<Comentario> findAllByIdPost(Long id_post) {
        return rp.findAllByIdPost(id_post);
    }
     
      //Getting lista de comentarios by person
     public  List<Comentario> findAllByIdPerson(String id_person) {
        return rp.findAllByIdPerson(id_person);
    }
     
     //Getting comentario avulso by id
     public Comentario findById(Long id) {
        return rp.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Comentario by id " + id + " was not found"));
    }
     
     public Comentario addComentario(Comentario cinefilo) {
        return rp.save(cinefilo);
    }
     
      public Comentario updateComentario(Comentario cinefilo) {
        return rp.save(cinefilo);
    }
      
      public void deleteComentario(Long id){
        try{
          rp.deleteComentarioById(id);  
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    "Não foi possivel deletar o Comentario");
        }
    }
    
}
