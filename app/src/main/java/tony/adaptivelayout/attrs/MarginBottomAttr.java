package tony.adaptivelayout.attrs;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tony on 7/18/16.
 */
public class MarginBottomAttr extends AutoAttr {
    public MarginBottomAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.MARGIN_BOTTOM;
    }

    @Override
    protected boolean defaultBaseWidth() {
        return false;
    }

    @Override
    protected void execute(View view, int value) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.bottomMargin = value;
    }

    public static MarginBottomAttr generate(int val, int flag) {
        MarginBottomAttr marginBottomAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                marginBottomAttr = new MarginBottomAttr(val, Attrs.MARGIN_BOTTOM, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                marginBottomAttr = new MarginBottomAttr(val, 0, Attrs.MARGIN_BOTTOM);
                break;
            case AutoAttr.BASE_DEFAULT:
                marginBottomAttr = new MarginBottomAttr(val, 0, 0);
                break;
        }
        return marginBottomAttr;
    }
}
