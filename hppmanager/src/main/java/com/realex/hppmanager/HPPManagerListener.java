package com.realex.hppmanager;

public interface HPPManagerListener<T> {

    public static final String HPP_MANAGER_COMPLETED_WITH_RESULT = "hppManagerCompletedWithResult";


    void hppManagerCompletedWithResult(T t);
    void hppManagerFailedWithError(HPPError error);
    void hppManagerCancelled();

}
