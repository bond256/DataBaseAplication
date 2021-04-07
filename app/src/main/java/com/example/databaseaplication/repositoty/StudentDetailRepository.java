package com.example.databaseaplication.repositoty;

import android.content.Context;

import com.example.databaseaplication.database.GetStudentDetailData;
import com.example.databaseaplication.model.MarksModel;
import com.example.databaseaplication.model.StudentModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class StudentDetailRepository {
    private GetStudentDetailData getStudentDetailData;
    private static StudentDetailRepository studentDetailRepository;

    public StudentDetailRepository(Context context) {
        this.getStudentDetailData=new GetStudentDetailData(context);
    }

    public static void initInstance(Context context) {
        if (studentDetailRepository == null) {
            studentDetailRepository = new StudentDetailRepository(context);
        }
    }

    public static StudentDetailRepository getInstance() throws Exception {
        if (studentDetailRepository == null) {
            throw new Exception("InitException");
        }
        return studentDetailRepository;
    }

    public Single<List<MarksModel>> getMarks(int id) {
        return getStudentDetailData.getMarks(id);
    }

    public Completable addMarks(MarksModel marksModel) {
       return getStudentDetailData.addMarks(marksModel);
    }

    public Single<StudentModel> getDetailStudent(int id) {
        return getStudentDetailData.getStudentDetail(id);
    }

    public Completable deleteMark(int id) {
        return getStudentDetailData.deleteMark(id);
    }

    public Completable editMark(MarksModel marksModel) {
       return getStudentDetailData.updateMark(marksModel);
    }


    public Single<List<MarksModel>> getSubjectByName(String name) {
        return getStudentDetailData.getSubjectByName(name);
    }

    public Single<List<MarksModel>> getSubjectsByMark(int mark) {
        return getStudentDetailData.getSubjectByMark(mark);
    }
}
