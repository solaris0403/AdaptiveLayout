package tony.adaptivelayout.utils;

import android.util.TypedValue;

/**
 * Created by zhy on 16/3/3.
 */
public class DimenUtils {
    private static int getComplexUnit(int data) {
        return TypedValue.COMPLEX_UNIT_MASK & (data >> TypedValue.COMPLEX_UNIT_SHIFT);
    }

    public static boolean isPxValue(TypedValue value) {
        if (value != null
                && value.type == TypedValue.TYPE_DIMENSION
                && getComplexUnit(value.data) == TypedValue.COMPLEX_UNIT_PX) {
            return true;
        }
        return false;
    }
}
