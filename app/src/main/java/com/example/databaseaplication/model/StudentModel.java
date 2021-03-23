package com.example.databaseaplication.model;

public class StudentModel {
    private Integer id;
    private String firstName;
    private String secondName;
    private Integer classId;
    private String gender;
    private Integer age;

    public StudentModel(Integer id, String firstName, String secondName, Integer classId, String gender, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.classId = classId;
        this.gender = gender;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
