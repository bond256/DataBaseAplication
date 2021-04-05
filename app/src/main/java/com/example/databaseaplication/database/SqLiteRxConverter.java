package com.example.databaseaplication.database;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

enum Errors{
    ADD,
    DELETE,
    UPDATE
}

public class SqLiteRxConverter<T> {
//    public Single<List<T>> makeSingleObservable(Class<T> class_meth) {
//        return Single.create(emitter -> {
//            class_meth.
//            emitter.onSuccess(listData);
//            if (listData.isEmpty()) emitter.onError(new Exception("Error empty list"));
//        });
//    }

    public Single<T> makeSingleObservable(T data) {
        return Single.create(emitter -> emitter.onSuccess(data));
    }

    public Completable makeSingleObservable(int code,Errors errors){
        return Completable.create(emitter -> {
            if(code>1) emitter.onComplete();
            else if(Errors.ADD==errors)emitter.onError(new Exception("AddException"));
            else if(Errors.DELETE==errors) emitter.onError(new Exception("DeleteException"));
            else if(Errors.UPDATE==errors) emitter.onError(new Exception("DeleteException"));
        });
    }
}
