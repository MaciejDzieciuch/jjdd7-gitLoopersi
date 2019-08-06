package com.infoshareacademy.jjdd7;

import java.util.Date;


public class Holiday {

    private String name;
    private Date date;
    private String type;
    private String description;

    public Holiday() {
    }

    public Holiday(String name, Date date, String type, String description) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
