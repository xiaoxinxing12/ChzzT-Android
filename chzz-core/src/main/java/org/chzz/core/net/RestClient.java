package org.chzz.core.net;

import android.content.Context;

import org.chzz.core.net.callback.IError;
import org.chzz.core.net.callback.IRequest;
import org.chzz.core.net.callback.ISuccess;
import org.chzz.core.net.callback.Ifailure;
import org.chzz.core.net.callback.RequestCallbacks;
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

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.PARAMS;

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final Ifailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYPE;
    private final Context CONTEXT;
    private final File FILE;

    public RestClient(String URL, Map<String, Object> params,
                      IRequest request, ISuccess success,
                      Ifailure failure, IError error,
                      RequestBody body, File file, LoaderStyle loader_stype, Context context) {
        this.URL = URL;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.FILE = file;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYPE = loader_stype;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder() {

        return new RestClientBuilder();
    }

    private void requset(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestSart();
        }
        if (LOADER_STYPE != null) {
            ChzzLoader.showLoading(CONTEXT, LOADER_STYPE);
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

        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYPE);
    }

    public final void get() {
        requset(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            requset(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("参数错误");
            }
            requset(HttpMethod.POST_RAW);
        }

    }

    public final void put() {
        if (BODY == null) {
            requset(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("参数错误");
            }
            requset(HttpMethod.PUT_RAW);
        }

    }

    public final void delete() {
        requset(HttpMethod.DELETE);
    }
}
