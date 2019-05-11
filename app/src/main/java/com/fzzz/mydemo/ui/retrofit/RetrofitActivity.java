package com.fzzz.mydemo.ui.retrofit;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.mydemo.Constants;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.base.BaseActivity;
import com.fzzz.mydemo.net.RemoteService;
import com.fzzz.mydemo.utils.PageUtil;

import java.io.IOException;

import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
@Route(path = Constants.PATH_RETROFIT)
public class RetrofitActivity extends BaseActivity {
    @Override
    public int getLayoutID() {
        return R.layout.activity_retrofit;
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

    }

    /**
     * 无参get请求，异步
     */
    private void noParamGetAsync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .build();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        Call<ResponseBody> call = remoteService.getNews("index");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    PageUtil.toResultPage(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("error");
            }
        });
    }

    /**
     * form表单post请求
     */
    private void hasFormPostAsync() {

    }

    /**
     * json提交post请求
     */
    private void hasJsonPostAsync() {

    }

}
