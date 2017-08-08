package org.chzz.demo;

import org.chzz.core.activity.ChzzActivity;
import org.chzz.core.fragment.ChzzFragment;
import org.chzz.demo.fragment.DemoFragment;

public class MainActivity extends ChzzActivity {

    @Override
    public ChzzFragment setRootFragment() {
        return new DemoFragment();
    }

}
