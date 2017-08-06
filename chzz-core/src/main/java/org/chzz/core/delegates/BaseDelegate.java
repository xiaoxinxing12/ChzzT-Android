package org.chzz.core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午12:49
 */
public abstract class BaseDelegate extends SwipeBackFragment {
    private Unbinder mUnbinder = null;

    public abstract Object setLayout();
  public  abstract  void onBindView(@Nullable Bundle savedInstanceState,View view);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (setLayout() instanceof Integer) {
            view = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            view = (View) setLayout();
        }
        if (view != null) {
            mUnbinder = ButterKnife.bind(this, view);
            onBindView(savedInstanceState,view);
        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }
}
