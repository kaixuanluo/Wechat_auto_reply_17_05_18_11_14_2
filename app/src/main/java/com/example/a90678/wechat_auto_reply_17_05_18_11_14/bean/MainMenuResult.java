package com.example.a90678.wechat_auto_reply_17_05_18_11_14.bean;

import com.example.a90678.lkx_common_17_05_17_16_45.common.bean.BaseListResult;
import com.example.a90678.lkx_common_17_05_17_16_45.common.bean.BaseResult;

import java.util.List;

/**
 * Created by 90678 on 2017/5/18.
 */

public class MainMenuResult extends BaseListResult<MainMenuResult.MainMenu> {

    @Override
    public List<MainMenu> getList() {
        return null;
    }

    public static class MainMenu {
        int MenuId;
        String menuTitle;
        int menuTitleImgSrc;
        String menuTitleImgUrl;
    }
}
