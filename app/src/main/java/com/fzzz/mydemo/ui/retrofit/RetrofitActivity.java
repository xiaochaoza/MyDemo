package com.fzzz.mydemo.ui.retrofit;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.mydemo.Constants;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.base.BaseActivity;
import com.fzzz.mydemo.bean.NewsJuheBean;
import com.fzzz.mydemo.helper.RetrofitJuHeHelper;
import com.fzzz.mydemo.net.RemoteService;
import com.fzzz.mydemo.utils.PageUtil;
import com.fzzz.mydemo.utils.RequestBodyUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;
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

    public static final String TAG = "RetrofitActivity";

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    public int getLayoutID() {
        return R.layout.activity_retrofit;
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5})
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
            case R.id.bt5:
                hanParamGetAsync();
                break;

        }
    }

    /**
     * 无参get请求，同步
     */
    private void noParamGetSync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com/")
                .build();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        Call<ResponseBody> call = remoteService.getNews("index");
        new Thread(() -> {
            try {
                Response response = call.execute();
                String result = ((ResponseBody) response.body()).string();
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com/")
                .build();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        Call<ResponseBody> call = remoteService.baidu();
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
                Log.d(TAG, "error");
            }
        });
    }

    /**
     * form表单post请求
     */
    private void hasFormPostAsync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_JUHE)
                .build();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        Call<ResponseBody> call = remoteService.getNewsPostWithKey(Constants.JUHE_APP_KEY, "yule");
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
                Log.d(TAG, "error");
            }
        });
    }

    /**
     * get提交带参
     */
    private void hanParamGetAsync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_JUHE)
                .build();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        Call<ResponseBody> call = remoteService.getNewsGetWithKey(Constants.JUHE_APP_KEY);
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
                Log.d(TAG, "error");
            }
        });
    }

    /**
     * json提交post请求 接口不支持json格式
     */
    private void hasJsonPostAsync() {
        Map<String, String> params = new HashMap<>();
        params.put("key", Constants.JUHE_APP_KEY);
        params.put("type", "top");
        RequestBody requestBody = RequestBodyUtil.creat(params);
        Call<ResponseBody> call = RetrofitJuHeHelper.get().getNewsPostJson(requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    NewsJuheBean news = new Gson().fromJson(json, NewsJuheBean.class);
                    if (null == news.getResult()) {
                        PageUtil.toResultPage(news.getError_code() + "---" + news.getReason());
                    } else {
                        List<NewsJuheBean.ResultBean.DataBean> data = news.getResult().getData();
                        StringBuffer sb = new StringBuffer();
                        for (NewsJuheBean.ResultBean.DataBean datum : data) {
                            sb.append(datum.getTitle());
                            sb.append("\n");
                        }
                        PageUtil.toResultPage(sb.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "erroe");
            }
        });
    }
}
