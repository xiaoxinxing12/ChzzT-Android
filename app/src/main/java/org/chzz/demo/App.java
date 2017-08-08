package org.chzz.demo;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.chzz.core.app.Chzz;

import me.yokeyword.fragmentation.Fragmentation;

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
                .configure();

        Fragmentation.builder()
                // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)// 更多查看wiki或demo
                .install();
    }
}
