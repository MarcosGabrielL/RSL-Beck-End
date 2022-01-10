/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.controllers;

import com.softsaj.redelivre.models.Comentario;
import com.softsaj.redelivre.services.ComentarioService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    
      @Autowired
    private ComentarioService vs;
     
     
     //Getting lista de comentarios by post
      @GetMapping("/movie/{idpost}")
    public ResponseEntity<List<Comentario>> getComentarioByIdPost (@PathVariable("idpost") Long idpost) {
         List<Comentario> comentarios = vs.findAllByIdPost(idpost);
       return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }
   
     //Getting lista de comentarios by person
      @GetMapping("/movie/{idperson}")
    public ResponseEntity<List<Comentario>> getComentarioByIdPost (@PathVariable("idperson") String idperson) {
         List<Comentario> comentarios = vs.findAllByIdPerson(idperson);
       return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }
        
    //Getting comentario by id
     @GetMapping("/comentario/{id}")
    public ResponseEntity<Comentario> getComentarioById (@PathVariable("id") Long id) {
        Comentario comentario = vs.findById(id);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }   
    
    //Add comentario em post By Person
    @PostMapping("/comentario/add/{idpost}")
    public ResponseEntity<Comentario> addMovie(
            @PathVariable("idpost") Long idpost, @RequestBody Comentario comentario) {
         
        comentario.setIdpost(idpost);
        Comentario newComentario = vs.addComentario(comentario);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/comentario/{id}").buildAndExpand(comentario.getId()).toUri();
        
        return new ResponseEntity<>(newComentario, HttpStatus.CREATED);
    }
    
    //Update comentario por id em Post por id
     @PutMapping("/comentario/post/{idpost}/update/{id}")
    public ResponseEntity<Comentario> updateComentario(
            @PathVariable("id") Long id,
            @PathVariable("idpost") String idpost,
            @RequestBody Comentario comentario) {
        
        Comentario newcomentario = vs.findById(id);
        newcomentario.setComentario(comentario.getComentario());
        
        Comentario updateComentario = vs.updateComentario(comentario);
        
        return new ResponseEntity<>(updateComentario, HttpStatus.OK);
    }
    
    @Transactional
    @DeleteMapping("/comentario/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id) {
        vs.deleteComentario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
}
