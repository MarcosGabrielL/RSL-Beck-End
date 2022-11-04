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
import java.util.Objects;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files_venda")
public class FileVenda {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
    private Long id;

     @Column(name="name")
  private String name;
     
     @Column(name="idpost")
   private String idpost;
   
   @Column(nullable = true, unique = false, length = 30, name="idvendedor")
   private String idvendedor;
   @Column(name="type")
  private String type;

  @Lob
  private byte[] data;

  public FileVenda() {
  }

    public FileVenda(Long id, String name, String idpost, String idvendedor, String type, byte[] data) {
        this.id = id;
        this.name = name;
        this.idpost = idpost;
        this.idvendedor = idvendedor;
        this.type = type;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public String getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(String idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
    

}
