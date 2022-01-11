/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.repositories;

import com.softsaj.redelivre.models.Post_Stories;
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
public interface Post_StoriesRepository extends JpaRepository<Post_Stories, Long> {
    
     Optional<Post_Stories> findPost_StoriesById(Long id);
     
      @Query("SELECT u FROM Post_Stories u WHERE u.idperson = ?1")
      List<Post_Stories> findAllByIdPerson(String idperson);
     
     void deletePost_StoriesById(Long id);
}
