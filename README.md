# 屏幕适配

### dp
>
Density-independent pixel (dp)独立像素密度。标准是160dip.即1dp对应1个pixel，计算公式如：px = dp * (dpi / 160)，屏幕密度越大，1dp对应 的像素点越多。
上面的公式中有个dpi，dpi为DPI是Dots Per Inch（每英寸所打印的点数），也就是当设备的dpi为160的时候1px=1dp；

dp能够让同一数值在不同的分辨率展示出大致相同的尺寸大小。但是当设备的尺寸差异较大的时候，就无能为力了。

### Google官方设计的适配方案
1. 资源图片

```
res/drawable        (default)
res/drawable-ldpi/  (240x320 and nearer resolution)
res/drawable-mdpi/  (320x480 and nearer resolution)
res/drawable-hdpi/  (480x800, 540x960 and nearer resolution)
res/drawable-xhdpi/  (720x1280 - Samsung S3, Micromax Canvas HD etc)
res/drawable-xxhdpi/ (1080x1920 - Samsung S4, HTC one, Nexus 5, etc)
```

2. 布局的适配

```
layout-large-mdpi   (1024x600)
layout-large-tvdpi  (800x1280)
layout-large-xhdpi  (1200x1920)
layout-xlarge-mdpi  (1280x800)
layout-xlarge-xhdpi (2560x1600)
//或者
layout-640x360
layout-800x480
```
3. 尺寸适配

```
res/values/dimens.xml(default)
res/values-ldpi/dimens.xml   (240x320 and nearer resolution)
res/values-mdpi/dimens.xml   (320x480 and nearer resolution)
res/values-hdpi/dimens.xml   (480x800, 540x960 and nearer resolution)
res/values-xhdpi/dimens.xml  (720x1280 - Samsung S3, Micromax Canvas HD, etc)
res/values-xxhdpi/dimens.xml (1080x1920 - Samsung S4, HTC one, etc)
res/values-large/dimens.xml  (480x800)

res/values-large-mdpi/dimens.xml (600x1024)
res/values-sw600dp/dimens.xml  (600x1024)
res/values-sw720dp/dimens.xml  (800x1280)
res/values-xlarge-xhdpi/dimens.xml (2560x1600 - Nexus 10")
res/values-large-xhdpi/dimens.xml  (1200x1920 - Nexus 7"(latest))
```

尽管有这么多适配方案，如果不闲麻烦的话，倒是可以最大限度上解决各种屏幕的适配。但是，这知识最大限度上的，还是会有没有考虑到的情况，或者考虑到了，可是却需要取舍，不能完美解决。

### 百分比
1. 代码去动态计算（很多人直接pass了，太麻烦）
2. 利用weight（weight必须依赖Linearlayout，而且并不能适用于任何场景）

#### 百分比布局
官方出品的百分比布局：com.android.support:percent。
* 两种布局
PercentRelativeLayout、PercentFrameLayout
* 支持的属性

```
layout_widthPercent、layout_heightPercent、
layout_marginPercent、layout_marginLeftPercent、
layout_marginTopPercent、layout_marginRightPercent、
layout_marginBottomPercent、layout_marginStartPercent、layout_marginEndPercent
```

* 使用
PercentRelativeLayout:

```
<?xml version="1.0" encoding="utf-8"?>
<tony.adaptivelayout.widget.percent.PercentRelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="tony.adaptivelayout.MainActivity">

    <TextView
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="Hello World!"
        app:layout_heightPercent="80%"
        app:layout_widthPercent="80%"/>
</tony.adaptivelayout.widget.percent.PercentRelativeLayout>
```

但是在使用过程中，觉得存在一些场景无法得到满足，当使用图片时，无法设置宽高的比例，很难使用百分比定义一个正方形的控件，一个控件的margin四个方向值一致。
解决方案：http://blog.csdn.net/lmj623565791/article/details/46767825
但是还是存在一个问题：ScrollView，ListView等容器内高度无法使用百分比。

### 最终解决方案
[AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout)
封装：[AndroidAutoLayout](https://github.com/zhengjingle/AndroidAutoLayout)
