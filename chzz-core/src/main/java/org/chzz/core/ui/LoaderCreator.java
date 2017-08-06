package org.chzz.core.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午3:43
 */
public final class LoaderCreator {

    private static final WeakHashMap<String, Indicator> Loading_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (Loading_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            Loading_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(Loading_MAP.get(type));
        return avLoadingIndicatorView;
    }


    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuffer drawableClassName = new StringBuffer();
        if (!name.contains(".")) {
            final String defualtPackgeName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defualtPackgeName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
