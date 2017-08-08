package org.chzz.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.chzz.demo.R;

/**
 * Created by copy on 2017/8/8.
 */

public class Test1Fragment extends BaseFragment {

    public static Test1Fragment newInstance() {
        Bundle args = new Bundle();
        Test1Fragment fragment = new Test1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }
}
