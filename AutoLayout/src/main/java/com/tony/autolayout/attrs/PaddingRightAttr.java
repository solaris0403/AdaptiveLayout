package com.tony.autolayout.attrs;

import android.view.View;

/**
 * Created by tony on 7/18/16.
 */
public class PaddingRightAttr extends AutoAttr{
    public PaddingRightAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.PADDING_RIGHT;
    }

    @Override
    protected boolean isBaseWidth() {
        return true;
    }

    @Override
    protected void execute(View view, int value) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), value, view.getPaddingBottom());
    }

    public static PaddingRightAttr generate(int val, int flag)
    {
        PaddingRightAttr paddingRightAttr = null;
        switch (flag)
        {
            case AutoAttr.BASE_WIDTH:
                paddingRightAttr = new PaddingRightAttr(val, Attrs.PADDING_RIGHT, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                paddingRightAttr = new PaddingRightAttr(val, 0, Attrs.PADDING_RIGHT);
                break;
            case AutoAttr.BASE_DEFAULT:
                paddingRightAttr = new PaddingRightAttr(val, 0, 0);
                break;
        }
        return paddingRightAttr;
    }
}
