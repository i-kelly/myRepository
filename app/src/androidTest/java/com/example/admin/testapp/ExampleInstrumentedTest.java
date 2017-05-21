package com.example.admin.testapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.model.bean.Person;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import util.T;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext()
            throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.admin.testapp", appContext.getPackageName());
    }

    @Test
    public void addPerson() {
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    T.showShort("添加数据成功，返回objectId为：" + objectId);
                } else {
                    T.showShort("创建数据失败：" + e.getMessage());
                }
            }
        });
    }
}
