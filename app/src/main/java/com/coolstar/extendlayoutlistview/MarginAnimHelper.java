package com.coolstar.extendlayoutlistview;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

/**
 * Created by jiguangxing on 2016/5/6.
 */
public class MarginAnimHelper {

    public static void animHidePlayBottomPanel(final View playBottomPanel){
        if(playBottomPanel==null){
            return;
        }
        playBottomPanel.clearAnimation();
        final int newMargin = -playBottomPanel.getHeight();
        Animation  playPanelHideAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playBottomPanel.getLayoutParams();
                params.bottomMargin = (int)(newMargin * interpolatedTime);
                playBottomPanel.setLayoutParams(params);
            }
        };
        playPanelHideAnim.setDuration(500); // in ms
        playBottomPanel.startAnimation(playPanelHideAnim);
    }

    public static  void animShowPlayBottomPanel(final  View playBottomPanel){
        if(playBottomPanel==null){
            return;
        }
        playBottomPanel.clearAnimation();
        Animation  playPanelShowAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playBottomPanel.getLayoutParams();
                params.bottomMargin += (int)(-params.bottomMargin * interpolatedTime);
                if(params.bottomMargin>0){
                    params.bottomMargin = 0;
                }
                playBottomPanel.setLayoutParams(params);
            }
        };
        playPanelShowAnim.setDuration(500); // in ms
        playBottomPanel.startAnimation(playPanelShowAnim);
    }
}
