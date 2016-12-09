package com.tony.autolayout.widget.auto;

import android.view.View;

import com.tony.autolayout.R;
import com.tony.autolayout.attrs.Attrs;
import com.tony.autolayout.attrs.AutoAttr;
import com.tony.autolayout.utils.AutoLayoutInfo;


/**
 * Created by tony on 7/21/16.
 */
public class AutoLayout {
    private static final String TAG = "AutoLayout";

    public static void auto(View view) {
        autoSize(view);
        autoMargin(view);
        autoPadding(view);
        autoTextSize(view);
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

    public static void autoSize(View view) {
        auto(view, Attrs.WIDTH | Attrs.HEIGHT, AutoAttr.BASE_DEFAULT);
    }

    public static void autoSize(View view, int base) {
        auto(view, Attrs.WIDTH | Attrs.HEIGHT, base);
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

    public static void autoTextSize(View view) {
        auto(view, Attrs.TEXT_SIZE, AutoAttr.BASE_DEFAULT);
    }

    public static void autoTextSize(View view, int base) {
        auto(view, Attrs.TEXT_SIZE, base);
    }

    public static boolean autoed(View view) {
        Object tag = view.getTag(R.id.id_tag_autolayout_size);
        if (tag != null) return true;
        view.setTag(R.id.id_tag_autolayout_size, "Just Identify");
        return false;
    }
}
