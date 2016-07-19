package tony.adaptivelayout.attrs;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tony on 7/18/16.
 */
public class MarginRightAttr extends AutoAttr {
    public MarginRightAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.MARGIN_RIGHT;
    }

    @Override
    protected boolean onBaseWidth() {
        return true;
    }

    @Override
    protected void execute(View view, int value) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.rightMargin = value;
    }
    public static MarginRightAttr generate(int val, int flag)
    {
        MarginRightAttr marginRightAttr = null;
        switch (flag)
        {
            case AutoAttr.BASE_WIDTH:
                marginRightAttr = new MarginRightAttr(val, Attrs.MARGIN_RIGHT, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                marginRightAttr = new MarginRightAttr(val, 0, Attrs.MARGIN_RIGHT);
                break;
            case AutoAttr.BASE_DEFAULT:
                marginRightAttr = new MarginRightAttr(val, 0, 0);
                break;
        }
        return marginRightAttr;
    }
}
