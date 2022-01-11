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
import com.softsaj.redelivre.models.Post_Video;
import com.softsaj.redelivre.services.PostService;
import com.softsaj.redelivre.services.Post_VideoService;
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
@RequestMapping("/videos")
public class Post_VideoController {
    
     @Autowired
    private Post_VideoService vs;
     
      @Autowired
    private PostService vp;
      
       @Autowired
  private FileStorageService storageService;
     
     //Getting TEXTAO BY ID
     @GetMapping("/video/{idpost}")
    public ResponseEntity<Post_Video> getPost_VideoById (@PathVariable("idpost") Long idpost) {
        Post_Video comentario = vs.findPost_VideoById(idpost);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    } 
     
     //Getting lista de TEXTAO BY PERSON
      @GetMapping("/videosperson/{idperson}")
    public ResponseEntity<List<Post_Video>> getPost_VideoByIdPerson (@PathVariable("idperson") String idperson) {
         List<Post_Video> posts = vs.findAllByIdPerson(idperson);
       return new ResponseEntity<>(posts, HttpStatus.OK);
    }
   
        
    //Add post Texto
     @PostMapping("/video/add")
    public ResponseEntity<Post_Video> addMovie( 
            @RequestBody Post_Video post_imagem,
                    @RequestParam("file") MultipartFile file) {
        
    String message = "";
    Post_Video newPost_Video = new Post_Video();
    try {
      FileDB filedb = storageService.store(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      
        //SALVA POST_ GERA ID
        post_imagem.setHora(HoraServidor.HoraServidor());
        newPost_Video = vs.addPost_Video(post_imagem);
        newPost_Video.setIdimagem(filedb.getId().toString());
        
        //Salva POST COM ID 
        Post post = new Post();
        post.setEmail(post_imagem.getEmail());
        post.setHastags(post_imagem.getHastags());
        post.setHora(HoraServidor.HoraServidor());
        post.setId(newPost_Video.getId());
        post.setLocal(post_imagem.getLocal());
        post.setTipo(post_imagem.getTipo());
        post.setIdperson(newPost_Video.getIdperson());
        Post newPost = vp.addPost(post);
        
         URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/post/{id}").buildAndExpand(post_imagem.getId()).toUri();
         
         return new ResponseEntity<>(newPost_Video, HttpStatus.CREATED);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "! "+e.getLocalizedMessage();
      return new ResponseEntity<>(newPost_Video, HttpStatus.EXPECTATION_FAILED);
    }
          
    }
    
    //Update post por id
     @PutMapping("/update/{id}")
    public ResponseEntity<Post_Video> updatePost_Video(
            @PathVariable("id") Long id,
            @RequestBody Post_Video post_textao) {
        
        //UPDATE TEXTAO
        Post_Video newtextao = vs.findPost_VideoById(id);
        newtextao.setHastags(post_textao.getHastags());
        newtextao.setLocal(post_textao.getLocal());
        newtextao.setTexto(post_textao.getTexto());
        Post_Video updatePost_Video = vs.updatePost_Video(newtextao);
        
        //UPDATE POST
        Post post = vp.findById(id);
        post.setHastags(post_textao.getHastags());
        post.setLocal(post_textao.getLocal());
        Post updatePost = vp.updatePost(post);
        
        return new ResponseEntity<>(updatePost_Video, HttpStatus.OK);
    }
    
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost_Video(@PathVariable("id") Long id) {
        vs.deletePost_Video(id);
        vp.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
}

