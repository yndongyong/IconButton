package com.yndongyong.iconbutton.strategy;

import android.view.View;

public interface CenterStrategy {

    int applyStyle(View view, DrawablePositions drawablePosition, int drawableWidth, int textWidth, int iconPadding);
}
