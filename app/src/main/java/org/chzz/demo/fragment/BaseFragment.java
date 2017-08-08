package org.chzz.demo.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import org.chzz.core.fragment.ChzzFragment;
import org.chzz.demo.R;

/**
 * Created by copy on 2017/8/8.
 */

public abstract class BaseFragment extends ChzzFragment {

    Toolbar mToolbar;

    @Override
    protected void iniToolbar(View mContentView) {
        mToolbar= (Toolbar) mContentView.findViewById(R.id.toolbar);
        if(null!=mToolbar){
            initToolbarNav(mToolbar);
        }
    }
}
