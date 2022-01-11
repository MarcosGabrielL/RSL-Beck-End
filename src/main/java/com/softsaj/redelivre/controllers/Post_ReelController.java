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
import com.softsaj.redelivre.models.Post_Reel;
import com.softsaj.redelivre.services.PostService;
import com.softsaj.redelivre.services.Post_ReelService;
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
@RequestMapping("/reels")
public class Post_ReelController {
    
     @Autowired
    private Post_ReelService vs;
     
      @Autowired
    private PostService vp;
  @Autowired
  private FileStorageService storageService;
     
     //Getting TEXTAO BY ID
     @GetMapping("/reel/{idpost}")
    public ResponseEntity<Post_Reel> getPost_ReelById (@PathVariable("idpost") Long idpost) {
        Post_Reel comentario = vs.findPost_ReelById(idpost);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    } 
     
     //Getting lista de TEXTAO BY PERSON
      @GetMapping("/reelperson/{idperson}")
    public ResponseEntity<List<Post_Reel>> getPost_ReelByIdPerson (@PathVariable("idperson") String idperson) {
         List<Post_Reel> posts = vs.findAllByIdPerson(idperson);
       return new ResponseEntity<>(posts, HttpStatus.OK);
    }
   
        
    //Add post Texto
   @PostMapping("/reel/add")
    public ResponseEntity<Post_Reel> addMovie( 
            @RequestBody Post_Reel post_imagem,
                    @RequestParam("file") MultipartFile file) {
        
    String message = "";
    Post_Reel newPost_Reel = new Post_Reel();
    try {
      FileDB filedb = storageService.store(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      
        //SALVA POST_ GERA ID
        post_imagem.setHora(HoraServidor.HoraServidor());
        newPost_Reel = vs.addPost_Reel(post_imagem);
        newPost_Reel.setIdimagem(filedb.getId().toString());
        
        //Salva POST COM ID 
        Post post = new Post();
        post.setEmail(post_imagem.getEmail());
        post.setHastags(post_imagem.getHastags());
        post.setHora(HoraServidor.HoraServidor());
        post.setId(newPost_Reel.getId());
        post.setLocal(post_imagem.getLocal());
        post.setTipo(post_imagem.getTipo());
        post.setIdperson(newPost_Reel.getIdperson());
        Post newPost = vp.addPost(post);
        
         URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/post/{id}").buildAndExpand(post_imagem.getId()).toUri();
         
         return new ResponseEntity<>(newPost_Reel, HttpStatus.CREATED);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "! "+e.getLocalizedMessage();
      return new ResponseEntity<>(newPost_Reel, HttpStatus.EXPECTATION_FAILED);
    }
          
    }
    
    //Update post por id
     @PutMapping("/update/{id}")
    public ResponseEntity<Post_Reel> updatePost_Reel(
            @PathVariable("id") Long id,
            @RequestBody Post_Reel post_textao) {
        
        //UPDATE TEXTAO
        Post_Reel newtextao = vs.findPost_ReelById(id);
        newtextao.setHastags(post_textao.getHastags());
        newtextao.setLocal(post_textao.getLocal());
        newtextao.setTexto(post_textao.getTexto());
        Post_Reel updatePost_Reel = vs.updatePost_Reel(newtextao);
        
        //UPDATE POST
        Post post = vp.findById(id);
        post.setHastags(post_textao.getHastags());
        post.setLocal(post_textao.getLocal());
        Post updatePost = vp.updatePost(post);
        
        return new ResponseEntity<>(updatePost_Reel, HttpStatus.OK);
    }
    
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost_Reel(@PathVariable("id") Long id) {
        vs.deletePost_Reel(id);
        vp.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
}
