package com.server.datatype;

/**
 * Created by jp on 15.11.2015.
 */
public class Tag {

    private int id;
    private String name;

    public Tag(){

    }

    public Tag(com.server.entities.Tag tag){
        this.id = tag.getId();
        this.name = tag.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
