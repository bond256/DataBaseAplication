package com.example.databaseaplication.classRoom;

import com.example.databaseaplication.model.ClassRoomModel;

import java.util.List;

public interface ClassRoomListContract {
    interface View {
        void showList(List<ClassRoomModel> classRooms);

        void showError(String message);
    }

    interface ClassListPresenter {
        void loadClass();

        void addClass(ClassRoomModel classRoom);

        void deleteClassRoom(int id);

        void editClassRoom(ClassRoomModel classRoomModel);

        void loadClassRoomsByName(String name);
    }
}
