package com.palestra.camel.dominio;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonFormat
@JsonInclude(Include.NON_NULL) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class Financial implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private String account;
    private Integer score;



    public Financial(long id, String name, String account, Integer score) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.score = score;
    }

    public Financial(){}
    
    @JsonGetter("id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @JsonGetter("score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }




}
