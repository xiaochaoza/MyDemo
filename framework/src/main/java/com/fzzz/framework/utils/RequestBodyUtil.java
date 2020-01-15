package com.fzzz.framework.utils;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-15
 * update:
 */
public class RequestBodyUtil {

    public static RequestBody creat(Map<String, String> params) {
        JSONObject jsonObject = new JSONObject(params);
        String json = jsonObject.toString();
        //"类型,字节码"
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(mediaType, json);
    }
}
