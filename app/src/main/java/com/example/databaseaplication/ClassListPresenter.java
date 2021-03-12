package com.example.databaseaplication;

public class ClassListPresenter implements ClassListContract.ClassListPresenter {
    private final ClassListContract.View view;
    ClassListPresenter(ClassListContract.View view){
        this.view=view;
    }
    @Override
    public void loadClass() {
        view.showList();
    }

    @Override
    public void addClass() {

    }
}
