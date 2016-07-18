package tony.adaptivelayout.attrs;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tony on 7/18/16.
 */
public class TextSizeAttr extends AutoAttr {
    public TextSizeAttr(int pxValue, int baseWidth, int baseHeight) {
        super(pxValue, baseWidth, baseHeight);
    }

    @Override
    protected int attrValue() {
        return Attrs.TEXT_SIZE;
    }

    @Override
    protected boolean defaultBaseWidth() {
        return false;
    }

    @Override
    protected void execute(View view, int value) {
        if (!(view instanceof TextView)) {
            return;
        }
        ((TextView) view).setIncludeFontPadding(false);
        ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, value);
    }

    public static TextSizeAttr generate(int val, int flag) {
        TextSizeAttr textSizeAttr = null;
        switch (flag) {
            case AutoAttr.BASE_WIDTH:
                textSizeAttr = new TextSizeAttr(val, Attrs.TEXT_SIZE, 0);
                break;
            case AutoAttr.BASE_HEIGHT:
                textSizeAttr = new TextSizeAttr(val, 0, Attrs.TEXT_SIZE);
                break;
            case AutoAttr.BASE_DEFAULT:
                textSizeAttr = new TextSizeAttr(val, 0, 0);
                break;
        }
        return textSizeAttr;
    }
}
