package com.wrk.mvp_best.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.wrk.mvp_best.R;
import com.wrk.mvp_best.presenter.IPresenter;
import com.wrk.mvp_best.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.tv_main_test)
    TextView tvMainTest;
    @BindView(R.id.btn_main_test)
    Button btnMainTest;


    private IPresenter mIPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //  Presenter初始化
        mIPresenter = new Presenter(this);
        //  将生命周期回调传给Presenter
        mIPresenter.onCreate();

    }


    @Override
    public void setData(final String data) {
        //  删除异步逻辑，调整到Presenter中
        //  设置数据
        tvMainTest.setText(data);

    }


    @OnClick(R.id.btn_main_test)
    public void onClick() {
        mIPresenter.perfromOnClick();
    }
}
