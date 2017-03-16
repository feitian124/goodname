package com.yunnuy.goodname.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChineseChar {
    @Id
    @GeneratedValue
    private Long id;
    
    public Long getId() {
        return id;
    }
}