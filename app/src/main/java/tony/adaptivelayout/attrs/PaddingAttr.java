package tony.adaptivelayout.attrs;

import android.view.View;

/**
 * Created by tony on 7/18/16.
 */
public class PaddingAttr extends AutoAttr {
    public PaddingAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.PADDING;
    }

    @Override
    protected boolean isBaseWidth() {
        return false;
    }

    @Override
    public void apply(View view) {
        int l, t, r, b;
        if (useDefaultValue()) {
            l = r = getPercentWidthSize();
            t = b = getPercentHeightSize();
            view.setPadding(l, t, r, b);
            return;
        }
        super.apply(view);
    }

    @Override
    protected void execute(View view, int value) {
        view.setPadding(value, value, value, value);
    }
}
