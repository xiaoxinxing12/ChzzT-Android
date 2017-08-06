package org.chzz.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import org.chzz.core.app.Chzz;
import org.chzz.core.delegates.ChzzDelegage;
import org.chzz.core.net.RestClient;
import org.chzz.core.net.callback.IError;
import org.chzz.core.net.callback.ISuccess;
import org.chzz.core.net.callback.Ifailure;
import org.chzz.core.ui.LoaderStyle;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午1:08
 */
public class DemoDelegate extends ChzzDelegage {
    @Override
    public Object setLayout() {
        return R.layout.delegate_demo;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext(), LoaderStyle.BallGridBeatIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Chzz.getApplication(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new Ifailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();


    }
}
