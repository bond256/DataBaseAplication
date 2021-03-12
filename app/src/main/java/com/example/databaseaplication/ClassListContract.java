package com.example.databaseaplication;

public interface ClassListContract {
    interface View{
        void showList();
        void showError();
    }

    interface ClassListPresenter{
        void loadClass();
        void addClass();
    }
}
