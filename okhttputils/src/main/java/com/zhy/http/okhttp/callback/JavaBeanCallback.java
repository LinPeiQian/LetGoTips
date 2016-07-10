package com.zhy.http.okhttp.callback;

import android.util.Log;

import com.zhy.http.okhttp.gson.GsonInstance;

import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 交互中 返回javabean的回调
 */
public class JavaBeanCallback<T> extends Callback<T>{

    private final String TAG=  getClass().getSimpleName() ;

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String str = response.body().string();
        Log.d(TAG , "response = "+str);
        Class clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return (T) GsonInstance.getGson().fromJson(str, clazz);
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(T response, int id, boolean cache) {

    }


}
