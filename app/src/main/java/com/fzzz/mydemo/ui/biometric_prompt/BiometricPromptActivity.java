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
             * 这个接口会再系统指纹认证出现不可恢复的错误的时候才会调用，并且参数errorCode就给出了错误码，标识了错误的原因。
             * 在AndroidP以前，这个方法回调回来之后，指纹识别sensor将会被关闭，也就是说，你再把手指放在指纹硬件上，
             * 将不会有反应了。这时候你需要提示用户关闭指纹识别弹窗，或改用密码支付等等
             * 什么情况下会回调error错误呢？比如，连续识别错误5次指纹、指纹硬件不可用等等。
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

            /**
             * 系统指纹认证失败的情况的下才会回调。注意这里的认证失败和上面的认证错误是不一样的，虽然结果都是不能认证。
             * 认证失败是指所有的信息都采集完整，并且没有任何异常，但是这个指纹和之前注册的指纹是不相符的；
             * 但是认证错误是指在采集或者认证的过程中出现了错误，比如指纹传感器工作异常等。也就是说认证失败是一个可以预期的正常情况，
             * 而认证错误是不可预期的异常情况。
             */
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                ToastUtil.show("验证失败");
            }

            /**
             * OnAuthenticationHelp方法是出现了可以回复的异常才会调用的。例：手指移动太快，当我们把手指放到传感器上的时候，
             *                     如果我们很快地将手指移走的话，那么指纹传感器可能只采集了部分的信息，因此认证会失败。
             *                     但是这个错误是可以恢复的，因此只要提示用户再次按下指纹，并且不要太快移走就可以解决。
             * @param helpCode
             * @param helpString
             */
            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                ToastUtil.show(helpString.toString());
            }

            /**
             * 如果我们上面在调用authenticate的时候，我们的CryptoObject不是null的话，那么我们在这个方法中可以通过
             * AuthenticationResult来获得Cypher对象然后调用它的doFinal方法。doFinal方法会检查结果是不是会拦截或者篡改过，
             * 如果是的话会抛出一个异常。当我们发现这些异常的时候都应该将认证当做是失败来来处理，为了安全建议大家都这么做。
             * @param result
             */
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                ToastUtil.show("验证成功");
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
        super.onActivityResult(requestCode, resultCode, data);
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
