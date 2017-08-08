package org.chzz.core.net;

import android.content.Context;

import org.chzz.core.net.callback.IError;
import org.chzz.core.net.callback.IFailure;
import org.chzz.core.net.callback.IRequest;
import org.chzz.core.net.callback.ISuccess;
import org.chzz.core.net.callback.RequestCallback;
import org.chzz.core.ui.ChzzLoader;
import org.chzz.core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午1:37
 */
public class RestClient {

    //请求接口地址
    private final String URL;
    //请求参数  Map<>形式
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.PARAMS;
    //开始或结束回调
    private final IRequest REQUEST;
    //请求成功回调
    private final ISuccess SUCCESS;
    //请求失败回调
    private final IFailure FAILURE;
    //请求错误
    private final IError ERROR;
    //原形参数 body
    private final RequestBody BODY;
    //加载图标样式
    private final LoaderStyle LOADER_STYLE;
    //上下文
    private final Context CONTEXT;
    //上传的文件
    private final File FILE;

    public RestClient(String URL, Map<String, Object> params,
                      IRequest request, ISuccess success,
                      IFailure failure, IError error,
                      RequestBody body, File file, LoaderStyle loader_style, Context context) {
        this.URL = URL;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.FILE = file;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loader_style;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder() {

        return new RestClientBuilder();
    }

    /**
     * 判断使用什么方法请求
     * @param method
     */
    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        //回调
        Call<String> call = null;
        if (REQUEST != null) {
            //请求开始
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            //显示加载图标
            ChzzLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(),requestBody);
                call=RestCreator.getRestService().upload(URL,body);
                break;
            default:
                break;

        }
        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack() {

        return new RequestCallback(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }

    /**
     * get请求
     */
    public final void get() {
        request(HttpMethod.GET);
    }

    /**
     * post 请求
     */
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("参数错误");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("参数错误");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
