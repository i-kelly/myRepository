package com.example.admin.testapp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.R;
import com.example.base.BaseActivity;
import com.example.model.bean.ExampleBean;
import com.example.present.contract.ExampleContract;

import butterknife.BindView;
import util.T;


public class ExampleActivity extends BaseActivity implements ExampleContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_example;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        String msg = "";
        switch (item.getItemId()) {
            case R.id.action_edit:
                msg += "Click edit";
                break;
            case R.id.action_share:
                msg += "Click share";
                break;
            case R.id.action_settings:
                msg += "Click setting";
                break;
        }

        if (!msg.equals("")) {
            T.showShort(msg);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showContent(ExampleBean bean) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void fail() {

    }
}
