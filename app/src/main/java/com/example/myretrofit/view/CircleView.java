package com.example.myretrofit.view;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created on 2017/6/21.
 * author ${yao}.
 */

public class CircleView extends View {

    //设置画笔变量
    Paint paint;

    // 自定义View有四个构造函数
    // 如果View是在Java代码里面new的，则调用第一个构造函数
    public CircleView(Context context) {
        super(context);
        // 在构造函数里初始化画笔的操作
        init();
    }

    // 如果View是在.xml里声明的，则调用第二个构造函数
    // 自定义属性是从AttributeSet参数传进来的
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //API21之后才使用
    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void init() {
        //创建画笔
        paint = new Paint();
        //设置画笔颜色为蓝色
        paint.setColor(Color.BLUE);
        //设置画笔宽度为10px
        paint.setStrokeWidth(5f);
        //设置画笔模式为填充
        paint.setStyle(Paint.Style.FILL);

    }

    //复写onDraw（）进行绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取控件的高度和宽度
        int with = getWidth();
        int height = getHeight();

        //设置圆的半径 = 宽，高最小值的2分之1
        int r = Math.min(with, height) / 2;

        //画出圆（蓝色）
        // 圆心 = 控件的中央,半径 = 宽,高最小值的2分之1
        canvas.drawCircle(with / 2, height / 2, r, paint);
    }
}
