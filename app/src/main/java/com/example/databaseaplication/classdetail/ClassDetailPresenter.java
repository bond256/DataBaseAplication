package com.example.databaseaplication.classdetail;

import android.content.Context;

public class ClassDetailPresenter implements ClassDetailContract.ClassDetailPresenter{
    private ClassDetailContract.View view;


    public ClassDetailPresenter(ClassDetailContract.View view, Context context) {
        this.view=view;
    }

    @Override
    public void loadDetails() {

    }
}
