package org.chzz.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.chzz.core.activity.ProxyActivity;
import org.chzz.core.app.Chzz;
import org.chzz.core.delegates.ChzzDelegage;

public class MainActivity extends ProxyActivity {

    @Override
    public ChzzDelegage setRootDelegate() {
        return new DemoDelegate();
    }

}
