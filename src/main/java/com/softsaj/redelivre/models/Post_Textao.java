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
    private Long id;
     
    @Column(nullable = false, unique = false, length = 45)
    private String idperson;
     
    @Column(nullable = false)
    private String texto;

    @Column(nullable = false, unique = false, length = 45)
    private String email;
     
    /*1 - Texto
      2 - Imagem
      3 - Video
      4 - Enquete
      5 - Stories
      6 - Reel*/
    @Column(nullable = false, length = 1)
    private String tipo;
     
    @Column(name = "hora", nullable = false, length = 20)
    private String hora;
     
    @Column(name = "hastags", nullable = false)
    private String hastags;
    
    @Column(name = "local", nullable = false)
    private String local;

     public Post_Textao() {
        super();
    }
    
    public Post_Textao(Long id, String idperson, String texto, String email, String tipo, String hora, String hastags, String local) {
        this.id = id;
        this.idperson = idperson;
        this.texto = texto;
        this.email = email;
        this.tipo = tipo;
        this.hora = hora;
        this.hastags = hastags;
        this.local = local;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHastags() {
        return hastags;
    }

    public void setHastags(String hastags) {
        this.hastags = hastags;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
