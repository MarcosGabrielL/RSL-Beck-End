/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softsaj.redelivre.models;


import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "enquetes")
public class Post_Enquete {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpost;
     
    @Column(nullable = false, unique = true, length = 45)
    private String idperson;
     
    @Column(nullable = false)
    private String Pergunta;
    
    @Column(nullable = false)
    private String Resposta1;
    
    @Column(nullable = true)
    private String Resposta2;
    
    @Column(nullable = true)
    private String Resposta3;
    
    @Column(nullable = true)
    private String Resposta4;
    
    @Column(nullable = true)
    private String total_resposta;

    public Post_Enquete(Long idpost, String idperson, String Pergunta, String Resposta1, String Resposta2, String Resposta3, String Resposta4, String total_resposta) {
        this.idpost = idpost;
        this.idperson = idperson;
        this.Pergunta = Pergunta;
        this.Resposta1 = Resposta1;
        this.Resposta2 = Resposta2;
        this.Resposta3 = Resposta3;
        this.Resposta4 = Resposta4;
        this.total_resposta = total_resposta;
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

    public String getPergunta() {
        return Pergunta;
    }

    public void setPergunta(String Pergunta) {
        this.Pergunta = Pergunta;
    }

    public String getResposta1() {
        return Resposta1;
    }

    public void setResposta1(String Resposta1) {
        this.Resposta1 = Resposta1;
    }

    public String getResposta2() {
        return Resposta2;
    }

    public void setResposta2(String Resposta2) {
        this.Resposta2 = Resposta2;
    }

    public String getResposta3() {
        return Resposta3;
    }

    public void setResposta3(String Resposta3) {
        this.Resposta3 = Resposta3;
    }

    public String getResposta4() {
        return Resposta4;
    }

    public void setResposta4(String Resposta4) {
        this.Resposta4 = Resposta4;
    }

    public String getTotal_resposta() {
        return total_resposta;
    }

    public void setTotal_resposta(String total_resposta) {
        this.total_resposta = total_resposta;
    }
    
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idpost);
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
        final Post_Enquete other = (Post_Enquete) obj;
        if (!Objects.equals(this.idpost, other.idpost)) {
            return false;
        }
        return true;
    }
    
    
    
}
