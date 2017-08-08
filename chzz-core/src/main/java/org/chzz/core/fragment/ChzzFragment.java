package org.chzz.core.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.chzz.core.R;
import org.chzz.core.activity.ChzzActivity;

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
public abstract class ChzzFragment extends SwipeBackFragment {
    private Unbinder mUnbinder = null;
    protected View mContentView;
    protected  LayoutInflater inflater;
    protected ViewGroup container;
    protected ChzzActivity chzzActivity;
    //边是否可退出Fragment
    private boolean  attachToSwipeBack=true;
    //是否显示退出按键
    private boolean showBack=true;
    protected abstract void bindView( @Nullable Bundle savedInstanceState);
    protected abstract void initView( @Nullable Bundle savedInstanceState);
    protected  void iniToolbar(View mContentView){};
    /**
     * 一般监听就设在这里吧
     */
    protected abstract void setListener();
    /**
     * 当fragment对用户可见时，会调用该方法，可在该方法中懒加载网络数据
     */
    public void onUserVisible() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        chzzActivity = (ChzzActivity) context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater=inflater;
        this.container=container;
        bindView(savedInstanceState);
        if (mContentView != null) {
            mUnbinder = ButterKnife.bind(this, mContentView);
        }
        initView(savedInstanceState);
        iniToolbar(mContentView);
        setListener();
        return attachToSwipeBack? attachToSwipeBack(mContentView):mContentView;
    }

    /**
     *
     * @param layoutResID 布局文件
     */
    protected void setContentView(@LayoutRes int layoutResID) {
        mContentView = inflater.inflate(layoutResID, container, false);
    }

    /**
     *
     * @param layoutResID  布局文件，是否显示后退按键 ,是否可边滑动退出
     * @param showBack
     */
    protected void setContentView(@LayoutRes int layoutResID,boolean showBack) {
        mContentView = inflater.inflate(layoutResID, container, false);
        this.showBack=showBack;
        this.attachToSwipeBack=showBack;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    /**
     * 设置Toolbar 图标
     * @param toolbar
     */
    protected void initToolbarNav(Toolbar toolbar) {
        if(showBack){
            toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop();
                }
            });
        }
    }

    public ChzzActivity getChzzActivity() {
        return chzzActivity;
    }
}
