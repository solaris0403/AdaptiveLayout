/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tony.adaptivelayout.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import tony.adaptivelayout.R;
import tony.adaptivelayout.attrs.Attrs;
import tony.adaptivelayout.attrs.HeightAttr;
import tony.adaptivelayout.attrs.MarginAttr;
import tony.adaptivelayout.attrs.MarginBottomAttr;
import tony.adaptivelayout.attrs.MarginLeftAttr;
import tony.adaptivelayout.attrs.MarginRightAttr;
import tony.adaptivelayout.attrs.MarginTopAttr;
import tony.adaptivelayout.attrs.MaxHeightAttr;
import tony.adaptivelayout.attrs.MaxWidthAttr;
import tony.adaptivelayout.attrs.MinHeightAttr;
import tony.adaptivelayout.attrs.MinWidthAttr;
import tony.adaptivelayout.attrs.PaddingAttr;
import tony.adaptivelayout.attrs.PaddingBottomAttr;
import tony.adaptivelayout.attrs.PaddingLeftAttr;
import tony.adaptivelayout.attrs.PaddingRightAttr;
import tony.adaptivelayout.attrs.PaddingTopAttr;
import tony.adaptivelayout.attrs.TextSizeAttr;
import tony.adaptivelayout.attrs.WidthAttr;
import tony.adaptivelayout.config.AutoLayoutConfig;

public class AutoLayoutHelper {
    private final ViewGroup mHost;

    private static final int[] LL = new int[]
            {
                    android.R.attr.layout_width,
                    android.R.attr.layout_height,
                    android.R.attr.layout_margin,
                    android.R.attr.layout_marginLeft,
                    android.R.attr.layout_marginTop,
                    android.R.attr.layout_marginRight,
                    android.R.attr.layout_marginBottom,
                    android.R.attr.padding,
                    android.R.attr.paddingLeft,
                    android.R.attr.paddingTop,
                    android.R.attr.paddingRight,
                    android.R.attr.paddingBottom,
                    android.R.attr.minWidth,
                    android.R.attr.maxWidth,
                    android.R.attr.minHeight,
                    android.R.attr.maxHeight,
                    android.R.attr.textSize
            };
    private static final int INDEX_WIDTH = 0;
    private static final int INDEX_HEIGHT = 1;
    private static final int INDEX_MARGIN = 2;
    private static final int INDEX_MARGIN_LEFT = 3;
    private static final int INDEX_MARGIN_TOP = 4;
    private static final int INDEX_MARGIN_RIGHT = 5;
    private static final int INDEX_MARGIN_BOTTOM = 6;
    private static final int INDEX_PADDING = 7;
    private static final int INDEX_PADDING_LEFT = 8;
    private static final int INDEX_PADDING_TOP = 9;
    private static final int INDEX_PADDING_RIGHT = 10;
    private static final int INDEX_PADDING_BOTTOM = 11;
    private static final int INDEX_MIN_WIDTH = 12;
    private static final int INDEX_MAX_WIDTH = 13;
    private static final int INDEX_MIN_HEIGHT = 14;
    private static final int INDEX_MAX_HEIGHT = 15;
    private static final int INDEX_TEXT_SIZE = 16;


    /**
     * move to other place?
     */
    private static AutoLayoutConfig mAutoLayoutConfig;

    public AutoLayoutHelper(ViewGroup host) {
        mHost = host;
        if (mAutoLayoutConfig == null) {
            initAutoLayoutConfig(host);
        }
    }

    private void initAutoLayoutConfig(ViewGroup host) {
        mAutoLayoutConfig = AutoLayoutConfig.getInstance();
        mAutoLayoutConfig.init(host.getContext());
    }


