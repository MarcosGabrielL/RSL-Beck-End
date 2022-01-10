/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.repositories;


import com.softsaj.redelivre.models.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcos
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
      Optional<Post> findPostById(Long id);
      
       @Query("SELECT u FROM Post u WHERE u.idperson = ?1")
       List<Post> findAllByIdPerson(String idperson);
       
       @Query("SELECT u FROM Post u WHERE u.tipo = ?1")
       List<Post> findAllByTipo(String tipo);
       
       @Query("SELECT u FROM Post u WHERE u.tipo = ?1 and u.idperson = ?2")
       List<Post> findAllByPersonAndTipo(String tipo, String idperson);
     
     void deletePostById(Long id);
    
}
