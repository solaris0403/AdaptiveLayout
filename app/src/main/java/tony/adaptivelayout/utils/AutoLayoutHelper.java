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
import tony.adaptivelayout.widget.AutoLayoutInfo;

public class AutoLayoutHelper {
    private final ViewGroup mHost;

    private static final int[] LL = new int[]
            { //
                    android.R.attr.textSize,
                    android.R.attr.padding,//
                    android.R.attr.paddingLeft,//
                    android.R.attr.paddingTop,//
                    android.R.attr.paddingRight,//
                    android.R.attr.paddingBottom,//
                    android.R.attr.layout_width,//
                    android.R.attr.layout_height,//
                    android.R.attr.layout_margin,//
                    android.R.attr.layout_marginLeft,//
                    android.R.attr.layout_marginTop,//
                    android.R.attr.layout_marginRight,//
                    android.R.attr.layout_marginBottom,//
                    android.R.attr.maxWidth,//
                    android.R.attr.maxHeight,//
                    android.R.attr.minWidth,//
                    android.R.attr.minHeight,//16843072
            };

    private static final int INDEX_TEXT_SIZE = 0;
    private static final int INDEX_PADDING = 1;
    private static final int INDEX_PADDING_LEFT = 2;
    private static final int INDEX_PADDING_TOP = 3;
    private static final int INDEX_PADDING_RIGHT = 4;
    private static final int INDEX_PADDING_BOTTOM = 5;
    private static final int INDEX_WIDTH = 6;
    private static final int INDEX_HEIGHT = 7;
    private static final int INDEX_MARGIN = 8;
    private static final int INDEX_MARGIN_LEFT = 9;
    private static final int INDEX_MARGIN_TOP = 10;
    private static final int INDEX_MARGIN_RIGHT = 11;
    private static final int INDEX_MARGIN_BOTTOM = 12;
    private static final int INDEX_MAX_WIDTH = 13;
    private static final int INDEX_MAX_HEIGHT = 14;
    private static final int INDEX_MIN_WIDTH = 15;
    private static final int INDEX_MIN_HEIGHT = 16;


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
     * 获取xml中的属性信息
     * @param context
     * @param attrs
     * @return
     */
    public static AutoLayoutInfo getAutoLayoutInfo(Context context, AttributeSet attrs) {
        AutoLayoutInfo info = new AutoLayoutInfo();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoLayout_Layout);
        int baseWidth = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_basewidth, 0);
        int baseHeight = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_baseheight, 0);
        a.recycle();

        //获取原有设置的值
        TypedArray array = context.obtainStyledAttributes(attrs, LL);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int index = array.getIndex(i);
            if (!DimenUtils.isPxVal(array.peekValue(index))) continue;
            int pxValue;
            try {
                pxValue = array.getDimensionPixelOffset(index, 0);
            } catch (Exception ignore)//not dimension
            {
                continue;
            }
            switch (index) {
                case INDEX_TEXT_SIZE:
                    info.addAttr(new TextSizeAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING:
                    info.addAttr(new PaddingAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_LEFT:
                    info.addAttr(new PaddingLeftAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_TOP:
                    info.addAttr(new PaddingTopAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_RIGHT:
                    info.addAttr(new PaddingRightAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_BOTTOM:
                    info.addAttr(new PaddingBottomAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_WIDTH:
                    info.addAttr(new WidthAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_HEIGHT:
                    info.addAttr(new HeightAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN:
                    info.addAttr(new MarginAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_LEFT:
                    info.addAttr(new MarginLeftAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_TOP:
                    info.addAttr(new MarginTopAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_RIGHT:
                    info.addAttr(new MarginRightAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_BOTTOM:
                    info.addAttr(new MarginBottomAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MAX_WIDTH:
                    info.addAttr(new MaxWidthAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MAX_HEIGHT:
                    info.addAttr(new MaxHeightAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MIN_WIDTH:
                    info.addAttr(new MinWidthAttr(pxValue, baseWidth, baseHeight));
                    break;
                case INDEX_MIN_HEIGHT:
                    info.addAttr(new MinHeightAttr(pxValue, baseWidth, baseHeight));
                    break;
            }
        }
        array.recycle();
        return info;
    }

    public interface AutoLayoutParams {
        AutoLayoutInfo getAutoLayoutInfo();
    }
}
