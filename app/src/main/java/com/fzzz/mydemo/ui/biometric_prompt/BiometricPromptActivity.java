package com.fzzz.mydemo.ui.biometric_prompt;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fzzz.framework.Constants;
import com.fzzz.framework.base.BaseActivity;
import com.fzzz.mydemo.R;
import com.fzzz.mydemo.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.OnClick;

/**
 * description: 生物识别：指纹和人脸等等
 * author: ShenChao
 * time: 2020-01-19
 * update:
 */
@Route(path = Constants.PATH_APP_BIOMETRICPROMPT)
public class BiometricPromptActivity extends BaseActivity {

    private KeyguardManager mKeyManager;
    private BiometricPrompt biometricPrompt;
    private CancellationSignal cancel;
    private BiometricPrompt.AuthenticationCallback authenticationCallback;
    private final static int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 0123;


    @Override
    public int getLayoutId() {
        return R.layout.activity_biometric_prompt;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mKeyManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);

        biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("指纹识别")
                .setDescription("识别指纹，我是描述")
                .setNegativeButton("取消", getMainExecutor(), (DialogInterface dialog, int which) -> {
                    ToastUtil.show("用户取消");
                    Logger.e("用户取消");
                })
                .build();

        cancel = new CancellationSignal();
        cancel.setOnCancelListener(() -> {
            ToastUtil.show("用户取消222");
            Logger.e("用户取消222");
        });

        authenticationCallback = new BiometricPrompt.AuthenticationCallback() {
            /**
             * 指纹验证错误
             * @param errorCode
             * @param errString
             */
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                ToastUtil.show(errString.toString());
                //多次失败后跳转到输入密码界面
                showAuthenticationScreen();
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                ToastUtil.show(helpString.toString());
            }

            /**
             * 指纹验证成功
             * @param result
             */
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                ToastUtil.show("验证成功");
            }

            /**
             * 指纹验证失败
             */
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                ToastUtil.show("验证失败");
            }
        };
    }

    @OnClick({R.id.bt_biometric_prompt_finger, R.id.bt_biometric_prompt_finger2, R.id.bt_biometric_prompt_face})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //直接调用校验，失败多次，未做处理
            case R.id.bt_biometric_prompt_finger:
                biometricPrompt.authenticate(cancel, getMainExecutor(), authenticationCallback);
                break;
            //调起系统指纹识别，失败多次会跳转到输入密码界面
            case R.id.bt_biometric_prompt_finger2:
                showAuthenticationScreen();
                break;
            case R.id.bt_biometric_prompt_face:
                break;
            default:
                break;
        }
    }

    /**
     * 调起系统指纹识别，失败多次会跳转到输入密码界面
     */
    private void showAuthenticationScreen() {
        Intent intent = mKeyManager.createConfirmDeviceCredentialIntent("指纹识别", "我是系统指纹识别的描述");
        if (intent != null) {
            startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                ToastUtil.show("识别成功");
            } else {
                ToastUtil.show("识别失败");
            }
        }
    }
}
