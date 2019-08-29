package com.fzzz.fragment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-23
 * update:
 */
public class MainModel extends ViewModel {
    private MutableLiveData<String> homeContent = new MutableLiveData<>();
    private MutableLiveData<String> mineContent = new MutableLiveData<>();

    public LiveData<String> getHomeContent() {
        return homeContent;
    }

    public LiveData<String> getMineContent() {
        return mineContent;
    }

    public void setHomeContent(String content) {
        homeContent.setValue(content);
    }

    public void setMineContent(String content) {
        mineContent.setValue(content);
    }


}
