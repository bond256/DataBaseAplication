package com.example.databaseaplication;

import com.example.databaseaplication.Model.ClassRoomModel;

import java.util.List;

public interface ClassListContract {
    interface View{
        void showList(List<ClassRoomModel> classRooms);
        void showError();
    }

    interface ClassListPresenter{
        void loadClass();
        void addClass(ClassRoomModel classRoom);
        void deleteClassRoom(int id);
        void editClassRoom(ClassRoomModel classRoomModel);
    }
}
