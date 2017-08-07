package org.chzz.demo;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

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
                .withApiHost("http://zyy.hxyiyo.com/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .whthInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
