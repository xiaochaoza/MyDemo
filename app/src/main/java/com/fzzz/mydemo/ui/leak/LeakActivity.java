package com.fzzz.mydemo.ui.leak;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: ShenChao
 * time: 2019-07-26
 * update:
 */
@Route(path = Constants.PATH_APP_LEAK)
public class LeakActivity extends BaseActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_leak;
    }

    List<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        persons.add(new Person("zhangsan",23));
        persons.add(new Person("lisi",24));
        persons.add(new Person("wangwu",25));
    }

    private class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
