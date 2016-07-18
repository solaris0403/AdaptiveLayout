package tony.adaptivelayout.attrs;

import android.view.View;

/**
 * Created by tony on 7/18/16.
 */
public class PaddingLeftAttr extends AutoAttr {
    public PaddingLeftAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.PADDING_LEFT;
    }

    @Override
    protected boolean defaultBaseWidth() {
        return true;
    }

    @Override
    protected void execute(View view, int value) {
        int l = value;
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
    }

    public static PaddingLeftAttr generate(int val, int flag) {
        PaddingLeftAttr paddingLeftAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                paddingLeftAttr = new PaddingLeftAttr(val, Attrs.PADDING_LEFT, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                paddingLeftAttr = new PaddingLeftAttr(val, 0, Attrs.PADDING_LEFT);
                break;
            case AutoAttr.BASE_DEFAULT:
                paddingLeftAttr = new PaddingLeftAttr(val, 0, 0);
                break;
        }
        return paddingLeftAttr;
    }
}
