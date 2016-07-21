package tony.adaptivelayout.utils;

import android.util.Log;

import tony.adaptivelayout.config.AutoLayoutConfig;


/**
 * Created by zhy on 15/12/4.
 */
public class AutoUtils {
    private static final String TAG = "AutoUtils";

    /**
     * 获取百分比之后的宽度 向上取整
     *
     * @param width 设计的宽度
     * @return 目标高度
     */
    public static int getPercentWidthSize(int width) {
        int screenWidth = AutoLayoutConfig.getInstance().getScreenWidth();
        int designWidth = AutoLayoutConfig.getInstance().getDesignWidth();
        int res = width * screenWidth;
        int result;
        if (res % designWidth == 0) {
            result = res / designWidth;
        } else {
            result = res / designWidth + 1;
        }
        Log.i(TAG, "PxWidth:" + width + ",PercentWidth:" + result);
        return result;
    }

    /**
     * 获取百分比之后的高度 向上取整
     *
     * @param height 设计的高度
     * @return 目标高度
     */
    public static int getPercentHeightSize(int height) {
        int screenHeight = AutoLayoutConfig.getInstance().getScreenHeight();
        int designHeight = AutoLayoutConfig.getInstance().getDesignHeight();
        int res = height * screenHeight;
        int result;
        if (res % designHeight == 0) {
            result = res / designHeight;
        } else {
            result = res / designHeight + 1;
        }
        Log.i(TAG, "PxHeight:" + height + ",PercentHeight:" + result);
        return result;
    }
}
