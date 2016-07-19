package tony.adaptivelayout.attrs;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tony on 7/18/16.
 */
public class HeightAttr extends AutoAttr {
    public HeightAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.HEIGHT;
    }

    @Override
    protected boolean onBaseWidth() {
        return false;
    }

    @Override
    protected void execute(View view, int value) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = value;
    }

    public static HeightAttr generate(int value, int flag) {
        HeightAttr heightAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                heightAttr = new HeightAttr(value, Attrs.HEIGHT, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                heightAttr = new HeightAttr(value, 0, Attrs.HEIGHT);
                break;
            case AutoAttr.BASE_DEFAULT:
                heightAttr = new HeightAttr(value, 0, 0);
                break;
        }
        return heightAttr;
    }
}
