package com.fzzz.mydemo.ui.retrofit_rxjava;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.BuildConfig;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.framework.helper.RetrofitJuHeHelper;
import com.fzzz.framework.helper.RetrofitLocalHelper;
import com.fzzz.framework.utils.RequestBodyUtil;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.adapter.BaseAdapter;
import com.fzzz.mydemo.bean.NewsJuheBean;
import com.fzzz.mydemo.bean.UserReturnBean;
import com.fzzz.mydemo.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * description:
 * author: ShenChao
 * time: 2019-05-10
 * update:
 */
@Route(path = Constants.PATH_APP_RETROFIT_RXJAVA)
public class RetrofitRxjavaActivity extends BaseActivity {
    public static final String TAG = "RetrofitRxjavaActivity";

    @BindView(R.id.content1)
    TextView content1;
    @BindView(R.id.content2)
    TextView content2;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_retrofit_rxjava;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etUsername.setFocusable(true);
        etUsername.setFocusableInTouchMode(true);
        etUsername.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.content1, R.id.content2, R.id.local_add, R.id.local_delete,
            R.id.local_update, R.id.local_find_all, R.id.local_find_one})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                bt1();
                content1.setVisibility(View.VISIBLE);
                content2.setVisibility(View.GONE);
                break;
            case R.id.bt2:
                bt2();
                content1.setVisibility(View.GONE);
                content2.setVisibility(View.VISIBLE);
                break;
            case R.id.content1:
                con1();
                break;
            case R.id.content2:
//                con2();
                break;
            case R.id.local_add:
                localAdd();
                break;
            case R.id.local_delete:
                localDelete();
                break;
            case R.id.local_update:
                localUpdate();
                break;
            case R.id.local_find_all:
                localFindAll();
                break;
            case R.id.local_find_one:
                localFindUserByUserName();
                break;
            default:
                break;
        }
    }

    private void localFindUserByUserName() {
        RequestBody requestBody = RequestBodyUtil.creat(getInput());
        disposable = RetrofitLocalHelper.get().findUserByUserName(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((responseBody) ->
                        new Gson().fromJson(responseBody.string(), UserReturnBean.class)
                ).subscribe((userReturnBean) -> {
                    if (null == userReturnBean) {
                        return;
                    }
                    BaseAdapter adapter = new BaseAdapter(userReturnBean, (userName, password) -> {
                        etUsername.setText(userName);
                        etPassword.setText(password);
                    });
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RetrofitRxjavaActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerview.setAdapter(adapter);
                    recyclerview.setLayoutManager(layoutManager);
                });
    }

    private void localFindAll() {
        RequestBody requestBody = RequestBodyUtil.creat(new HashMap<>());
        disposable = RetrofitLocalHelper.get().findAll(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((responseBody) ->
                        new Gson().fromJson(responseBody.string(), UserReturnBean.class)
                ).subscribe((userReturnBean) -> {
                    BaseAdapter adapter = new BaseAdapter(userReturnBean, (userName, password) -> {
                        etUsername.setText(userName);
                        etPassword.setText(password);
                    });
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RetrofitRxjavaActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerview.setAdapter(adapter);
                    recyclerview.setLayoutManager(layoutManager);
                });
    }

    private void localUpdate() {
        RequestBody requestBody = RequestBodyUtil.creat(getInput());
        disposable = RetrofitLocalHelper.get().update(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((responseBody) ->
                        new Gson().fromJson(responseBody.string(), UserReturnBean.class)
                )
                .subscribe((userReturnBean) -> {
                    if (null == userReturnBean) {
                        return;
                    }
                    ToastUtil.show(userReturnBean.resultMessage);
                });
    }

    private void localDelete() {
        RequestBody requestBody = RequestBodyUtil.creat(getInput());
        disposable = RetrofitLocalHelper.get().delete(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((responseBody) ->
                        new Gson().fromJson(responseBody.string(), UserReturnBean.class)
                )
                .subscribe((userReturnBean) -> {
                    if (null == userReturnBean) {
                        return;
                    }
                    ToastUtil.show(userReturnBean.resultMessage);
                });
    }

    private void localAdd() {
        RequestBody requestBody = RequestBodyUtil.creat(getInput());
        disposable = RetrofitLocalHelper.get().add(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((responseBody) ->
                        new Gson().fromJson(responseBody.string(), UserReturnBean.class)
                )
                .subscribe((userReturnBean) -> {
                    if (null == userReturnBean) {
                        return;
                    }
                    ToastUtil.show(userReturnBean.resultMessage);
                });
    }

    private Map<String, String> getInput() {
        String userName = etUsername.getText().toString().trim();
        String passWord = etPassword.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(userName)) {
            params.put("userName", userName);
        }
        if (!TextUtils.isEmpty(passWord)) {
            params.put("passWord", passWord);
        }
        return params;
    }

    private void bt1() {
        content1.setText(getString(R.string.retrofit_rxjava_content1));
    }

    private void bt2() {
        content2.setText(getString(R.string.retrofit_rxjava_content2));
    }

    private void con1() {
        content1.setText("");
        disposable = RetrofitJuHeHelper.get().getNewsGet(BuildConfig.JUHE_APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((responseBody) -> {
                    Gson gson = new Gson();
                    NewsJuheBean newsJuheBean = gson.fromJson(responseBody.string(), NewsJuheBean.class);
                    return Observable.fromIterable(newsJuheBean.getResult().getData());
                }).subscribe((dataBean) -> {
                    content1.append(dataBean.getTitle());
                    content1.append("\n");
                });
    }

    private void con2() {
        content2.setText("");
        disposable = RetrofitJuHeHelper.get().getNewsPost(BuildConfig.JUHE_APP_KEY, "top")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((responseBody) -> {
                    Gson gson = new Gson();
                    NewsJuheBean newsJuheBean = gson.fromJson(responseBody.string(), NewsJuheBean.class);
                    return Observable.fromIterable(newsJuheBean.getResult().getData());
                }).subscribe((dataBean) -> {
                    content1.append(dataBean.getTitle());
                    content1.append("\n");
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != disposable && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
