package com.coolstar.extendlayoutlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by jiguangxing on 2016/5/6.
 */
public class SDListViewActivity extends AppCompatActivity {

    ListView listView;
    View bottomPanel,topPanel;
    private boolean isPlayPanelHideing;
    private Animation playPanelHideAnim;
    private Animation playPanelShowAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview1);
        topPanel = findViewById(R.id.topPanel);
        bottomPanel = findViewById(R.id.bottompanel);
        listView = (ListView) findViewById(R.id.sdlistview);
        CommonDemoAdapter adapter = new CommonDemoAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {  //此方法简单可靠，只不过是按项数据切换滚动触发，慢一点。
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }
                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if(totalItemCount>2){ //当只有标题栏与第一个数据时，不需要隐藏控制栏，
                        if(firstVisibleItem>mListViewFirstItem) {
                            if(isPlayPanelHideing==false){
                                animHidePlayBottomPanel(bottomPanel);
                            }
                        }else if(firstVisibleItem<mListViewFirstItem){
                            if(isPlayPanelHideing==true){
                                animShowPlayBottomPanel(bottomPanel);
                            }
                        }
                        mListViewFirstItem = firstVisibleItem;
                    }
                }
            });
    }

    protected  void animHidePlayBottomPanel(final  View playBottomPanel){
        if(playBottomPanel==null){
            return;
        }
        isPlayPanelHideing = true;
        playBottomPanel.clearAnimation();
        if(playPanelHideAnim==null){
            final int newMargin = -playBottomPanel.getHeight();
            playPanelHideAnim = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playBottomPanel.getLayoutParams();
                    params.bottomMargin = (int)(newMargin * interpolatedTime);
                    playBottomPanel.setLayoutParams(params);
                }
            };
            playPanelHideAnim.setDuration(500); // in ms
        }
        playBottomPanel.startAnimation(playPanelHideAnim);
    }

    protected  void animShowPlayBottomPanel(final  View playBottomPanel){
        if(playBottomPanel==null){
            return;
        }
        playBottomPanel.clearAnimation();
        if(playPanelShowAnim==null){
            playPanelShowAnim = new Animation() {
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
        }
        playBottomPanel.startAnimation(playPanelShowAnim);
        isPlayPanelHideing = false;
    }

    private int mListViewFirstItem = 0;
}
