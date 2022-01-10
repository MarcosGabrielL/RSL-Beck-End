/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.controllers;

import com.softsaj.redelivre.models.Post;
import com.softsaj.redelivre.services.PostService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/posts")
public class PostController {
    
     @Autowired
    private PostService vs;
     
     //Getting POST BY ID
     @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById (@PathVariable("id") Long id) {
        Post comentario = vs.findById(id);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    } 
     
     //Getting lista de POST BY PERSON
      @GetMapping("/post/{idperson}")
    public ResponseEntity<List<Post>> getPostByIdPerson (@PathVariable("idperson") String idperson) {
         List<Post> posts = vs.findAllByIdPerson (idperson);
       return new ResponseEntity<>(posts, HttpStatus.OK);
    }
   
     //Getting lista de POST BY TIPO
      @GetMapping("/post/{tipo}")
    public ResponseEntity<List<Post>> getPostByTipo (@PathVariable("tipo") String tipo) {
         List<Post> posts = vs.findAllByTipo(tipo);
       return new ResponseEntity<>(posts, HttpStatus.OK);
    }
        
     //Getting lista de POST BY PERSON AND TIPO
      @GetMapping("/post/{idperson}/{tipo}")
    public ResponseEntity<List<Post>> getPostByTipo 
        (@PathVariable("tipo") String tipo, @PathVariable("idperson") String idperson) {
            
         List<Post> posts = vs.findAllByPersonAndTipo(tipo,idperson);
       return new ResponseEntity<>(posts, HttpStatus.OK);
    }
        
    //Add post
    @PostMapping("/post/add")
    public ResponseEntity<Post> addMovie( @RequestBody Post post) {
         
        //SALVA POST
        Post newPost = vs.addPost(post);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/post/{id}").buildAndExpand(post.getId()).toUri();
        
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
    
    //Update post por id
     @PutMapping("/update/{idpost}")
    public ResponseEntity<Post> updatePost(
            @PathVariable("idpost") Long idpost,
            @RequestBody Post post) {
        
        Post newpost = vs.findById(idpost);
        newpost.setHastags(post.getHastags());
        newpost.setLocal(post.getLocal());
        
        Post updatePost = vs.updatePost(newpost);
        
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }
    
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id) {
        vs.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
}
