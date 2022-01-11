/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.repositories;

import com.softsaj.redelivre.models.Post_Imagem;
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
public interface Post_ImagemRepository extends JpaRepository<Post_Imagem, Long> {
    
     Optional<Post_Imagem> findPost_ImagemById(Long id);
     
      @Query("SELECT u FROM Post_Imagem u WHERE u.idperson = ?1")
      List<Post_Imagem> findAllByIdPerson(String idperson);
     
     void deletePost_ImagemById(Long id);
}

