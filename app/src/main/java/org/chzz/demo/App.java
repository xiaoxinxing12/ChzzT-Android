package org.chzz.demo;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;

import org.chzz.core.app.Chzz;
import org.chzz.core.net.interceptors.DebugInterceptor;
import org.chzz.ec.icon.FontEcModule;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 上午10:58
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Chzz.init(this)
                .withApiHost("http://127.0.0.1")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .whthInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
