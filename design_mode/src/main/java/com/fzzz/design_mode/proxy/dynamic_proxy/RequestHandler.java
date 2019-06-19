package com.fzzz.design_mode.proxy.dynamic_proxy;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-19
 * update:
 */
public class RequestHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[] annotations = method.getAnnotations();
        if (annotations != null && annotations.length > 0) {
            Annotation annotation = annotations[0];
            if (annotation instanceof GET) {
                GET get = (GET) annotation;
                return handleGetRequest(method, get, args);
            } else if (annotation instanceof POST) {
                POST post = (POST) annotation;
                return handlePostRequest(method, post, args);
            }
        }
        return null;
    }

    private Observable handleGetRequest(Method method, GET get, Object[] params) {
        String url = get.value();
        Type genericType = method.getGenericReturnType();
        Parameter[] parameters = method.getParameters();

        ParameterizedType parameterizedType = (ParameterizedType) genericType;
        Class returnGenericClazz = null;
        //解析方法返回值类型
        if (parameterizedType!=null) {
            Type[] types = parameterizedType.getActualTypeArguments();
            for (int i = 0; i < types.length; i++) {
                Class cls = (Class) types[i];
                returnGenericClazz = cls;
                break;
            }
        }
        //解析请求参数，然后拼接到url
        if (params != null) {
            url += "?";
            for (int i = 0; i < params.length; i++) {
                Query query = parameters[i].getAnnotation(Query.class);
                url += query.value() + "=" + params[0].toString();
                if (i < params.length - 1) {
                    url += "&";
                }
            }
        }
        final String getUrl = url;
        final Class returnClazz = returnGenericClazz;
        return Observable.create(observableEmitter -> {
            Request request = new Request.Builder().url(getUrl).build();
            Response response = new OkHttpClient()
                    .newCall(request).execute();
            if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                //这里mock返回数据
//                String responseStr = MockFactory.mockCheckUpdateStr();
                Object result = new Gson().fromJson(responseStr, returnClazz);
                observableEmitter.onNext(result);
            }else {
                observableEmitter.onError(new IllegalStateException("http request failed!"));
            }
            observableEmitter.onComplete();
        });
    }

    private Object handlePostRequest(Method method, POST post, Object[] args) {
        return null;
    }
}
