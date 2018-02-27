package com.dong.apitestpro.util;

import org.json.JSONObject;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Dong on 2017/12/19 0019.
 */

public class OkhttpUtil {

    private static final String TAG = "OkhttpUtil";
    private static OkhttpUtil mOkhttpUtil;
    private static OkHttpClient okHttpClient;
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/jpg; charset=utf-8");

    private OkhttpUtil() {
        X509TrustManager trustManager = new TrustAllCerts();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(createSSLSocketFactory(trustManager), trustManager);
        builder.hostnameVerifier(new TrustAllHostnameVerifier());
        okHttpClient = builder.build();
    }

    public interface CallBack {
        /**
         * 返回请求成功的Response.body()
         */
        void onRequestComplete(String result);

        /**
         * 返回请求成功的Response
         */
        void onRequestWithResponse(byte[] response);

        void onRequestFail(String reason);
    }

    public static OkhttpUtil get() {
        if (mOkhttpUtil == null) {
            mOkhttpUtil = new OkhttpUtil();
        }
        return mOkhttpUtil;
    }

    // okhttp信任https证书  start
    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[0];
        }
    }

    private static SSLSocketFactory createSSLSocketFactory(X509TrustManager trustManager) {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{trustManager}, null);
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    // okhttp信任https证书  end

    public void doGetAsyn(final String urlStr, final CallBack callBack) {
        Request request = new Request.Builder().url(urlStr).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                if(callBack != null){
                    callBack.onRequestFail("ioexception");
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (callBack != null) {
                        String result = response.body().string();
                        callBack.onRequestComplete(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * post 请求
     *
     * @param urlStr    请求的url
     * @param callBack  请求后的回调
     */
    public void doPostAsyn(final String urlStr, final CallBack callBack){
        // 设置媒体类型
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "");

        Request request = new Request.Builder()
                .post(requestBody)
                .url(urlStr)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                if(callBack != null){
                    callBack.onRequestFail("ioexception");
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (callBack != null) {
                        callBack.onRequestComplete(response.body().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 带参数的 post 请求
     *
     * @param urlStr    请求的url
     * @param mediaType 媒体格式
     * @param params    参数
     * @param callBack  请求后的回调
     */
    public void doPostWithParam(final String urlStr, MediaType mediaType, Map<String, Object> params, final CallBack callBack) {
        if (params == null) {
            throw new NullPointerException("params is null");
        }

        JSONObject jsonParams = new JSONObject(params);
        // 设置媒体类型
        RequestBody requestBody = RequestBody.create(mediaType, jsonParams.toString());
        Request request = new Request.Builder()
                .url(urlStr)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 请求失败
                if (callBack != null) {
                    callBack.onRequestFail("ioexception");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (callBack != null) {
                        String result = response.body().string();
                        callBack.onRequestComplete(result);
                        callBack.onRequestWithResponse(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 带参数的 put 请求
     * @param url 请求的url链接
     * @param tokenId 授权码
     * @param params 请求携带的参数
     * @param callBack 请求的回调
     */
    public void doPut(String url, Map<String, Object> params, final CallBack callBack) {
        // 设置媒体类型
        RequestBody body;
        if (params == null) {
            body = RequestBody.create(MEDIA_TYPE_JSON, "");
        } else {
            JSONObject jsonParams = new JSONObject(params);
            body = RequestBody.create(MEDIA_TYPE_JSON, jsonParams.toString());
        }
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
//                .addHeader("Authorization", "Bearer " + tokenId)
                .url(url)
                .put(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 请求失败
                if (callBack != null) {
                    callBack.onRequestFail(e.toString());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callBack != null) {
                    try {
                        String result = response.body().string();
                        callBack.onRequestComplete(result);
                        callBack.onRequestWithResponse(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 带参数的 delete 请求
     * @param url 请求的url链接
     * @param tokenId 授权码
     * @param params 请求携带的参数
     * @param callBack 请求的回调
     */
    public void doDelete(String url, Map<String, Object> params, final CallBack callBack) {
        // 设置媒体类型
        RequestBody body;
        if (params == null) {
            body = RequestBody.create(MEDIA_TYPE_JSON, "");
        } else {
            JSONObject jsonParams = new JSONObject(params);
            body = RequestBody.create(MEDIA_TYPE_JSON, jsonParams.toString());
        }
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
//                .addHeader("Authorization", "Bearer " + tokenId)
                .url(url)
                .delete(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 请求失败
                if (callBack != null) {
                    callBack.onRequestFail(e.toString());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callBack != null) {
                    try {
                        String result = response.body().string();
                        callBack.onRequestComplete(result);
                        callBack.onRequestWithResponse(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
