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
    private Long id;
     
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
    
     @Column(nullable = false, unique = true, length = 45)
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

    public Post_Enquete(Long id, String idperson, String Pergunta, String Resposta1, String Resposta2, String Resposta3, String Resposta4, String total_resposta, String email, String tipo, String hora, String hastags, String local) {
        this.id = id;
        this.idperson = idperson;
        this.Pergunta = Pergunta;
        this.Resposta1 = Resposta1;
        this.Resposta2 = Resposta2;
        this.Resposta3 = Resposta3;
        this.Resposta4 = Resposta4;
        this.total_resposta = total_resposta;
        this.email = email;
        this.tipo = tipo;
        this.hora = hora;
        this.hastags = hastags;
        this.local = local;
    }

    public Post_Enquete() {
        super();
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

   
}
