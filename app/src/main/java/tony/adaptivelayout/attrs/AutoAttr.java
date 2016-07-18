package tony.adaptivelayout.attrs;

import android.view.View;

import tony.adaptivelayout.utils.AutoUtils;

/**
 * Created by tony on 7/18/16.
 */
public abstract class AutoAttr {
    public static final int BASE_WIDTH = 1;
    public static final int BASE_HEIGHT = 2;
    public static final int BASE_DEFAULT = 3;

    protected int mPxValue;
    protected int mBaseWidth;
    protected int mBaseHeight;

    public AutoAttr(int pxValue, int baseWidth, int baseHeight) {
        this.mPxValue = pxValue;
        this.mBaseWidth = baseWidth;
        this.mBaseHeight = baseHeight;
    }

    public void apply(View view) {
        int value;
        if (useDefaultValue()) {
            value = defaultBaseWidth() ? getPercentWidthSize() : getPercentHeightSize();
        } else if (baseWidth()) {
            value = getPercentWidthSize();
        } else {
            value = getPercentHeightSize();
        }
        if (value > 0) {
            value = Math.max(value, 1);
        }
        execute(view, value);
    }

    protected int getPercentWidthSize() {
        return AutoUtils.getPercentWidthSizeBigger(mPxValue);
    }

    protected int getPercentHeightSize() {
        return AutoUtils.getPercentHeightSizeBigger(mPxValue);
    }

    protected boolean baseWidth() {
        return contains(mBaseWidth, attrValue());
    }

    protected boolean useDefaultValue() {
        return !(contains(mBaseWidth, attrValue())) && !(contains(mBaseHeight, attrValue()));
    }

    protected boolean contains(int baseValue, int flag) {
        return (baseValue & flag) != 0;
    }


    protected abstract int attrValue();

    protected abstract boolean defaultBaseWidth();

    protected abstract void execute(View view, int value);
}
