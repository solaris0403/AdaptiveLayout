package tony.adaptivelayout.attrs;

import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by tony on 7/18/16.
 */
public class MinWidthAttr extends AutoAttr {
    public MinWidthAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.MIN_WIDTH;
    }

    @Override
    protected boolean onBaseWidth() {
        return true;
    }

    @Override
    protected void execute(View view, int value) {
        view.setMinimumWidth(value);
    }

    public static int getMinWidth(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            return view.getMinimumWidth();
        try {
            Field minWidth = view.getClass().getField("mMinWidth");
            minWidth.setAccessible(true);
            return (int) minWidth.get(view);
        } catch (Exception ignore) {
        }
        return 0;
    }

    public static MinWidthAttr generate(int val, int flag) {
        MinWidthAttr minWidthAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                minWidthAttr = new MinWidthAttr(val, Attrs.MIN_WIDTH, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                minWidthAttr = new MinWidthAttr(val, 0, Attrs.MIN_WIDTH);
                break;
            case AutoAttr.BASE_DEFAULT:
                minWidthAttr = new MinWidthAttr(val, 0, 0);
                break;
        }
        return minWidthAttr;
    }
}
