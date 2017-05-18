package com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/10 14:57 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/10 14:57 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public abstract class BaseActivity extends AppCompatActivity {

    private boolean actionBarVisiable;

    public boolean isActionBarVisiable() {
        return actionBarVisiable;
    }

    public void setActionBarVisiable(boolean actionBarVisiable) {
        this.actionBarVisiable = actionBarVisiable;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = getFragment();
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, fragment)
                    .commit();
        }
    }

    public abstract Fragment getFragment();
}
