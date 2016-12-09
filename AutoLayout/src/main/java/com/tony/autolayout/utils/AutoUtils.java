package com.tony.autolayout.utils;

import android.util.Log;

import com.tony.autolayout.config.AutoLayoutConfig;


/**
 * 调整size
 */
public class AutoUtils {
    private static final String TAG = "AutoUtils";

    public static boolean contains(int value, int attr) {
        return (value & attr) != 0;
    }
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
        if (designWidth == 0){
            return width;
        }
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
