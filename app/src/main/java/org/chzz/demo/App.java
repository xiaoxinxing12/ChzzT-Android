package org.chzz.demo;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.chzz.core.app.Chzz;
import org.chzz.core.net.cookie.CookiesManager;
import org.chzz.demo.common.ConstantValues;
import org.chzz.demo.common.database.DatabaseManager;

import java.util.concurrent.TimeUnit;

import me.yokeyword.fragmentation.Fragmentation;
import okhttp3.OkHttpClient;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 上午10:58
 */
public class App extends Application {
    private static App app;
    public  static String Route="hxsrts";
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //听诊器  如要使用网络听诊器 必须自定义 okHttpClient 传入Chzz
        Stetho.initializeWithDefaults(this);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .followRedirects(false)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cookieJar(new CookiesManager(this))
                .build();

        //初始化所有配置
        Chzz.init(this)
                .withApiHost(ConstantValues.BASE_URL)
                .withIcon(new FontAwesomeModule())

                .withOkHttpClient(client)
                .configure();

        Fragmentation.builder()
                // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)// 更多查看wiki或demo
                .install();
        //初始化数据库
        DatabaseManager.getInstance().init(this);
    }
    public static App getInstance() {
        return app;
    }
}
