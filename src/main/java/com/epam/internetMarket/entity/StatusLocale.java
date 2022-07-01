package com.epam.internetMarket.entity;

import java.io.Serializable;

public class StatusLocale implements Serializable {
    private long id;
    private long localeId;
    private String name;
    private long statusId;

    public StatusLocale() {
    }

    public StatusLocale(long id, long localeId, String name, long statusId) {
        this.id = id;
        this.localeId = localeId;
        this.name = name;
        this.statusId = statusId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLocaleId() {
        return localeId;
    }

    public void setLocaleId(long localeId) {
        this.localeId = localeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }
}
