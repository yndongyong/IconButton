package com.yndongyong.iconbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.yndongyong.iconbutton.strategy.CenterStrategy;
import com.yndongyong.iconbutton.strategy.DrawableAndTextCenterStrategy;
import com.yndongyong.iconbutton.strategy.DrawablePositions;
import com.yndongyong.iconbutton.strategy.TextCenterStrategy;


public class IconCheckBox extends AppCompatCheckBox {

    //图片和文本一起居中
    public static final int CENTER_ANCHOR_ALL = 0;
    //文本居中
    public static final int CENTER_ANCHOR_TEXT = 1;

    protected int drawableWidth;
    protected DrawablePositions drawablePosition;
    // Cached to prevent allocation during onLayout
    protected Rect bounds = new Rect();
    private int iconPadding;
    private int centerAnchor = CENTER_ANCHOR_ALL;
    private CenterStrategy mCenterStrategy;

    public IconCheckBox(Context context) {
        this(context, null);
    }

    public IconCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.IconButtonStyle);
        centerAnchor = typedArray.getInt(R.styleable.IconButtonStyle_dy_centerAnchor, 0);
        typedArray.recycle();
        int iconPadding = getCompoundDrawablePadding();
        setIconPadding(iconPadding);
        setClickable(true);
        setFocusable(true);
        if (centerAnchor == CENTER_ANCHOR_TEXT) {
            mCenterStrategy = new TextCenterStrategy();
        } else {
            mCenterStrategy = new DrawableAndTextCenterStrategy();
        }

    }

    public void setIconPadding(int iconPadding) {
        this.iconPadding = iconPadding;
        requestLayout();
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawables(left, top, right, bottom);
        if (left != null && right != null) {
            drawableWidth = left.getIntrinsicWidth() + right.getIntrinsicWidth();
            drawablePosition = DrawablePositions.BOTH;
        } else if (left != null) {
            drawableWidth = left.getIntrinsicWidth();
            drawablePosition = DrawablePositions.LEFT;
        } else if (right != null) {
            drawableWidth = right.getIntrinsicWidth();
            drawablePosition = DrawablePositions.RIGHT;
        } else {
            drawablePosition = DrawablePositions.NONE;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        TextPaint textPaint = getPaint();
        String textStr = getText().toString();
        textPaint.getTextBounds(textStr, 0, textStr.length(), bounds);
        int textWidth = bounds.width();

        int horizontalPadding = mCenterStrategy.applyStyle(this, drawablePosition, drawableWidth, textWidth, iconPadding);
        setCompoundDrawablePadding(-horizontalPadding + iconPadding);
    }
}
