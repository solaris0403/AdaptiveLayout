package com.tony.autolayout.attrs;

import android.view.View;

/**
 * Created by tony on 7/18/16.
 */
public class PaddingTopAttr extends AutoAttr {
    public PaddingTopAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.PADDING_TOP;
    }

    @Override
    protected boolean isBaseWidth() {
        return false;
    }

    @Override
    protected void execute(View view, int value) {
        view.setPadding(view.getPaddingLeft(), value, view.getPaddingRight(), view.getPaddingBottom());
    }

    public static PaddingTopAttr generate(int val, int flag) {
        PaddingTopAttr paddingTopAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                paddingTopAttr = new PaddingTopAttr(val, Attrs.PADDING_TOP, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                paddingTopAttr = new PaddingTopAttr(val, 0, Attrs.PADDING_TOP);
                break;
            case AutoAttr.BASE_DEFAULT:
                paddingTopAttr = new PaddingTopAttr(val, 0, 0);
                break;
        }
        return paddingTopAttr;
    }
}
