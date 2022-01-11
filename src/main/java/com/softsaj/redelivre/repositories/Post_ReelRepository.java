/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.repositories;

import com.softsaj.redelivre.models.Post_Reel;
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
public interface Post_ReelRepository extends JpaRepository<Post_Reel, Long> {
    
     Optional<Post_Reel> findPost_ReelById(Long id);
     
      @Query("SELECT u FROM Post_Reel u WHERE u.idperson = ?1")
      List<Post_Reel> findAllByIdPerson(String idperson);
     
     void deletePost_ReelById(Long id);
}
