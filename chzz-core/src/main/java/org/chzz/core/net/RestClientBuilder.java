package org.chzz.core.net;

import android.content.Context;

import org.chzz.core.net.callback.IError;
import org.chzz.core.net.callback.IRequest;
import org.chzz.core.net.callback.ISuccess;
import org.chzz.core.net.callback.IFailure;
import org.chzz.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午3:00
 */
public class RestClientBuilder {
    //请求接口地址
    private String mUrl;
    //请求参数  Map<>形式
    private static final Map<String, Object> PARAMS = RestCreator.PARAMS;
    //开始或结束回调
    private IRequest mRequest;
    //请求成功回调
    private ISuccess mSuccess;
    //请求失败回调
    private IFailure mFailure;
    //请求错误回调
    private IError mError;
    //原形参数 body
    private RequestBody mBody;
    //上下文
    private Context context;
    //加载图标样式
    private LoaderStyle loaderStyle;
    //上传的文件
    private File file;

    public RestClientBuilder() {

    }

    public final RestClientBuilder params(Map<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {

        this.PARAMS.put(key, value);
        return this;
    }
    public final RestClientBuilder file(File file) {

        this.file=file;
        return this;
    }
    public final RestClientBuilder file(String file) {
        this.file=new File(file);
        return this;
    }
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);

        return this;
    }

    public final RestClientBuilder onRequest(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    public final RestClientBuilder failure(IFailure IFailure) {
        this.mFailure = IFailure;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.context = context;
        this.loaderStyle = style;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.context = context;
        this.loaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    private Map<String, Object> checkParama() {
        return PARAMS;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mRequest, mSuccess, mFailure, mError, mBody, file,loaderStyle, context);
    }

}
