package com.example.a90678.wechat_auto_reply_17_05_18_11_14.component;

import com.example.a90678.lkx_common_17_05_17_16_45.common.module.BaseApiServiceModule;
import com.example.a90678.wechat_auto_reply_17_05_18_11_14.module.MainMenuModule;
import com.example.a90678.wechat_auto_reply_17_05_18_11_14.presenter.MainMenuPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 90678 on 2017/5/18.
 */
@Singleton
@Component (modules = {BaseApiServiceModule.class, MainMenuModule.class})
public interface MainMenuComponent {
    void inject(MainMenuPresenter mainMenuPresenter);
}
