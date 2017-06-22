package com.example.myretrofit.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.myretrofit.R;

/**
 * Created on 2017/6/21.
 * author ${yao}.
 */

public class CircleView extends View {

    //设置画笔变量
    Paint paint;
    private int color;

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
        this(context, attrs, 0);
        init();
    }

    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //加载自定义属性集合CircleView
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleView);

        //解析集合中的属性circle_color属性
        // 该属性的id为:R.styleable.CircleView_circle_color
        // 第二个参数是默认设置颜色
        color = array.getColor(R.styleable.CircleView_circle_color, Color.RED);

        //解析后释放资源
        array.recycle();

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
        paint.setColor(color);
        //设置画笔宽度为10px
        paint.setStrokeWidth(5f);
        //设置画笔模式为填充
        paint.setStyle(Paint.Style.FILL);

    }

    //复写onDraw（）进行绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();


        //获取控件的高度和宽度
        int with = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;

        //设置圆的半径 = 宽，高最小值的2分之1
        int r = Math.min(with, height) / 2;

        //画出圆（蓝色）
        // 圆心 = 控件的中央,半径 = 宽,高最小值的2分之1
        canvas.drawCircle(paddingLeft + with / 2, paddingTop + height / 2, r, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        //获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //设置默认宽/高值
        //默认宽高的设定无固定依据，根据需要灵活设置
        //类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽高值有特殊处理
        int width = 400;
        int height = 400;

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, height);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, heightSize);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, height);
        }
    }
}
