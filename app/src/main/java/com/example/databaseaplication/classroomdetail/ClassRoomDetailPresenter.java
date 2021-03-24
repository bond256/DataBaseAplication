package com.example.databaseaplication.classroomdetail;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.databaseaplication.model.StudentModel;
import com.example.databaseaplication.repositoty.DetailRepository;

public class ClassRoomDetailPresenter implements ClassRoomDetailContract.ClassDetailPresenter{
    private ClassRoomDetailContract.View view;
    private DetailRepository detailRepository;
    private Handler handler;


    public ClassRoomDetailPresenter(ClassRoomDetailContract.View view, Context context) {
        this.view=view;
        this.detailRepository=new DetailRepository(context);
        this.handler=new Handler(Looper.getMainLooper());
    }

    @Override
    public void loadDetails(int id) {
        new Thread(()-> handler.post(()->{
            view.showDetails(detailRepository.getDetails(id));
        })).start();
    }

    @Override
    public void loadStudents(int id) {
        new Thread(()-> handler.post(()->{
            view.showStudent(detailRepository.getStudents(id));
        })).start();
    }

    @Override
    public void addStudent(StudentModel student) {
        new Thread(()-> handler.post(()->{
            detailRepository.addStudent(student);
        })).start();
    }

    @Override
    public void deleteStudent(int id) {
        new Thread(()-> handler.post(()->{
            detailRepository.deleteStudent(id);
        })).start();
    }

    @Override
    public void editStudent(StudentModel studentModel) {
        new Thread(()-> handler.post(()->{
            detailRepository.editStudent(studentModel);
        })).start();
    }
}
