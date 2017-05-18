package com.example.a90678.lkx_common_17_05_17_16_45.common.status;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2017/1/10 16:52 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2017/1/10 16:52 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public interface BaseLoadingStatus<T> extends BaseStatus{
    void loadData();//加载数据
    void startLoad();//开始加载
    void loading();//加载中
    void success(T data);//加载成功
    void empty();//数据为空
    void failure(int code, String msg);//加载失败
    void error(String msg);//发生错误
    void complete();//加载完成
    void reLoad();//重新加载
    void offLine();//掉线
    void unLogin();//未登陆
}
