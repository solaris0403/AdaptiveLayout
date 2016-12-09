package com.tony.autolayout.attrs;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tony on 7/18/16.
 */
public class MaxHeightAttr extends AutoAttr {
    public MaxHeightAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.MAX_HEIGHT;
    }

    @Override
    protected boolean isBaseWidth() {
        return false;
    }

    @Override
    protected void execute(View view, int value) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("setMaxHeight", int.class);
            setMaxWidthMethod.invoke(view, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static int getMaxHeight(View view) {
        try {
            Method getMaxHeightMethod = view.getClass().getMethod("getMaxHeight");
            return (int) getMaxHeightMethod.invoke(view);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static MaxHeightAttr generate(int val, int flag) {
        MaxHeightAttr maxHeightAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                maxHeightAttr = new MaxHeightAttr(val, Attrs.MAX_HEIGHT, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                maxHeightAttr = new MaxHeightAttr(val, 0, Attrs.MAX_HEIGHT);
                break;
            case AutoAttr.BASE_DEFAULT:
                maxHeightAttr = new MaxHeightAttr(val, 0, 0);
                break;
        }
        return maxHeightAttr;
    }
}
