package com.fzzz.fragment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * description:
 * author: ShenChao
 * time: 2019-07-25
 * update:
 */
public class HomeFragmentModel extends ViewModel {
    private MutableLiveData<String> content;

    public LiveData<String> getContent() {
        if (null == content) {
            content = new MutableLiveData<>();
        }
        return content;
    }

    public void setContent(String content) {
        this.content.setValue(content);
    }
}
