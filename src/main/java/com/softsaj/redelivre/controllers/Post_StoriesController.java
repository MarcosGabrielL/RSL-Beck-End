/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.controllers;

import com.softsaj.redelivre.Util.HoraServidor;
import com.softsaj.redelivre.file.FileDB;
import com.softsaj.redelivre.file.FileStorageService;
import com.softsaj.redelivre.models.Post;
import com.softsaj.redelivre.models.Post_Stories;
import com.softsaj.redelivre.services.PostService;
import com.softsaj.redelivre.services.Post_StoriesService;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/stories")
public class Post_StoriesController {
    
     @Autowired
    private Post_StoriesService vs;
     
      @Autowired
    private PostService vp;
  @Autowired
  private FileStorageService storageService;
     
     //Getting TEXTAO BY ID
     @GetMapping("/storie/{idpost}")
    public ResponseEntity<Post_Stories> getPost_StoriesById (@PathVariable("idpost") Long idpost) {
        Post_Stories comentario = vs.findPost_StoriesById(idpost);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    } 
     
     //Getting lista de TEXTAO BY PERSON
      @GetMapping("/storieperson/{idperson}")
    public ResponseEntity<List<Post_Stories>> getPost_StoriesByIdPerson (@PathVariable("idperson") String idperson) {
         List<Post_Stories> posts = vs.findAllByIdPerson(idperson);
       return new ResponseEntity<>(posts, HttpStatus.OK);
    }
   
        
     @PostMapping("/storie/add")
    public ResponseEntity<Post_Stories> addMovie( 
            @RequestBody Post_Stories post_imagem,
                    @RequestParam("file") MultipartFile file) {
        
    String message = "";
    Post_Stories newPost_Stories = new Post_Stories();
    try {
      FileDB filedb = storageService.store(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      
        //SALVA POST_ GERA ID
        post_imagem.setHora(HoraServidor.HoraServidor());
        newPost_Stories = vs.addPost_Stories(post_imagem);
        newPost_Stories.setIdimagem(filedb.getId().toString());
        
        //Salva POST COM ID 
        Post post = new Post();
        post.setEmail(post_imagem.getEmail());
        post.setHastags(post_imagem.getHastags());
        post.setHora(HoraServidor.HoraServidor());
        post.setId(newPost_Stories.getId());
        post.setLocal(post_imagem.getLocal());
        post.setTipo(post_imagem.getTipo());
        post.setIdperson(newPost_Stories.getIdperson());
        Post newPost = vp.addPost(post);
        
         URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/post/{id}").buildAndExpand(post_imagem.getId()).toUri();
         
         return new ResponseEntity<>(newPost_Stories, HttpStatus.CREATED);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "! "+e.getLocalizedMessage();
      return new ResponseEntity<>(newPost_Stories, HttpStatus.EXPECTATION_FAILED);
    }
          
    }
    
    
    //Update post por id
     @PutMapping("/update/{id}")
    public ResponseEntity<Post_Stories> updatePost_Stories(
            @PathVariable("id") Long id,
            @RequestBody Post_Stories post_textao) {
        
        //UPDATE TEXTAO
        Post_Stories newtextao = vs.findPost_StoriesById(id);
        newtextao.setHastags(post_textao.getHastags());
        newtextao.setLocal(post_textao.getLocal());
        newtextao.setTexto(post_textao.getTexto());
        Post_Stories updatePost_Stories = vs.updatePost_Stories(newtextao);
        
        //UPDATE POST
        Post post = vp.findById(id);
        post.setHastags(post_textao.getHastags());
        post.setLocal(post_textao.getLocal());
        Post updatePost = vp.updatePost(post);
        
        return new ResponseEntity<>(updatePost_Stories, HttpStatus.OK);
    }
    
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost_Stories(@PathVariable("id") Long id) {
        vs.deletePost_Stories(id);
        vp.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
}

