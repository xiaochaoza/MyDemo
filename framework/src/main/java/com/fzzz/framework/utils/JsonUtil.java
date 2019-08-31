package com.fzzz.framework.utils;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-30
 * update:
 */
public class JsonUtil {

    public static String getStringFromFile(Context context, String fileName) {
        try {
            StringBuilder sb = new StringBuilder();
            //获取流
            InputStream is = context.getAssets().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
