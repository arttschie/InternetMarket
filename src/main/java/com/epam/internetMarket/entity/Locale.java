package com.epam.internetMarket.entity;

import java.io.Serializable;

public class Locale implements Serializable {
    private Long id;
    private String shortName;
    private String name;

    public Locale() {
    }

    public Locale(Long id, String shortName, String name) {
        this.id = id;
        this.shortName = shortName;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
