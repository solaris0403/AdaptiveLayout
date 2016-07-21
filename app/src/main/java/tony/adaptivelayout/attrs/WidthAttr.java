package tony.adaptivelayout.attrs;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tony on 7/18/16.
 */
public class WidthAttr extends AutoAttr {
    public WidthAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.WIDTH;
    }

    @Override
    protected boolean isBaseWidth() {
        return true;
    }

    @Override
    protected void execute(View view, int value) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = value;
    }

    public static WidthAttr generate(int value, int base) {
        WidthAttr widthAttr = null;
        switch (base) {
            case AutoAttr.BASE_WIDTH:
                widthAttr = new WidthAttr(value, Attrs.WIDTH, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                widthAttr = new WidthAttr(value, 0, Attrs.WIDTH);
                break;
            case AutoAttr.BASE_DEFAULT:
                widthAttr = new WidthAttr(value, 0, 0);
                break;
        }
        return widthAttr;
    }
}
