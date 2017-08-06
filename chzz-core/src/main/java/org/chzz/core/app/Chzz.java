package org.chzz.core.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 上午9:56
 */
public final class Chzz {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXY.name(), context.getApplicationContext());
        return Configurator.getInstance();

    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }
    public static <T> T getConfiguration(Enum<ConfigType>  key) {
        return getConfigurator().getConfiguration(key);
    }
    public static HashMap<String, Object> getConfigurations() {

        return Configurator.getInstance().getChzzConfigs();
    }


    public  static  Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXY.name());
    }


}
