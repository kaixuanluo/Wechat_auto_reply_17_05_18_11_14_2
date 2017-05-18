package com.example.a90678.lkx_common_17_05_17_16_45.common.view.activity;

import android.os.Bundle;
import android.view.View;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import javax.annotation.Nonnull;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * 描述说明  <br/>
 * Author : luokaixuan <br/>
 * CreateDate : 2016/12/26 18:02 <br/>
 * Modified : luokaixuan <br/>
 * ModifiedDate : 2016/12/26 18:02 <br/>
 * Email : 1005949566@qq.com <br/>
 * Version 1.0
 */
public class RxFragment extends BaseToolbarFragment implements LifecycleProvider<FragmentEvent> {

    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    public final Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Nonnull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@Nonnull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(this.lifecycleSubject, event);
    }

    @Nonnull
    @Override
    public LifecycleTransformer bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(this.lifecycleSubject);
    }

//    @Nonnull
//    @Override
//    public LifecycleTransformer bindToLifecycle() {
//        return null;
//    }
//
//    @Nonnull
//    @Override
//    public LifecycleTransformer bindUntilEvent(@Nonnull Object event) {
//        return null;
//    }
//
//    @Override
//    public final <T> Observable.Transformer<T, T> bindUntilEvent(FragmentEvent event) {
//        return RxLifecycle.bindUntilFragmentEvent(lifecycleSubject, event);
//    }
//
//    @Override
//    public final <T> Observable.Transformer<T, T> bindToLifecycle() {
//        return RxLifecycle.bindFragment(lifecycleSubject);
//    }

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }
}
