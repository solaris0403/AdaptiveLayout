package tony.adaptivelayout.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import tony.adaptivelayout.utils.ScreenUtils;

/**
 * Created by tony on 7/18/16.
 */
public class AutoLayoutConfig {
    private static final String TAG = "AutoLayoutConfig";
    private static AutoLayoutConfig mInstance = new AutoLayoutConfig();

    private static final String KEY_DESIGN_WIDTH = "design_width";
    private static final String KEY_DESIGN_HEIGHT = "design_height";

    private int mScreenWidth;
    private int mScreenHeight;

    private int mDesignWidth;
    private int mDesignHeight;

    //默认没有菜单栏(status+content)
    private boolean useMenuBarSize = false;

    private AutoLayoutConfig() {
    }

    public static AutoLayoutConfig getInstance() {
        return mInstance;
    }

    public void checkParams() {
        if (mDesignWidth <= 0 || mDesignHeight <= 0) {
            throw new RuntimeException("you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.");
        }
    }

    public AutoLayoutConfig useMenuBarSize() {
        useMenuBarSize = true;
        return this;
    }

    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public int getDesignWidth() {
        return mDesignWidth;
    }

    public int getDesignHeight() {
        return mDesignHeight;
    }

    public void init(Context context) {
        getMetaData(context);
        int[] screenSize = ScreenUtils.getScreenSize(context, useMenuBarSize);
        mScreenWidth = screenSize[0];
        mScreenHeight = screenSize[1];
        Log.i(TAG, "mScreenWidth:" + mScreenWidth + ",mScreenHeight:" + mScreenHeight);
    }

    //获取设置的设计尺寸 通常为int值 如果有人设置为float 可以去削他
    private void getMetaData(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                mDesignWidth = (int) applicationInfo.metaData.get(KEY_DESIGN_WIDTH);
                mDesignHeight = (int) applicationInfo.metaData.get(KEY_DESIGN_HEIGHT);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.", e);
        }
        Log.i(TAG, "mDesignWidth:" + mDesignWidth + ",mDesignHeight:" + mDesignHeight);
    }
}
