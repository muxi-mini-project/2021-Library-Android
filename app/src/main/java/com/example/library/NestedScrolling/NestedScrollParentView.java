package com.example.library.NestedScrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class NestedScrollParentView extends LinearLayout {
    private View mHeader;
    private int mHeaderHeight;

    public NestedScrollParentView(Context context){
        super(context);
    }
    public NestedScrollParentView(Context context, @Nullable AttributeSet attributeSet){
        super(context,attributeSet);
    }
    public NestedScrollParentView(Context context,@Nullable AttributeSet attributeSet,int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child,View target,int nestedScrollAxes){
        return true;
    }
    @Override
    public void onNestedPreScroll(View target,int dx,int dy,int[] consumed){
        super.onNestedPreScroll(target,dx,dy,consumed);

        boolean headerScrollUp = dy > 0 &&getScrollY() < mHeaderHeight;
        boolean headerScrollDown = dy < 0 && getScrollY() > 0 && !target.canScrollVertically(-1);
        if (headerScrollUp || headerScrollDown){
            scrollBy(0,dy);
            consumed[1] = dy;
        }
    }
    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        if (getChildCount() > 0){
            mHeader = getChildAt(0);
        }
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeaderHeight = mHeader.getMeasuredHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mHeader.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int totalHeight = MeasureSpec.getSize(heightMeasureSpec) + mHeader.getMeasuredHeight();
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(totalHeight, mode));
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        } else if (y > mHeaderHeight) {
            y = mHeaderHeight;
        }

        super.scrollTo(x, y);
    }

}
