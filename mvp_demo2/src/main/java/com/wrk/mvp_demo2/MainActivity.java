package com.wrk.mvp_demo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wrk.mvp_demo2.view.MoviesActivity;
import com.wrk.mvp_demo2.view.QueryIPActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_main_ip)
    Button btnMainIp;
    @BindView(R.id.btn_main_movie)
    Button btnMainMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_main_ip, R.id.btn_main_movie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_ip:
                startActivity(new Intent(MainActivity.this, QueryIPActivity.class));
                break;
            case R.id.btn_main_movie:
                startActivity(new Intent(MainActivity.this, MoviesActivity.class));
                break;
        }
    }
}
