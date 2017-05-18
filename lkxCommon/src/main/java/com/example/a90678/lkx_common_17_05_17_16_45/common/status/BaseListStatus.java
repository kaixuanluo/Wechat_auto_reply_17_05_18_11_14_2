package com.example.a90678.lkx_common_17_05_17_16_45.common.status;

import java.util.List;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/18 15:01 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/18 15:01 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public interface BaseListStatus<ITEM> {
    void loadMore(ITEM data);
    void getListData(List<ITEM> list);
    void getListMoreData(List<ITEM> list);
    void noMore();
    void hasMore();
}
