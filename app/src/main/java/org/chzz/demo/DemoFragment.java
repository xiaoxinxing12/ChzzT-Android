package org.chzz.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import org.chzz.core.app.Chzz;
import org.chzz.core.fragment.ChzzFragment;
import org.chzz.core.net.RestClient;
import org.chzz.core.net.callback.IError;
import org.chzz.core.net.callback.IFailure;
import org.chzz.core.net.callback.ISuccess;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午1:08
 */
public class DemoFragment extends ChzzFragment {
    @Override
    public Object setLayout() {
        return R.layout.delegate_demo;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View view) {
        testRestClient();
        testRestClient1();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("router/system/user/getTenantList")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Chzz.getApplication(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(Chzz.getApplication(), "onFailure", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(Chzz.getApplication(), msg, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }

    private void testRestClient1() {
        RestClient.builder()
                .url("http://www.baidu.com")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Chzz.getApplication(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(Chzz.getApplication(), "onFailure", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(Chzz.getApplication(), msg, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }
}
