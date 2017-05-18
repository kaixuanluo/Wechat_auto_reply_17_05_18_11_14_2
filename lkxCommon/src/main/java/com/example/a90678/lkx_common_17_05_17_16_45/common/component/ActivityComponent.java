package com.example.a90678.lkx_common_17_05_17_16_45.common.component;

import android.app.Activity;


import com.example.a90678.lkx_common_17_05_17_16_45.common.module.ActivityModule;
import com.example.a90678.lkx_common_17_05_17_16_45.common.module.PerActivity;

import dagger.Component;

/**
 * Created by Arron on 16/7/25.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
