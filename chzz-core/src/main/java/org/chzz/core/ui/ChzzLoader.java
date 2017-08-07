package org.chzz.core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import org.chzz.core.R;
import org.chzz.core.utils.DimenUtil;

import java.util.ArrayList;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 下午3:56
 */
public class ChzzLoader {
    private static final int Loader_SIZE_SCALE = 8;
    private static final int LOADER_OFFEST_SCALE = 10;
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);
        int deviceWidth = DimenUtil.getScreenWith();
        int deviceHeight = DimenUtil.getScreenHeitht();
        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / Loader_SIZE_SCALE;
            lp.height = deviceHeight / Loader_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFEST_SCALE;
            lp.gravity = Gravity.CENTER;

        }
        LOADERS.add(dialog);
        dialog.show();
    }

    /**
     * 显示dialog Loading
     *
     * @param context
     */
    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    /**
     * 关闭所有dialog
     */
    public static void stopLoading() {
        if (null != LOADERS && LOADERS.size() > 0) {
            for (AppCompatDialog dialog : LOADERS) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }

}
