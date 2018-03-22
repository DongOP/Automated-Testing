package com.dong.apitestpro.impl;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Dong on 2018/3/22 0022.
 */

public class OkhttpUtilsDemo {

    public interface ICallBack {
        void onSuccess(String result);
        void onFail(String reason);
    }

    public void doGet(String url,final ICallBack callBack) {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFail(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().toString());
            }
        });
    }

    private void getMessage(String url) {
        doGet(url, new ICallBack() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFail(String reason) {

            }
        });
    }
}
