package com.example.levente.viewpager_util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Uses a combination of a PageTransformer and swapping X & Y coordinates
 * of touch events to create the illusion of a vertically scrolling ViewPager.
 * <p/>
 * Requires API 11+
 */
public class VerticalViewPager extends ViewPager {

    ViewPager mParent;
    float mStartDragX, mStartDragY;
    float epsilon = 20;

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // The majority of the magic happens here
        setPageTransformer(true, new VerticalPageTransformer());
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public void setParent(ViewPager parent) {
        mParent = parent;
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                view.setAlpha(1);

                // Counteract the default slide transition
                view.setTranslationX(view.getWidth() * -position);

                //set Y position to swipe in from top
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
        swapXY(ev); // return touch coordinates to original reference frame for any child views
        return intercepted;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartDragX = ev.getX();
                mStartDragY = ev.getY();
                Log.d("action_down", "down : x=" + mStartDragX + "y=" + mStartDragY);

            case MotionEvent.ACTION_MOVE:
                Log.d("values", "ev.getX()" + ev.getX() + "after: " + (ev.getX() - mStartDragX));
                Log.d("values", "ev.getY()" + ev.getY() + "after: " + (ev.getY() - mStartDragY));

                if (Math.abs(ev.getY() - mStartDragY) > Math.abs(ev.getX() - mStartDragX)) {
                    if (Math.abs(ev.getY() - mStartDragY - mStartDragY) > epsilon){
                        Log.d("swiper: ", "Down or Up");
                        mParent.requestDisallowInterceptTouchEvent(false);
                        return super.onTouchEvent(swapXY(ev));
                    }

                } else {
                    if (Math.abs(ev.getX() - mStartDragX - mStartDragX) > epsilon){
                        Log.d("swiper: ", "Left or Right");
                        mParent.requestDisallowInterceptTouchEvent(true);
                        mParent.onTouchEvent(ev);
                    }

                }
                return true;

            case MotionEvent.ACTION_UP:
                Log.d("action_up", "up");
                return true;
        }

        return false;

    }


}