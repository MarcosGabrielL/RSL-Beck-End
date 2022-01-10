/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.repositories;

import com.softsaj.redelivre.models.Comentario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Marcos
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    //Getting lista de comentarios by post
     @Query("SELECT u FROM Comentario u WHERE u.idpost = ?1")
      List<Comentario> findAllByIdPost(Long idpost);
      
     @Query("SELECT u FROM Comentario u WHERE u.idperson = ?1")
      List<Comentario> findAllByIdPerson(String idperson);
    
     
     void deleteComentarioById(Long id);
}
