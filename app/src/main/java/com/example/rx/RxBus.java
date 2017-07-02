package com.example.rx;

/*
 *  @项目名：  myRepository 
 *  @包名：    com.example.rx
 *  @文件名:   RxBus
 *  @创建者:   Admin
 *  @创建时间:  2017/7/2 23:38
 *  @描述：    TODO
 */

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

public class RxBus {
    private final FlowableProcessor<Object> mBus;

    private RxBus() {
        mBus = PublishProcessor.create()
                               .toSerialized();
    }

    private static class Holder {
        private static RxBus instance = new RxBus();
    }

    public static RxBus getDefault() {
        return Holder.instance;
    }

    public void post(@NonNull Object obj) {
        mBus.onNext(obj);
    }

    public <T> Flowable<T> register(Class<T> clz) {
        return mBus.ofType(clz);
    }

    public void unregisterAll() {
        //会将所有由mBus 生成的 Flowable 都置  completed 状态  后续的 所有消息  都收不到了
        mBus.onComplete();
    }

    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }

    // 封装默认订阅
    public <T> Disposable toDefaultFlowable(Class<T> eventType,
                                            Consumer<T> act) {
        return mBus.ofType(eventType)
                   .compose(RxUtil.<T>rxSchedulerHelper())
                   .subscribe(act);
    }
}
