package com.example.a90678.lkx_common_17_05_17_16_45.common.component;

import com.example.a90678.lkx_common_17_05_17_16_45.common.module.ApplicationModule;
import com.example.a90678.lkx_common_17_05_17_16_45.common.module.BaseDataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/12/28 11:17 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/12/28 11:17 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BaseDataModule baseDataModule);

}
