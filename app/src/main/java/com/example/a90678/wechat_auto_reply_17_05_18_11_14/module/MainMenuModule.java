package com.example.a90678.wechat_auto_reply_17_05_18_11_14.module;

import com.example.a90678.wechat_auto_reply_17_05_18_11_14.bean.MainMenuResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 90678 on 2017/5/18.
 */

@Module
public class MainMenuModule {

    @Provides
    @Singleton
    public List<MainMenuResult.MainMenu> provideMenuItem () {
        return new ArrayList<>();
    }
}
