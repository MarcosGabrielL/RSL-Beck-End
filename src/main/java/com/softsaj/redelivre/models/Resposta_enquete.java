/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.models;


import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "respostas_enquete")
public class Resposta_enquete {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpost;
     
    @Column(nullable = false, unique = true, length = 45)
    private String idperson;
    
    //1,2,3,4
    @Column(nullable = false)
    private String resposta;

    public Resposta_enquete(Long idpost, String idperson, String resposta) {
        this.idpost = idpost;
        this.idperson = idperson;
        this.resposta = resposta;
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

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.idpost);
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
        final Resposta_enquete other = (Resposta_enquete) obj;
        if (!Objects.equals(this.idpost, other.idpost)) {
            return false;
        }
        return true;
    }
    
    
    
}
