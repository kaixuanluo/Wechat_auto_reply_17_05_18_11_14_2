package com.example.a90678.lkx_common_17_05_17_16_45.common.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class FixedFlipper extends ViewFlipper {
    public FixedFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDetachedFromWindow(){
        try{
            super.onDetachedFromWindow();
        }catch(Exception e){
            super.stopFlipping();
        }
    }
}