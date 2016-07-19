package tony.adaptivelayout.utils;

import android.view.View;

import tony.adaptivelayout.R;
import tony.adaptivelayout.attrs.Attrs;
import tony.adaptivelayout.attrs.AutoAttr;
import tony.adaptivelayout.config.AutoLayoutConfig;
import tony.adaptivelayout.widget.AutoLayoutInfo;


/**
 * Created by zhy on 15/12/4.
 */
public class AutoUtils {

    /**
     * 会直接将view的LayoutParams上设置的width，height直接进行百分比处理
     *
     * @param view
     */
    public static void auto(View view) {
        autoSize(view);
        autoPadding(view);
        autoMargin(view);
        autoTextSize(view, AutoAttr.BASE_DEFAULT);
    }

    /**
     * @param view
     * @param attrs #Attrs.WIDTH|Attrs.HEIGHT
     * @param base  AutoAttr.BASE_WIDTH|AutoAttr.BASE_HEIGHT|AutoAttr.BASE_DEFAULT
     */
    public static void auto(View view, int attrs, int base) {
        AutoLayoutInfo autoLayoutInfo = AutoLayoutInfo.getAttrFromView(view, attrs, base);
        if (autoLayoutInfo != null) {
            autoLayoutInfo.fillAttrs(view);
        }
    }

    public static void autoTextSize(View view) {
        auto(view, Attrs.TEXT_SIZE, AutoAttr.BASE_DEFAULT);
    }

    public static void autoTextSize(View view, int base) {
        auto(view, Attrs.TEXT_SIZE, base);
    }

    public static void autoMargin(View view) {
        auto(view, Attrs.MARGIN, AutoAttr.BASE_DEFAULT);
    }

    public static void autoMargin(View view, int base) {
        auto(view, Attrs.MARGIN, base);
    }

    public static void autoPadding(View view) {
        auto(view, Attrs.PADDING, AutoAttr.BASE_DEFAULT);
    }

    public static void autoPadding(View view, int base) {
        auto(view, Attrs.PADDING, base);
    }

    public static void autoSize(View view) {
        auto(view, Attrs.WIDTH | Attrs.HEIGHT, AutoAttr.BASE_DEFAULT);
    }

    public static void autoSize(View view, int base) {
        auto(view, Attrs.WIDTH | Attrs.HEIGHT, base);
    }

    public static boolean autoed(View view) {
        Object tag = view.getTag(R.id.id_tag_autolayout_size);
        if (tag != null) return true;
        view.setTag(R.id.id_tag_autolayout_size, "Just Identify");
        return false;
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
        if (res % designWidth == 0) {
            return res / designWidth;
        } else {
            return res / designWidth + 1;
        }
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
        if (res % designHeight == 0) {
            return res / designHeight;
        } else {
            return res / designHeight + 1;
        }
    }
}
