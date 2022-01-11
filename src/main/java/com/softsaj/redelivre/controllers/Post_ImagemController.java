/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.controllers;

import com.softsaj.redelivre.Util.HoraServidor;
import com.softsaj.redelivre.file.FileDB;
import com.softsaj.redelivre.file.FileStorageService;
import com.softsaj.redelivre.file.ResponseMessage;
import com.softsaj.redelivre.models.Post;
import com.softsaj.redelivre.models.Post_Imagem;
import com.softsaj.redelivre.services.PostService;
import com.softsaj.redelivre.services.Post_ImagemService;
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
@RequestMapping("/imagens")
public class Post_ImagemController {
    
     @Autowired
    private Post_ImagemService vs;
     
      @Autowired
    private PostService vp;
     
  @Autowired
  private FileStorageService storageService;
  
     //Getting TEXTAO BY ID
     @GetMapping("/imagem/{idpost}")
    public ResponseEntity<Post_Imagem> getPost_ImagemById (@PathVariable("idpost") Long idpost) {
        Post_Imagem comentario = vs.findPost_ImagemById(idpost);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    } 
     
     //Getting lista de TEXTAO BY PERSON
      @GetMapping("/imagemperson/{idperson}")
    public ResponseEntity<List<Post_Imagem>> getPost_ImagemByIdPerson (@PathVariable("idperson") String idperson) {
         List<Post_Imagem> posts = vs.findAllByIdPerson(idperson);
       return new ResponseEntity<>(posts, HttpStatus.OK);
    }
   
        
    //Add post Texto
    @PostMapping("/imagem/add")
    public ResponseEntity<Post_Imagem> addMovie( 
            @RequestBody Post_Imagem post_imagem,
                    @RequestParam("file") MultipartFile file) {
        
    String message = "";
    Post_Imagem newPost_Imagem = new Post_Imagem();
    try {
      FileDB filedb = storageService.store(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      
        //SALVA POST_ GERA ID
        post_imagem.setHora(HoraServidor.HoraServidor());
        newPost_Imagem = vs.addPost_Imagem(post_imagem);
        newPost_Imagem.setIdimagem(filedb.getId().toString());
        
        //Salva POST COM ID 
        Post post = new Post();
        post.setEmail(post_imagem.getEmail());
        post.setHastags(post_imagem.getHastags());
        post.setHora(HoraServidor.HoraServidor());
        post.setId(newPost_Imagem.getId());
        post.setLocal(post_imagem.getLocal());
        post.setTipo(post_imagem.getTipo());
        post.setIdperson(newPost_Imagem.getIdperson());
        Post newPost = vp.addPost(post);
        
         URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/post/{id}").buildAndExpand(post_imagem.getId()).toUri();
         
         return new ResponseEntity<>(newPost_Imagem, HttpStatus.CREATED);
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "! "+e.getLocalizedMessage();
      return new ResponseEntity<>(newPost_Imagem, HttpStatus.EXPECTATION_FAILED);
    }
          
    }
    
    //Update post por id
     @PutMapping("/update/{id}")
    public ResponseEntity<Post_Imagem> updatePost_Imagem(
            @PathVariable("id") Long id,
            @RequestBody Post_Imagem post_imagem) {
        
        //UPDATE TEXTAO
        Post_Imagem newimagem = vs.findPost_ImagemById(id);
        newimagem.setHastags(post_imagem.getHastags());
        newimagem.setLocal(post_imagem.getLocal());
        newimagem.setTexto(post_imagem.getTexto());
        Post_Imagem updatePost_Imagem = vs.updatePost_Imagem(newimagem);
        
        //UPDATE POST
        Post post = vp.findById(id);
        post.setHastags(post_imagem.getHastags());
        post.setLocal(post_imagem.getLocal());
        Post updatePost = vp.updatePost(post);
        
        return new ResponseEntity<>(updatePost_Imagem, HttpStatus.OK);
    }
    
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost_Imagem(@PathVariable("id") Long id) {
        vs.deletePost_Imagem(id);
        vp.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
 
    
}
