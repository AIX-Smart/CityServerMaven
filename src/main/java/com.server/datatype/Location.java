package com.server.datatype;

/**
 * Created by jp on 13.11.2015.
 */
public class Location {
    private int id;
    private String name;

    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Location(com.server.entities.Location locationEntity){
        this.id = locationEntity.getId();
        this.name = locationEntity.getName();
    }
    public Location(int id){
        this.id = id;
        this.name = "GinBar";
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