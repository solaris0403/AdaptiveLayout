package com.tony.autolayout.attrs;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tony on 7/18/16.
 */
public class MarginTopAttr extends AutoAttr {
    public MarginTopAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.MARGIN_TOP;
    }

    @Override
    protected boolean isBaseWidth() {
        return false;
    }

    @Override
    protected void execute(View view, int value) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            lp.topMargin = value;
        }
    }

    public static MarginTopAttr generate(int val, int base) {
        MarginTopAttr marginTopAttr = null;
        switch (base) {
            case AutoAttr.BASE_WIDTH:
                marginTopAttr = new MarginTopAttr(val, Attrs.MARGIN_TOP, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                marginTopAttr = new MarginTopAttr(val, 0, Attrs.MARGIN_TOP);
                break;
            case AutoAttr.BASE_DEFAULT:
                marginTopAttr = new MarginTopAttr(val, 0, 0);
                break;
        }
        return marginTopAttr;
    }
}
