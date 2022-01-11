/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.controllers;

import com.softsaj.redelivre.Util.HoraServidor;
import com.softsaj.redelivre.models.Post;
import com.softsaj.redelivre.models.Post_Enquete;
import com.softsaj.redelivre.services.PostService;
import com.softsaj.redelivre.services.Post_EnqueteService;
import com.softsaj.redelivre.services.post_TextaoService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Marcos
 */
@RestController
@RequestMapping("/enquetes")
public class Post_EnqueteController {
    
     @Autowired
    private Post_EnqueteService vs;
     
      @Autowired
    private PostService vp;
     
     //Getting TEXTAO BY ID
     @GetMapping("/enquete/{idpost}")
    public ResponseEntity<Post_Enquete> getPost_EnqueteById (@PathVariable("idpost") Long idpost) {
        Post_Enquete comentario = vs.findPost_EnqueteById(idpost);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    } 
     
     //Getting lista de TEXTAO BY PERSON
      @GetMapping("/enqueteperson/{idperson}")
    public ResponseEntity<List<Post_Enquete>> getPost_EnqueteByIdPerson (@PathVariable("idperson") String idperson) {
         List<Post_Enquete> posts = vs.findAllByIdPerson(idperson);
       return new ResponseEntity<>(posts, HttpStatus.OK);
    }
   
        
    //Add post Texto
    @PostMapping("/enquete/add")
    public ResponseEntity<Post_Enquete> addMovie( 
            @RequestBody Post_Enquete post_enquete) {
        
        //SALVA POST_TEXTAO GERA ID
        post_enquete.setHora(HoraServidor.HoraServidor());
        Post_Enquete newPost_Enquete = vs.addPost_Enquete(post_enquete);
        
        
        //Salva POST COM ID 
        Post post = new Post();
        post.setEmail(post_enquete.getEmail());
        post.setHastags(post_enquete.getHastags());
        post.setHora(HoraServidor.HoraServidor());
        post.setId(newPost_Enquete.getId());
        post.setLocal(post_enquete.getLocal());
        post.setTipo(post_enquete.getTipo());
        post.setIdperson(newPost_Enquete.getIdperson());
        Post newPost = vp.addPost(post);
         
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/post/{id}").buildAndExpand(post_enquete.getId()).toUri();
        
        return new ResponseEntity<>(newPost_Enquete, HttpStatus.CREATED);
    }
    
    //Update post por id
     @PutMapping("/update/{id}")
    public ResponseEntity<Post_Enquete> updatePost_Enquete(
            @PathVariable("id") Long id,
            @RequestBody Post_Enquete post_enquete) {
        
        //UPDATE TEXTAO
        Post_Enquete newenquete = vs.findPost_EnqueteById(id);
        newenquete.setHastags(post_enquete.getHastags());
        newenquete.setLocal(post_enquete.getLocal());
        Post_Enquete updatePost_Enquete = vs.updatePost_Enquete(newenquete);
        
        //UPDATE POST
        Post post = vp.findById(id);
        post.setHastags(post_enquete.getHastags());
        post.setLocal(post_enquete.getLocal());
        Post updatePost = vp.updatePost(post);
        
        return new ResponseEntity<>(updatePost_Enquete, HttpStatus.OK);
    }
    
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost_Enquete(@PathVariable("id") Long id) {
        vs.deletePost_Enquete(id);
        vp.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
}
