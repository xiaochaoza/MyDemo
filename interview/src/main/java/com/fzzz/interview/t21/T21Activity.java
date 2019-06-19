package com.fzzz.interview.t21;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;

import java.util.ArrayList;

/**
 * description:
 * author: ShenChao23.png
 * time: 2019-06-18
 * update:
 */
@Route(path = Constants.PATH_INTERVIEW_T21)
public class T21Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main();
    }

    private static void addPi(ArrayList<? super Double> list) {
        list.add(Math.PI);
    }

    public void main() {
        ArrayList<Double> dList = new ArrayList<>();
        ArrayList<Number> numList = new ArrayList<>();
        addPi(dList);
        addPi(numList);
        System.out.println(dList.toString());
        System.out.println(numList.toString());
    }
}
