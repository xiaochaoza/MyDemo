package com.fzzz.mydemo.ui.gson;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * description:
 * author: ShenChao
 * time: 2019-08-15
 * update:
 */
public class RxHelper {
    private Type clsType;

    private Flowable<Response<ResponseBody>> flowable;
    private CallBack callBack;
    private boolean isDownload = false;

    public RxHelper load(Flowable<Response<ResponseBody>> flowable) {
        this.flowable = flowable;
        return this;
    }

    public RxHelper callBack(CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public void start() {

        if (callBack != null && callBack instanceof CallBackAdapter) {
            clsType = ((CallBackAdapter) callBack).type;
        }

        startNormalRequest(flowable);
    }

    /**
     * 正常流程
     *
     * @param flowable
     */
    private void startNormalRequest(Flowable<Response<ResponseBody>> flowable) {
        flowable
                .subscribeOn(Schedulers.io())
                .map(new Function<Response<ResponseBody>, String>() {
                    @Override
                    public String apply(Response<ResponseBody> response) throws Exception {
                        Headers header = response.headers();
                        return response.body().string();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<String>() {

                    @Override
                    public void onNext(String s) {
                        invokerHandle(s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callBack.onError("9999", "网络异常");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface CallBack<D> {
        void onSuccess(List<D> resultList, D result);

        void onError(String retCode, String retMessage);
    }

    public static abstract class CallBackAdapter<D> implements CallBack<D> {
        private Type type;

        public CallBackAdapter() {
            type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }

    public void invokerHandle(String response) {

        if (!isJson(response)) {
            return;
        }

        try {

            JSONObject js = new JSONObject(response);
            String retCode = js.optString("retCode");
            String retMsg = js.optString("retMsg");
            Object jsonObj = js.opt("result");

            if ("200".equals(retCode)) {
                if (jsonObj instanceof JSONObject) {
                    callBack.onSuccess(null, new Gson().fromJson(jsonObj.toString(), clsType));
                } else if (jsonObj instanceof JSONArray) {
                    JSONArray array = (JSONArray) jsonObj;
                    callBack.onSuccess(new Gson().fromJson(array.toString(), clsType), null);
                } else if (jsonObj instanceof String) {
                    callBack.onSuccess(null, jsonObj);
                }
            } else {
                callBack.onError(retCode, retMsg);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否是json结构
     */
    private boolean isJson(String value) {
        try {
            new JSONObject(value);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}
