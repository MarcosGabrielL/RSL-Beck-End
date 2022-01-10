/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.controllers;

import com.softsaj.redelivre.Util.HoraServidor;
import com.softsaj.redelivre.models.Post;
import com.softsaj.redelivre.models.Post_Textao;
import com.softsaj.redelivre.services.PostService;
import com.softsaj.redelivre.services.post_TextaoService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/textoes")
public class Post_TextaoController {
    
     @Autowired
    private post_TextaoService vs;
     
      @Autowired
    private PostService vp;
     
     //Getting TEXTAO BY ID
     @GetMapping("/textao/{idpost}")
    public ResponseEntity<Post_Textao> getPost_TextaoById (@PathVariable("idpost") Long idpost) {
        Post_Textao comentario = vs.findPost_TextaoById(idpost);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    } 
     
     //Getting lista de TEXTAO BY PERSON
      @GetMapping("/textaoperson/{idperson}")
    public ResponseEntity<List<Post_Textao>> getPost_TextaoByIdPerson (@PathVariable("idperson") String idperson) {
         List<Post_Textao> posts = vs.findAllByIdPerson(idperson);
       return new ResponseEntity<>(posts, HttpStatus.OK);
    }
   
        
    //Add post Texto
    @PostMapping("/textao/add")
    public ResponseEntity<Post_Textao> addMovie( 
            @RequestBody Post_Textao post_textao) {
        
        //SALVA POST_TEXTAO GERA ID
        post_textao.setHora(HoraServidor.HoraServidor());
        Post_Textao newPost_Textao = vs.addPost_Textao(post_textao);
        
        
        //Salva POST COM ID 
        Post post = new Post();
        post.setEmail(post_textao.getEmail());
        post.setHastags(post_textao.getHastags());
        post.setHora(HoraServidor.HoraServidor());
        post.setId(newPost_Textao.getId());
        post.setLocal(post_textao.getLocal());
        post.setTipo(post_textao.getTipo());
        post.setIdperson(newPost_Textao.getIdperson());
        Post newPost = vp.addPost(post);
         
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/post/{id}").buildAndExpand(post_textao.getId()).toUri();
        
        return new ResponseEntity<>(newPost_Textao, HttpStatus.CREATED);
    }
    
    //Update post por id
     @PutMapping("/update/{id}")
    public ResponseEntity<Post_Textao> updatePost_Textao(
            @PathVariable("id") Long id,
            @RequestBody Post_Textao post_textao) {
        
        //UPDATE TEXTAO
        Post_Textao newtextao = vs.findPost_TextaoById(id);
        newtextao.setHastags(post_textao.getHastags());
        newtextao.setLocal(post_textao.getLocal());
        newtextao.setTexto(post_textao.getTexto());
        Post_Textao updatePost_Textao = vs.updatePost_Textao(newtextao);
        
        //UPDATE POST
        Post post = vp.findById(id);
        post.setHastags(post_textao.getHastags());
        post.setLocal(post_textao.getLocal());
        Post updatePost = vp.updatePost(post);
        
        return new ResponseEntity<>(updatePost_Textao, HttpStatus.OK);
    }
    
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost_Textao(@PathVariable("id") Long id) {
        vs.deletePost_Textao(id);
        vp.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
}
