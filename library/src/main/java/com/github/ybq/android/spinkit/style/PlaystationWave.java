package com.github.ybq.android.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;

import com.github.ybq.android.spinkit.animation.SpriteAnimatorBuilder;
import com.github.ybq.android.spinkit.sprite.RectSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.sprite.SpriteContainer;


public class PlaystationWave extends SpriteContainer {

    @Override
    public Sprite[] onCreateChild() {
        WaveItem[] waveItems = new WaveItem[6];
        for (int i = 0; i < waveItems.length; i++) {
            waveItems[i] = new WaveItem();
            waveItems[i].setAnimationDelay(-1600 + i * 180);
        }
        return waveItems;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        bounds = clipSquare(bounds);
        int childCount = getChildCount();
        int boxWidth = bounds.width() / childCount * 2 / 3;
        int totalBoxWidth = boxWidth * childCount;
        int delta = bounds.width() - totalBoxWidth;
        int firstL = bounds.left + delta / 2;
        int width = 8;
        for (int i = 0; i < getChildCount(); i++) {
            Sprite sprite = getChildAt(i);
            int l = firstL + i * boxWidth;
            int r = l + width;
            sprite.setDrawBounds(l, bounds.top, r, bounds.bottom);
        }
    }

    private class WaveItem extends RectSprite {

        WaveItem() {
            setScaleY(0.4f);
        }

        @Override
        public ValueAnimator onCreateAnimation() {
            float fractions[] = new float[]{0f, 0.2f,1f};
            return new SpriteAnimatorBuilder(this)
                    .scaleY(fractions, 0.4f, 0.45f, 0.4f)
                    .alpha(fractions, 120, 255, 120)
                    .duration(1600)
                    .easeInOut(fractions)
                    .build();
        }
    }
}
