/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.repositories;

/**
 *
 * @author Marcos
 */
import com.softsaj.redelivre.models.Post_Enquete;
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
public interface Post_EnqueteRepository extends JpaRepository<Post_Enquete, Long> {
    
       Optional<Post_Enquete> findPost_EnqueteById(Long id);
     
      @Query("SELECT u FROM Post_Imagem u WHERE u.idperson = ?1")
      List<Post_Enquete> findAllByIdPerson(String idperson);
     
     void deletePost_EnqueteById(Long id);
}
