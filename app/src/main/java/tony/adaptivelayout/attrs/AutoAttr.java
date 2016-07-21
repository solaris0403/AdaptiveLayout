package tony.adaptivelayout.attrs;

import android.view.View;

import tony.adaptivelayout.utils.AutoUtils;

/**
 * 所有属性的父类 针对每一个属性重新计算
 */
public abstract class AutoAttr {
    //基准
    public static final byte BASE_WIDTH = 0x01;
    public static final byte BASE_HEIGHT = 0x02;
    public static final byte BASE_DEFAULT = 0x03;

    //设置的值 与基准值
    protected int mPxValue;
    protected int mBaseWidth;
    protected int mBaseHeight;

    public AutoAttr(int pxValue, int baseWidth, int baseHeight) {
        this.mPxValue = pxValue;
        this.mBaseWidth = baseWidth;
        this.mBaseHeight = baseHeight;
    }

    //针对不同的方案 设置属性值
    public void apply(View view) {
        int value;
        if (useDefaultValue()) {
            value = isBaseWidth() ? getPercentWidthSize() : getPercentHeightSize();
        } else if (baseWidth()) {
            value = getPercentWidthSize();
        } else {
            value = getPercentHeightSize();
        }
        execute(view, value);
    }

    protected int getPercentWidthSize() {
        return AutoUtils.getPercentWidthSize(mPxValue);
    }

    protected int getPercentHeightSize() {
        return AutoUtils.getPercentHeightSize(mPxValue);
    }

    //设置的是否已宽为基准
    protected boolean baseWidth() {
        return contains(mBaseWidth, attrValue());
    }

    //宽高都没有设置基准 默认值
    protected boolean useDefaultValue() {
        return !contains(mBaseWidth, attrValue()) && !contains(mBaseHeight, attrValue());
    }

    protected boolean contains(int baseValue, int flag) {
        return (baseValue & flag) != 0;
    }


    /**
     * 返回当前属性
     *
     * @return
     */
    protected abstract int attrValue();

    /**
     * 该属性是否以宽为基准
     *
     * @return
     */
    protected abstract boolean isBaseWidth();

    /**
     * 针对当前view重新设置该属性的值
     *
     * @param view
     * @param value
     */
    protected abstract void execute(View view, int value);
}
