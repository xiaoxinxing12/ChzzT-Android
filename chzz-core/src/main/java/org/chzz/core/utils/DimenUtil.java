package org.chzz.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.chzz.core.app.Chzz;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午4:07
 */
public class DimenUtil {
    public static int getScreenWith() {
        final Resources resources = Chzz.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeitht() {
        final Resources resources = Chzz.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
