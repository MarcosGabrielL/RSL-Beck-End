/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.gibgasVenda.file;

/**
 *
 * @author Marcos
 */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface FileDBRepositoryVenda extends JpaRepository<FileVenda, Long> {
    
     //@Query("SELECT u FROM files_venda u WHERE u.idpost = ?1")
     //public  List<FileVenda> findByPost(String idpost);
     
    // @Query("SELECT u FROM files_venda u WHERE u.idvendedor = ?1")
   //  public  List<FileVenda> findByIdVendedor(String idvendedor);

}

