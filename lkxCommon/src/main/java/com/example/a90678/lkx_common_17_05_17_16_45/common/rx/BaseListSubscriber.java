package com.example.a90678.lkx_common_17_05_17_16_45.common.rx;

import com.example.a90678.lkx_common_17_05_17_16_45.common.bean.BaseListResult;
import com.example.a90678.lkx_common_17_05_17_16_45.common.status.BaseLoadingStatus;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/18 18:59 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/18 18:59 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class BaseListSubscriber<ITEM, DATA extends BaseListResult> extends BaseLoadingSubscriber<DATA> {

    public BaseListSubscriber(BaseLoadingStatus<DATA> baseLoadingStatus) {
        super(baseLoadingStatus);
    }
}
