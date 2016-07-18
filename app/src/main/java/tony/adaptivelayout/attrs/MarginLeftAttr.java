package tony.adaptivelayout.attrs;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tony on 7/18/16.
 */
public class MarginLeftAttr extends AutoAttr {
    public MarginLeftAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.MARGIN_LEFT;
    }

    @Override
    protected boolean defaultBaseWidth() {
        return true;
    }

    @Override
    protected void execute(View view, int value) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.leftMargin = value;
    }

    public static MarginLeftAttr generate(int val, int flag) {
        MarginLeftAttr marginLeftAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                marginLeftAttr = new MarginLeftAttr(val, Attrs.MARGIN_LEFT, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                marginLeftAttr = new MarginLeftAttr(val, 0, Attrs.MARGIN_LEFT);
                break;
            case AutoAttr.BASE_DEFAULT:
                marginLeftAttr = new MarginLeftAttr(val, 0, 0);
                break;
        }
        return marginLeftAttr;
    }
}
