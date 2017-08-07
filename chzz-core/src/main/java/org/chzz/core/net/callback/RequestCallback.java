package org.chzz.core.net.callback;

import android.os.Handler;

import org.chzz.core.ui.ChzzLoader;
import org.chzz.core.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午3:30
 */
public class RequestCallback implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler handler = new Handler();

    public RequestCallback(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loader_style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loader_style;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        if (LOADER_STYLE != null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ChzzLoader.stopLoading();
                }
            }, 1000);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        if (LOADER_STYLE != null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ChzzLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
