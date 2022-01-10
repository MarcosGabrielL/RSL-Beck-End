/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.models;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "comentarios")
public class Comentario {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 45)
    private Long idpost;
    
    @Column(nullable = false)
    private String comentario;
    
    @Column(nullable = false, length = 45)
    private String idperson;
    
    @Column(nullable = false)
    private String hora;
    
    @Column(nullable = true)
    private String count_curtidas;

    public Comentario(Long id, Long idpost, String comentario, String idperson, String hora, String count_curtidas) {
        this.id = id;
        this.idpost = idpost;
        this.comentario = comentario;
        this.idperson = idperson;
        this.hora = hora;
        this.count_curtidas = count_curtidas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdpost() {
        return idpost;
    }

    public void setIdpost(Long idpost) {
        this.idpost = idpost;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getIdperson() {
        return idperson;
    }

    public void setIdperson(String idperson) {
        this.idperson = idperson;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCount_curtidas() {
        return count_curtidas;
    }

    public void setCount_curtidas(String count_curtidas) {
        this.count_curtidas = count_curtidas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Comentario other = (Comentario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    

}
