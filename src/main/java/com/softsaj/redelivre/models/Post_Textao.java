/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.models;

/**
 *
 * @author Marcos
 */

import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "textoes")
public class Post_Textao {
    
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpost;
     
    @Column(nullable = false, unique = true, length = 45)
    private String idperson;
     
    @Column(nullable = false)
    private String texto;

    public Post_Textao(Long idpost, String idperson, String texto) {
        this.idpost = idpost;
        this.idperson = idperson;
        this.texto = texto;
    }

    public Long getIdpost() {
        return idpost;
    }

    public void setIdpost(Long idpost) {
        this.idpost = idpost;
    }

    public String getIdperson() {
        return idperson;
    }

    public void setIdperson(String idperson) {
        this.idperson = idperson;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idpost);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post_Textao other = (Post_Textao) obj;
        if (!Objects.equals(this.idpost, other.idpost)) {
            return false;
        }
        return true;
    }
     
    
}
