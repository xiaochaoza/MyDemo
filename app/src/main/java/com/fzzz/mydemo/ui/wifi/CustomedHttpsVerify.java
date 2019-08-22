package com.fzzz.mydemo.ui.wifi;

import android.content.Context;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * TODO 应用可根据自身公共处理逻辑，重写以下方法，在方法中对生成、测试环境判断，执行不同的处理逻辑
 */
public class CustomedHttpsVerify {
    private Context context;

    // RN页面fetch请求及网络图片加载过程中，https校验所用的SSLContext
    public SSLContext getSSLContext() {
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext;
    }

    /**
     *     TODO 请求https校验所用的验证规则
     */
    public HostnameVerifier getHostnameVerifier(){
        return new HostnameVerifier(){
            Boolean isOK = false;
            @Override
            public boolean verify(String hostname, SSLSession session) {
                /*String peerHost = session.getPeerHost(); //服务器返回的主机名
                LogUtil.e("peerHost==" + peerHost);
                try {
                    X509Certificate[] peerCertificates = (X509Certificate[])session.getPeerCertificates();
                    for(X509Certificate certificate : peerCertificates){
                        certificate.checkValidity();

                        //校验公钥是否一致
                        String publicKey = certificate.getPublicKey().toString();

                        LogUtil.e("publicKey111==" + publicKey.toString());

                        publicKey = publicKey.substring(publicKey.indexOf("modulus=")+8,publicKey.indexOf(","));

                        LogUtil.e("publicKey222==" + publicKey.toString());

                        if (peerHost.equals(hostname)){
                            // TODO 验证公钥是否在信任列表
                            for (String key : AppConstants.HTTPS_PUB_KEYS) {
//                                key = key.substring(18,key.length() - 10);
//                                LogUtil.e("keey333==" + key);
                                if (key.equals(publicKey)){
                                    isOK = true;
                                    break;
                                }
                            }
                        }

                    }
                } catch (SSLPeerUnverifiedException e) {
                    e.printStackTrace();
                    isOK = false;
                } catch (CertificateExpiredException e) {
                    e.printStackTrace();
                    isOK = false;
                } catch (CertificateNotYetValidException e) {
                    e.printStackTrace();
                    isOK = false;
                }*/
                return true;
            }
        };
    }

    //网络图片加载过程中https 未自定义
    /**
     *     TODO RN页面Webview中，sslError的回调处理，这里自定义校验失败时跳过指定证书使用的逻辑
     */
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
       /* if (error.getPrimaryError() == SslError.SSL_DATE_INVALID  // 日期不正确
                || error.getPrimaryError() == SslError.SSL_EXPIRED // 日期不正确
                || error.getPrimaryError() == SslError.SSL_INVALID // webview BUG
                || error.getPrimaryError() == SslError.SSL_UNTRUSTED) { // 根证书丢失
            if (chkMySSLCNCert(error.getCertificate())) {
                handler.proceed();  // 如果证书一致，忽略错误
            }
        }*/
        Boolean isOK = false;
        Bundle bundle = SslCertificate.saveState(error.getCertificate());
        byte[] bytes = bundle.getByteArray("x509-certificate");
        if (bytes != null) {
            try {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                Certificate ca = cf.generateCertificate(new java.io.ByteArrayInputStream(bytes));

                String publicKey = ((X509Certificate) ca).getPublicKey().toString();
                publicKey = publicKey.substring(publicKey.indexOf("modulus=")+8,publicKey.indexOf(","));

                /*// TODO 验证公钥是否在信任列表
                for (String key : AppConstants.HTTPS_PUB_KEYS) {
                    if (key.equals(publicKey)){
                        isOK = true;
                        break;
                    }
                }*/
            } catch (Exception e) {
            }
        }
        if (isOK){
            handler.proceed();
        }
    }

}
