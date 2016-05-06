package com.coolstar.extendlayoutlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ListView;

/**
 * Created by jiguangxing on 2016/5/6.
 */
public class TouchDirectionListView extends ListView {
    private boolean mIsScrollToDown;

    public TouchDirectionListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public TouchDirectionListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public TouchDirectionListView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();  //获取系统的触摸限值
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                showLog("onInterceptTouchEvent----Down");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                showLog("onInterceptTouchEvent----Move");
//                break;
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP:
//                showLog("onInterceptTouchEvent----Up");
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

    private void showLog(String msg) {
        Log.d("TDListView",msg);
    }

    private float downY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                showLog("dispatchTouchEvent--Down");
                if(ev.getPointerCount()==1){
                    downY = ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                showLog("dispatchTouchEvent--Move");
                if(ev.getPointerCount()==1){
                    float moveY = ev.getY();
                    float distance = moveY-downY;
                    if(distance>0&&distance>mTouchSlop&&mIsScrollToDown){
                        mIsScrollToDown = false;
                        if(listener!=null){
                            listener.onScrollDirectionChanged(mIsScrollToDown);
                        }
                    }else if(distance<0&&distance<-mTouchSlop&&mIsScrollToDown==false){
                        mIsScrollToDown = true;
                        if(listener!=null){
                            listener.onScrollDirectionChanged(mIsScrollToDown);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                showLog("dispatchTouchEvent--Up");
                if(ev.getPointerCount()==1){
                    downY=0;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                showLog("onTouchEvent------Down");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                showLog("onTouchEvent------Move");
//                break;
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP:
//                showLog("onTouchEvent------Up");
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }

    private float mTouchSlop;
    private IScrollDirectionListener listener;
    public void setScrollDirectionListener(IScrollDirectionListener listener) {
        this.listener = listener;
    }
}
