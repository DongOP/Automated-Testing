package com.dong.apitestpro.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.apitestpro.R;
import com.dong.apitestpro.util.OkhttpUtil;

public class BaseMethodActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mBack;
    private TextView mShowMsg;
    private Button mGet,mPost,mDelete,mPut;
    private Handler mUpdateHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_method);

        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.iv_main_back);
        mShowMsg = (TextView) findViewById(R.id.msg_tv);
        mGet = (Button) findViewById(R.id.get_request);
        mPost = (Button) findViewById(R.id.post_request);
        mDelete = (Button) findViewById(R.id.delete_request);
        mPut = (Button) findViewById(R.id.put_request);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseMethodActivity.this.finish();
            }
        });
        mGet.setOnClickListener(this);
        mPost.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mPut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_request:
                doGetRequest();
                break;
            case R.id.post_request:
                doPostRequest();
                break;
            case R.id.delete_request:
                doDeleteRequest();
                break;
            case R.id.put_request:
                doPutRequest();
                break;
            default:
                break;
        }
    }

    private void doPutRequest() {
        mShowMsg.setText("loading...");
        String putUrl = "https://postman-echo.com/put";
        OkhttpUtil.get().doPut(putUrl, null, new OkhttpUtil.CallBack() {
            @Override
            public void onRequestComplete(final String result) {
                if (!result.isEmpty()) {
                    mUpdateHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mShowMsg.setText("PUT请求结果：" + result);
                        }
                    });
                }
            }

            @Override
            public void onRequestWithResponse(byte[] response) {

            }

            @Override
            public void onRequestFail(final String reason) {
                mUpdateHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mShowMsg.setText("doPutRequest, reason=" + reason);
                    }
                });
            }
        });
    }

    private void doDeleteRequest() {
        mShowMsg.setText("loading...");
        String delUrl = "https://postman-echo.com/delete";
        OkhttpUtil.get().doDelete(delUrl, null, new OkhttpUtil.CallBack() {
            @Override
            public void onRequestComplete(final String result) {
                if (!result.isEmpty()) {
                    mUpdateHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mShowMsg.setText("DELETE请求结果：" + result);
                        }
                    });
                }
            }

            @Override
            public void onRequestWithResponse(byte[] response) {
            }

            @Override
            public void onRequestFail(final String reason) {
                mUpdateHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mShowMsg.setText("doDeleteRequest, reason=" + reason);
                    }
                });
            }
        });
    }

    private void doPostRequest() {
        mShowMsg.setText("loading...");
        String postUrl = "https://postman-echo.com/post";
        OkhttpUtil.get().doPostAsyn(postUrl, new OkhttpUtil.CallBack() {
            @Override
            public void onRequestComplete(final String result) {
                if (!result.isEmpty()) {
                    mUpdateHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mShowMsg.setText("POST请求结果：" + result);
                        }
                    });
                }
            }

            @Override
            public void onRequestWithResponse(byte[] response) {
            }

            @Override
            public void onRequestFail(final String reason) {
                mUpdateHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mShowMsg.setText("doPostRequest, reason=" + reason);
                    }
                });
            }
        });
    }

    private void doGetRequest() {
        mShowMsg.setText("loading...");
        String urlStr = "https://postman-echo.com/time/now";
        OkhttpUtil.get().doGetAsyn(urlStr, new OkhttpUtil.CallBack() {
            @Override
            public void onRequestComplete(final String result) {
                if (!result.isEmpty()) {
                    mUpdateHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mShowMsg.setText("GET请求结果：" + result);
                        }
                    });
                }
            }

            @Override
            public void onRequestWithResponse(byte[] response) {
            }

            @Override
            public void onRequestFail(final String reason) {
                mUpdateHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mShowMsg.setText("doGetRequest onRequest Fail! reason=" + reason);
                    }
                });
            }
        });
    }
}
