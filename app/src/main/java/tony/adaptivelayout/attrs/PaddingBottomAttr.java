package tony.adaptivelayout.attrs;

import android.view.View;

/**
 * Created by tony on 7/18/16.
 */
public class PaddingBottomAttr extends AutoAttr {
    public PaddingBottomAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.PADDING_BOTTOM;
    }

    @Override
    protected boolean isBaseWidth() {
        return false;
    }

    @Override
    protected void execute(View view, int value) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), value);
    }

    public static PaddingBottomAttr generate(int val, int flag) {
        PaddingBottomAttr paddingBottomAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                paddingBottomAttr = new PaddingBottomAttr(val, Attrs.PADDING_BOTTOM, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                paddingBottomAttr = new PaddingBottomAttr(val, 0, Attrs.PADDING_BOTTOM);
                break;
            case AutoAttr.BASE_DEFAULT:
                paddingBottomAttr = new PaddingBottomAttr(val, 0, 0);
                break;
        }
        return paddingBottomAttr;
    }
}
