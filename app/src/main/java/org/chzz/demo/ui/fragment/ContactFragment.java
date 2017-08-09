package org.chzz.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.chzz.demo.R;

import butterknife.BindView;

/**
 * Created by copy on 2017/8/9.
 */

public class ContactFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    public static ContactFragment newInstance() {
        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void bindView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_contact, false);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        tvTitle.setText("联系人");
    }
}
