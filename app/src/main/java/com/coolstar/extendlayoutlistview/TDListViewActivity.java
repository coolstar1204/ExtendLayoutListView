package com.coolstar.extendlayoutlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by jiguangxing on 2016/5/6.
 */
public class TDListViewActivity extends AppCompatActivity {

    TouchDirectionListView listView;
    View  bottomPanel,topPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview2);
        topPanel = findViewById(R.id.toppanel);
        bottomPanel = findViewById(R.id.bottompanel);
        listView = (TouchDirectionListView)findViewById(R.id.tdlistview);
        CommonDemoAdapter adapter = new CommonDemoAdapter(this);
        listView.setAdapter(adapter);
        listView.setScrollDirectionListener(new IScrollDirectionListener() {
            @Override
            public void onScrollDirectionChanged(boolean isDowning) {
                if(isDowning){
                    //隐藏下面板
                    MarginAnimHelper.animHidePlayBottomPanel(bottomPanel);
                    MarginAnimHelper.animHidePlayBottomPanel(topPanel);
                }else{
                    //显示下面板
                    MarginAnimHelper.animShowPlayBottomPanel(bottomPanel);
                    MarginAnimHelper.animShowPlayBottomPanel(topPanel);
                }
            }
        });

    }
}
