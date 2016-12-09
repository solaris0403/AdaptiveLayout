package com.tony.autolayout.attrs;

import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by tony on 7/18/16.
 */
public class MinHeightAttr extends AutoAttr {
    public MinHeightAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.MIN_HEIGHT;
    }

    @Override
    protected boolean isBaseWidth() {
        return false;
    }

    @Override
    protected void execute(View view, int value) {
        view.setMinimumHeight(value);
    }

    public static int getMinHeight(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return view.getMinimumHeight();
        } else {
            try {
                Field minHeight = view.getClass().getField("mMinHeight");
                minHeight.setAccessible(true);
                return (int) minHeight.get(view);
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public static MinHeightAttr generate(int val, int flag) {
        MinHeightAttr minHeightAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                minHeightAttr = new MinHeightAttr(val, Attrs.MIN_HEIGHT, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                minHeightAttr = new MinHeightAttr(val, 0, Attrs.MIN_HEIGHT);
                break;
            case AutoAttr.BASE_DEFAULT:
                minHeightAttr = new MinHeightAttr(val, 0, 0);
                break;
        }
        return minHeightAttr;
    }
}
