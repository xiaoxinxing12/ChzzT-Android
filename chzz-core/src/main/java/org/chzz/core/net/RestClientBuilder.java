package org.chzz.core.net;

import android.content.Context;

import org.chzz.core.net.callback.IError;
import org.chzz.core.net.callback.IRequest;
import org.chzz.core.net.callback.ISuccess;
import org.chzz.core.net.callback.Ifailure;
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
    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.PARAMS;
    private IRequest mRequest;
    private ISuccess mSuccess;
    private Ifailure mFailure;
    private IError mError;
    private RequestBody mBody;
    private Context context;
    private LoaderStyle loaderStyle;
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

    public final RestClientBuilder failure(Ifailure ifailure) {
        this.mFailure = ifailure;
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
