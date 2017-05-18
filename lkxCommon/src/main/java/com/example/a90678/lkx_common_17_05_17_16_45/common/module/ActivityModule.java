package com.example.a90678.lkx_common_17_05_17_16_45.common.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Arron on 16/7/25.
 */
@PerActivity
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    protected Activity provideActivity() {
        return this.activity;
    }
}
