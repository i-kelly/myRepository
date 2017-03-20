package com.example.nav;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2017/1/5.
 * 底部导航栏通知
 */

public class NoticeManager<T> {

    private static NoticeManager instance;
    private        int           msgCount;
    private        T             t;

    static {
        instance = new NoticeManager();
    }


    private NoticeManager() {

    }

    public static NoticeManager getInstance() {
        return instance;
    }

    public static void bindNotify(NoticeNotify noticeNotify) {
        instance.mNotifies.add(noticeNotify);
        instance.check(noticeNotify);
    }

    public static void unBindNotify(NoticeNotify noticeNotify) {
        instance.mNotifies.remove(noticeNotify);
    }

    private void check(NoticeNotify noticeNotify) {
        if (t != null) {
            noticeNotify.onNoticeArrived(t, msgCount);
        }
    }

    private final List<NoticeNotify> mNotifies = new ArrayList<>();


    public void onNoticeChanged(T t, int count) {
        msgCount = count;
        this.t = t;
        for (NoticeNotify notify : mNotifies) {
            notify.onNoticeArrived(t, count);
        }
    }

    public interface NoticeNotify<T> {
        void onNoticeArrived(T t, int count);
    }
}
