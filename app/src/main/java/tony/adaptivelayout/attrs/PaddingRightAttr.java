package tony.adaptivelayout.attrs;

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
    protected boolean onBaseWidth() {
        return true;
    }

    @Override
    protected void execute(View view, int value) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = value;
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
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
