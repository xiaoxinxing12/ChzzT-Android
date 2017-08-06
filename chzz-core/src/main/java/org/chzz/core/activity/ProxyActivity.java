package org.chzz.core.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import org.chzz.core.R;
import org.chzz.core.delegates.ChzzDelegage;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午12:48
 */
public abstract class ProxyActivity extends SupportActivity {

public  abstract ChzzDelegage setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private  void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout contentFrameLayout=new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.delegate_container);
        setContentView(contentFrameLayout);
        if(savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
