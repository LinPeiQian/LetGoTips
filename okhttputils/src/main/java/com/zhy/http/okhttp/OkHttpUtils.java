package com.zhy.http.okhttp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.HeadBuilder;
import com.zhy.http.okhttp.builder.OtherRequestBuilder;
import com.zhy.http.okhttp.builder.PostFileBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.JavaBeanCallback;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.gson.GsonInstance;
import com.zhy.http.okhttp.request.RequestCall;
import com.zhy.http.okhttp.utils.HttpSpUtils;
import com.zhy.http.okhttp.utils.Platform;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhy on 15/8/17.
 */
public class OkHttpUtils<T>
{
    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;
    private final String TAG = getClass().getSimpleName();

    public OkHttpUtils(OkHttpClient okHttpClient)
    {
        if (okHttpClient == null)
        {
            mOkHttpClient = new OkHttpClient();
        } else
        {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = Platform.get();
    }


    public static OkHttpUtils initClient(OkHttpClient okHttpClient)
    {
        if (mInstance == null)
        {
            synchronized (OkHttpUtils.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtils getInstance()
    {
        return initClient(null);
    }


    public Executor getDelivery()
    {
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient()
    {
        return mOkHttpClient;
    }

    public static GetBuilder get()
    {
        return new GetBuilder();
    }

    public static PostStringBuilder postString()
    {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile()
    {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post()
    {
        return new PostFormBuilder();
    }

    public static OtherRequestBuilder put()
    {
        return new OtherRequestBuilder(METHOD.PUT);
    }

    public static HeadBuilder head()
    {
        return new HeadBuilder();
    }

    public static OtherRequestBuilder delete()
    {
        return new OtherRequestBuilder(METHOD.DELETE);
    }

    public static OtherRequestBuilder patch()
    {
        return new OtherRequestBuilder(METHOD.PATCH);
    }

    public void execute(final Context context , final RequestCall requestCall, Callback callback)
    {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;
        final int id = requestCall.getOkHttpRequest().getId();

        //回调缓存数据  context为空即表示不适用缓存
        if(context != null){
            Request request = requestCall.getRequest();
            String cacheData = HttpSpUtils.getInstance(context).getCacheData(request.toString());
            if(!TextUtils.isEmpty(cacheData)){
                try{
                    if(callback instanceof JavaBeanCallback){
                        Class clazz = (Class<T>) ((ParameterizedType) callback.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                        Object obj = (T) GsonInstance.getGson().fromJson(cacheData, clazz);
                        sendSuccessResultCallback(obj, finalCallback, id , true , context , requestCall);
                    }else if(callback instanceof StringCallback){
                        sendSuccessResultCallback(cacheData, finalCallback, id , true , context , requestCall);
                    }
                }catch (Exception e){
                    Log.d(TAG , "cache javabean e = " + e);
                }
            }
        }

        requestCall.getCall().enqueue(new okhttp3.Callback()
        {
            @Override
            public void onFailure(Call call, final IOException e)
            {
                sendFailResultCallback(call, e, finalCallback, id);
            }

            @Override
            public void onResponse(final Call call, final Response response)
            {
                if (call.isCanceled())
                {
                    sendFailResultCallback(call, new IOException("Canceled!"), finalCallback, id);
                    return;
                }

                if (!finalCallback.validateReponse(response, id))
                {
                    sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response.code()), finalCallback, id);
                    return;
                }

                try
                {
                    Object o = finalCallback.parseNetworkResponse(response, id );
                    sendSuccessResultCallback(o, finalCallback, id , false , context , requestCall);
                } catch (Exception e)
                {
                    sendFailResultCallback(call, e, finalCallback, id);
                }
            }
        });
    }


    public void sendFailResultCallback(final Call call, final Exception e, final Callback callback, final int id)
    {
        if (callback == null) return;

        mPlatform.execute(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onError(call, e, id);
                callback.onAfter(id);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback, final int id , final boolean cache  , final Context context , final RequestCall requestCall)
    {
        if (callback == null) return;
        mPlatform.execute(new Runnable()
        {
            @Override
            public void run()
            {
                callback.onResponse(object, id , cache);
                if(!cache && context != null){
                    //保存缓存数据
                    if(callback instanceof StringCallback){
                        HttpSpUtils.getInstance(context).setCacheData(requestCall.getRequest().toString() , object.toString());
                    }else if(callback instanceof JavaBeanCallback){
                        HttpSpUtils.getInstance(context).setCacheData(requestCall.getRequest().toString() , GsonInstance.getGson().toJson(object));
                    }
                }
                callback.onAfter(id);

            }
        });
    }

    public void cancelTag(Object tag)
    {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
    }

    public static class METHOD
    {
        public static final String HEAD = "HEAD";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
        public static final String PATCH = "PATCH";
    }
}
