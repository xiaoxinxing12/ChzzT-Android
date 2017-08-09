package org.chzz.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.chzz.demo.R;

import butterknife.BindView;

/**
 * Created by copy on 2017/8/9.
 */

public class IndexFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static IndexFragment newInstance() {
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_index, false);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        tvTitle.setText("消息");
    }

    @Override
    protected void setListener() {

    }

}
