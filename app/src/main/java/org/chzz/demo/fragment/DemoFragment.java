package org.chzz.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.chzz.core.app.Chzz;
import org.chzz.core.net.RestClient;
import org.chzz.core.net.callback.Success;
import org.chzz.demo.R;
import org.chzz.demo.util.ToastUtil;

import butterknife.OnClick;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午1:08
 */
public class DemoFragment extends BaseFragment {
    @Override
    protected void bindView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_demo,false);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }
    @Override
    protected void setListener() {
    }

    @Override
    public void onUserVisible() {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("router/system/user/getTenantList")
                .loader(getContext())
                .success(new Success() {
                    @Override
                    public void onSuccess(Object response) {
                        String a = response.toString();
                    }
                })
                .build()
                .get();
    }
    @OnClick(R.id.but_test)
    void test(){
    start(new Test1Fragment());
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if(requestCode==100){
            ToastUtil.show(Chzz.getApplication(),"1111");
        }
    }
}
