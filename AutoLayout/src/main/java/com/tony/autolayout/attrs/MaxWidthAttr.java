package com.tony.autolayout.attrs;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tony on 7/18/16.
 */
public class MaxWidthAttr extends AutoAttr {
    public MaxWidthAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.MAX_WIDTH;
    }

    @Override
    protected boolean isBaseWidth() {
        return true;
    }

    @Override
    protected void execute(View view, int value) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("setMaxWidth", int.class);
            setMaxWidthMethod.invoke(view, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static int getMaxWidth(View view) {
        try {
            Method getMaxWidthMethod = view.getClass().getMethod("getMaxWidth");
            return (int) getMaxWidthMethod.invoke(view);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static MaxWidthAttr generate(int val, int flag) {
        MaxWidthAttr maxWidthAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                maxWidthAttr = new MaxWidthAttr(val, Attrs.MAX_WIDTH, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                maxWidthAttr = new MaxWidthAttr(val, 0, Attrs.MAX_WIDTH);
                break;
            case AutoAttr.BASE_DEFAULT:
                maxWidthAttr = new MaxWidthAttr(val, 0, 0);
                break;
        }
        return maxWidthAttr;
    }
}
