package org.chzz.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by copy on 2017/8/6.
 * Description:  配置文件
 * User: copy
 * Date: 2017-08-06
 * Time: 上午9:57
 */
public class Configurator {
    //存放所有的配置
    private static final HashMap<String, Object> CHZZ_CONFIGS = new HashMap<>();
    //存放图标文字库
    private static  final ArrayList<IconFontDescriptor> IOCNS=new ArrayList<>();
   //存放拦截器数据
    private static  final  ArrayList<Interceptor> INTERCEPTORS=new ArrayList<>();

    private Configurator() {
        CHZZ_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    /**
     *  单例模式
     * @return
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取 所有配置
     * @return
     */
    final HashMap<String, Object> getChzzConfigs() {
        return CHZZ_CONFIGS;
    }

    /**
     * 实例化配置 configurator
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 配置完成
     */
    public final void configure() {
        initIcons();
        CHZZ_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    /**
     * 配置host
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        CHZZ_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * 加入文字图标配置
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor){
        IOCNS.add(descriptor);
        return this;
    }

    /**
     * 拦截器
     * @param interceptor
     * @return
     */
    public final  Configurator whthInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        CHZZ_CONFIGS.put(ConfigType.INTERCEPTORS.name(),INTERCEPTORS);
        return this;
    }

    /**
     * 拦截器
     * @param interceptor
     * @return
     */
    public final  Configurator whthInterceptor(ArrayList<Interceptor> interceptor){
        INTERCEPTORS.addAll(interceptor);
        CHZZ_CONFIGS.put(ConfigType.INTERCEPTORS.name(),INTERCEPTORS);
        return this;
    }


    /**
     * 检查配置是否完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) CHZZ_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("配置没有完成");
        }
    }

    /**
     * 注解 告知编译器 我没有检查过的
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) CHZZ_CONFIGS.get(key.name());
    }

    private void initIcons(){
        if(IOCNS.size()>0){
            final Iconify.IconifyInitializer initializer=Iconify.with(IOCNS.get(0));
            for(int i=1;i<IOCNS.size();i++){
                initializer.with(IOCNS.get(i));
            }
        }
    }
}
