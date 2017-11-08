package com.palestra.camel.dominio;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }




}
