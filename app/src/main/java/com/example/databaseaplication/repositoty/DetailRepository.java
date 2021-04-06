package com.example.databaseaplication.repositoty;

import android.content.Context;

import com.example.databaseaplication.database.GetClassRoomDetailData;
import com.example.databaseaplication.model.ClassRoomModel;
import com.example.databaseaplication.model.StudentModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class DetailRepository {
    private GetClassRoomDetailData getClassRoomDetailData;
    private static DetailRepository detailRepository;

    public DetailRepository(Context context) {
        this.getClassRoomDetailData = new GetClassRoomDetailData(context);
    }


    public static void initInstance(Context context) {
        if (detailRepository == null) {
            detailRepository = new DetailRepository(context);
        }
    }

    public static DetailRepository getInstance() {
        return detailRepository;
    }

    public Completable addStudent(StudentModel student) {
        return getClassRoomDetailData.addNewStudent(student);
    }

    public Single<List<StudentModel>> getStudents(int id) {
        return getClassRoomDetailData.getStudents(id);
    }

    public Single<ClassRoomModel> getDetails(int id) {
        return getClassRoomDetailData.getClassRoomDetail(id);
    }

    public Completable deleteStudent(int id) {
        return getClassRoomDetailData.deleteStudent(id);
    }

    public Completable editStudent(StudentModel studentModel) {
        return getClassRoomDetailData.editStudent(studentModel);
    }

    public Single<List<StudentModel>> getStudentsByName(String name, int id) {
        return getClassRoomDetailData.getStudentByName(name, id);
    }


}
