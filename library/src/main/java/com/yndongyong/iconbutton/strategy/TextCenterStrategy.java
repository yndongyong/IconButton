package com.yndongyong.iconbutton.strategy;

import android.view.View;

public class TextCenterStrategy implements CenterStrategy {

    @Override
    public int applyStyle(View view, DrawablePositions drawablePosition, int drawableWidth, int textWidth, int drawablePadding) {

        int horizontalPadding = (view.getWidth() - textWidth) / 2;
        int childOffsetX;

        switch (drawablePosition) {
            case LEFT:
                childOffsetX = horizontalPadding - drawablePadding - drawableWidth;
                view.setPadding(childOffsetX, view.getPaddingTop(), 0, view.getPaddingBottom());
                break;
            case RIGHT:
                childOffsetX = view.getWidth() - (horizontalPadding + textWidth + drawablePadding + drawableWidth);
                view.setPadding(0, view.getPaddingTop(), childOffsetX, view.getPaddingBottom());
                break;
            case BOTH:
                childOffsetX = horizontalPadding = (view.getWidth() - (drawableWidth + drawablePadding *2 + textWidth)) / 2;
                view.setPadding(childOffsetX, view.getPaddingTop(), childOffsetX, view.getPaddingBottom());
                break;
            default:
                view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
                break;
        }
        return horizontalPadding;

    }
}