    //重新调整每一个子元素的宽高
    public void adjustChildren() {
        AutoLayoutConfig.getInstance().checkParams();
        for (int i = 0; i < mHost.getChildCount(); i++) {
            View view = mHost.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params instanceof AutoLayoutParams) {
                AutoLayoutInfo info = ((AutoLayoutParams) params).getAutoLayoutInfo();
                if (info != null) {
                    info.fillAttrs(view);
                }
            }
        }
    }

    /**
     * 获取元素中的属性信息
     *
     * @param context
     * @param attrs
     * @return
     */
    public static AutoLayoutInfo getAutoLayoutInfo(Context context, AttributeSet attrs) {
        AutoLayoutInfo info = new AutoLayoutInfo();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoLayout);
        int baseWidth = a.getInt(R.styleable.AutoLayout_layout_auto_basewidth, 0);
        int baseHeight = a.getInt(R.styleable.AutoLayout_layout_auto_baseheight, 0);
        boolean autoEnable = a.getBoolean(R.styleable.AutoLayout_layout_auto_enable, true);
        int invalidate = a.getInt(R.styleable.AutoLayout_layout_auto_invalidate, 0);
        a.recycle();

        if (!autoEnable) {
            return info;
        }

        /**
         * 当enable、invalidate、base有同时出现的情况以下列优先级为准
         * 全局禁用(enable=false)>局部失效(invalidate)>基准设置(base)
         * 如果basewidth和invalidate(width)并存,则basewidth失效
         */
        TypedArray array = context.obtainStyledAttributes(attrs, LL);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int index = array.getIndex(i);
            if (!DimenUtils.isPxValue(array.peekValue(index))) continue;//如果不是px，则不处理
            int pxValue;
            try {
                pxValue = array.getDimensionPixelOffset(index, 0);//获取取到的px值
            } catch (Exception exception)
            {
                continue;
            }
            switch (index) {
                case INDEX_WIDTH:
                    if (contains(invalidate, Attrs.WIDTH)) break;
                    info.addAttr(new WidthAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_HEIGHT:
                    if (contains(invalidate, Attrs.HEIGHT)) break;
                    info.addAttr(new HeightAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN:
                    if (contains(invalidate, Attrs.MARGIN)) break;
                    info.addAttr(new MarginAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_LEFT:
                    if (contains(invalidate, Attrs.MARGIN_LEFT)) break;
                    info.addAttr(new MarginLeftAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_TOP:
                    if (contains(invalidate, Attrs.MARGIN_TOP)) break;
                    info.addAttr(new MarginTopAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_RIGHT:
                    if (contains(invalidate, Attrs.MARGIN_RIGHT)) break;
                    info.addAttr(new MarginRightAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_BOTTOM:
                    if (contains(invalidate, Attrs.MARGIN_BOTTOM)) break;
                    info.addAttr(new MarginBottomAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING:
                    if (contains(invalidate, Attrs.PADDING)) break;
                    info.addAttr(new PaddingAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_LEFT:
                    if (contains(invalidate, Attrs.PADDING_LEFT)) break;
                    info.addAttr(new PaddingLeftAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_TOP:
                    if (contains(invalidate, Attrs.PADDING_TOP)) break;
                    info.addAttr(new PaddingTopAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_RIGHT:
                    if (contains(invalidate, Attrs.PADDING_RIGHT)) break;
                    info.addAttr(new PaddingRightAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_BOTTOM:
                    if (contains(invalidate, Attrs.PADDING_BOTTOM)) break;
                    info.addAttr(new PaddingBottomAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MIN_WIDTH:
                    if (contains(invalidate, Attrs.MIN_WIDTH)) break;
                    info.addAttr(new MinWidthAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MAX_WIDTH:
                    if (contains(invalidate, Attrs.MAX_WIDTH)) break;
                    info.addAttr(new MaxWidthAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MIN_HEIGHT:
                    if (contains(invalidate, Attrs.MIN_HEIGHT)) break;
                    info.addAttr(new MinHeightAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MAX_HEIGHT:
                    if (contains(invalidate, Attrs.MAX_HEIGHT)) break;
                    info.addAttr(new MaxHeightAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_TEXT_SIZE:
                    if (contains(invalidate, Attrs.TEXT_SIZE)) break;
                    info.addAttr(new TextSizeAttr(pxValue, baseWidth, baseHeight));
                    break;
            }
        }
        array.recycle();
        return info;
    }

    private static boolean contains(int value, int attr) {
        return (value & attr) != 0;
    }

    public interface AutoLayoutParams {
        AutoLayoutInfo getAutoLayoutInfo();
    }
}
