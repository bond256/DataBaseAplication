package com.example.databaseaplication.classdetail;

public interface ClassDetailContract {
    interface View{
        void showDetails();
        void showError();
    }

    interface ClassDetailPresenter{
        void loadDetails();
    }
}
