package org.chzz.demo;

import org.chzz.core.activity.BaseActivity;
import org.chzz.core.fragment.ChzzFragment;

public class MainActivity extends BaseActivity {

    @Override
    public ChzzFragment setRootFragment() {
        return new DemoFragment();
    }

}
