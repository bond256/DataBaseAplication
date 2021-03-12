package com.example.databaseaplication.Model;

public class ClassRoomModel {
    private Integer id;
    private String name;
    private String typeOfClass;
    private Integer numberRoom;
    private Integer level;

    public ClassRoomModel(Integer id, String name, String typeOfClass, Integer numberRoom, Integer level) {
        this.id = id;
        this.name = name;
        this.typeOfClass = typeOfClass;
        this.numberRoom = numberRoom;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfClass() {
        return typeOfClass;
    }

    public void setTypeOfClass(String typeOfClass) {
        this.typeOfClass = typeOfClass;
    }

    public Integer getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(Integer numberRoom) {
        this.numberRoom = numberRoom;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
