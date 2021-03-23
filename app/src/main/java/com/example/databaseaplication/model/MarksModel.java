package com.example.databaseaplication.model;

public class MarksModel {
    private Integer id;
    private String subjectName;
    private Integer studentId;
    private Integer Mark;
    private String dataMark;


    public MarksModel(Integer id, String subjectName, Integer studentId, Integer mark, String dataMark) {
        this.id = id;
        this.subjectName = subjectName;
        this.studentId = studentId;
        Mark = mark;
        this.dataMark = dataMark;
    }

    public Integer getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getMark() {
        return Mark;
    }

    public String getDataMark() {
        return dataMark;
    }
}
