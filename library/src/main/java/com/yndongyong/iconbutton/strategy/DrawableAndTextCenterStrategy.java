package com.yndongyong.iconbutton.strategy;

import android.view.View;

public class DrawableAndTextCenterStrategy implements CenterStrategy {

    @Override
    public int applyStyle(View view, DrawablePositions drawablePosition, int drawableWidth, int textWidth, int iconPadding) {

        int childOffsetX  = (view.getWidth() - (drawableWidth + iconPadding + textWidth)) / 2;
        switch (drawablePosition) {
            case LEFT:
                view.setPadding(childOffsetX, view.getPaddingTop(), 0, view.getPaddingBottom());
                break;
            case RIGHT:
                view.setPadding(0, view.getPaddingTop(), childOffsetX, view.getPaddingBottom());
                break;
            case BOTH:
                view.setPadding(childOffsetX, view.getPaddingTop(), childOffsetX, view.getPaddingBottom());
                break;
            default:
                view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
                break;
        }
        return childOffsetX;
    }
}
