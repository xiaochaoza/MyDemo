package com.fzzz.mydemo.ui.okhttp;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.mydemo.Constants;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.base.BaseActivity;
import com.fzzz.mydemo.utils.PageUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
@Route(path = Constants.PATH_OKHTTP)
public class OkHttpActivity extends BaseActivity {

    //1.创建OkHttpClient对象
    OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();

    @Override
    public int getLayoutID() {
        return R.layout.activity_okhttp;
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                noParamGetSync();
                break;
            case R.id.bt2:
                noParamGetAsync();
                break;
            case R.id.bt3:
                hasFormPostAsync();
                break;
            case R.id.bt4:
                hasJsonPostAsync();
                break;
        }
    }

    /**
     * 无参get请求，同步
     */
    private void noParamGetSync() {
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .get()
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = httpClient.newCall(request);
        //4.同步调用会阻塞主线程,这边在子线程进行
        new Thread(() -> {
            try {
                Response response = call.execute();
                String result = response.body().string();
                if (response.isSuccessful()) {
                    PageUtil.toResultPage(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 无参get请求，异步
     */
    private void noParamGetAsync() {
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .get()
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = httpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PageUtil.toResultPage(response.body().string());
            }
        });
    }

    /**
     * form表单post请求
     */
    private void hasFormPostAsync() {
        RequestBody requestBody = new FormBody.Builder()
                .add("key", Constants.JUHE_APP_KEY)
                .add("type", "yule")
                .build();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder()
                .url(Constants.BASE_URL + "toutiao/index")
                .post(requestBody)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = httpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PageUtil.toResultPage(response.body().string());
            }
        });
    }

    /**
     * json提交post请求
     */
    private void hasJsonPostAsync() {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//"类型,字节码"

        JSONObject params = new JSONObject();
        try {
            params.put("key", Constants.JUHE_APP_KEY);
            params.put("type", "top");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(mediaType, params.toString());
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder()
                .url(Constants.BASE_URL + "toutiao/index")
                .post(requestBody)
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = httpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PageUtil.toResultPage(response.body().string());
            }
        });
    }

    class Para {
        public String type;
        public String key;
    }

    /**
     * 添加请求头
     *
     * @return
     */
    private Request.Builder addHeads() {
        Request.Builder builder = new Request.Builder()
                //可以添加多个header，key一样会覆盖
                .addHeader("a", "1")
                .addHeader("b", "2")
                .addHeader("c", "3")
                .header("d", "4");
        return builder;
    }
}
