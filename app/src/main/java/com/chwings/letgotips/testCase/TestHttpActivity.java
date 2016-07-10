package com.chwings.letgotips.testCase;

import android.os.Bundle;
import android.util.Log;

import com.chwings.letgotips.R;
import com.chwings.letgotips.activity.BaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.JavaBeanCallback;

import okhttp3.Call;

public class TestHttpActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_http;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OkHttpUtils.post().url("http://www.smartcoach.cn:8030/api.php/login/fetch_verify_code")
                .addParams("mobileNum" , "13538072833").build()
                .execute(this , new JavaBeanCallback<TestHuilianCodeBean>(){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        super.onError(call, e, id);
                        Log.d(TAG , "onError = " + e);
                    }

                    @Override
                    public void onResponse(TestHuilianCodeBean response, int id, boolean cache) {
                        super.onResponse(response, id, cache);
                        Log.d(TAG , "onResponse = "+ response.errMsg + " cache = "+ cache);
                    }

                });

    }
}
