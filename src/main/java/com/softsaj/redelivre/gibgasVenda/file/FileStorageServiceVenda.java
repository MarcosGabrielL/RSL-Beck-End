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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageServiceVenda {

  @Autowired
  private FileDBRepositoryVenda fileDBRepository;

  public FileVenda store(MultipartFile file, String idpost, String idvendedor) throws IOException {
      
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileVenda FileDB = new FileVenda();
    FileDB.setData(file.getBytes());
    FileDB.setIdpost(idpost);
    FileDB.setIdvendedor(idvendedor);
    FileDB.setName(fileName);
    FileDB.setType(file.getContentType());

    return fileDBRepository.save(FileDB);
  }
  
  
   public FileVenda storeupdate(MultipartFile file, String idpost, String idvendedor, Long id) throws IOException {
      
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileVenda FileDB = new FileVenda();
    FileDB.setId(id);
    FileDB.setData(file.getBytes());
    FileDB.setIdpost(idpost);
    FileDB.setIdvendedor(idvendedor);
    FileDB.setName(fileName);
    FileDB.setType(file.getContentType());

    return fileDBRepository.save(FileDB);
  }

  public FileVenda getFile(Long id) {
    return fileDBRepository.findById(id).get();
  }
  
  public List<FileVenda> findByIdProduto(String id) {
    //return fileDBRepository.findByPost(id);
	  return new ArrayList<FileVenda>();
  }
  
  /*public List<FileVenda> findByIdVendedor(String id) {
    return fileDBRepository.findByIdVendedor(id);
  }*/
  
  public Stream<FileVenda> getAllFiles() {
    return fileDBRepository.findAll().stream();
  }
}
