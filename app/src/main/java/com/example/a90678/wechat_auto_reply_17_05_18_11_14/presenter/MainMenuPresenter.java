package com.example.a90678.wechat_auto_reply_17_05_18_11_14.presenter;

import android.view.Menu;
import android.view.MenuItem;

import com.example.a90678.lkx_common_17_05_17_16_45.common.bean.EmailListBean;
import com.example.a90678.lkx_common_17_05_17_16_45.common.component.DaggerActivityComponent;
import com.example.a90678.lkx_common_17_05_17_16_45.common.presenter.BaseListPresenter;
import com.example.a90678.lkx_common_17_05_17_16_45.common.status.BaseListStatus;
import com.example.a90678.lkx_common_17_05_17_16_45.common.status.BaseLoadingStatus;
import com.example.a90678.wechat_auto_reply_17_05_18_11_14.bean.MainMenuResult;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by 90678 on 2017/5/18.
 */

public class MainMenuPresenter extends BaseListPresenter<MainMenuResult.MainMenu, MainMenuResult> {

    @Inject
    protected List<MainMenuResult.MainMenu> menuItems;

    public MainMenuPresenter(BaseListStatus<MainMenuResult.MainMenu> baseListStatus,
                             BaseLoadingStatus<MainMenuResult> baseLoadingStatus) {
        super(baseListStatus, baseLoadingStatus);

    }
}
