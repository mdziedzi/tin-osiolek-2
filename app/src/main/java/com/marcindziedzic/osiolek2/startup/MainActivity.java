package com.marcindziedzic.osiolek2.startup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.marcindziedzic.osiolek2.R;

public class MainActivity extends AppCompatActivity implements StartupContract.View{

    private StartupContract.Presenter presenter;

    private TextView helloWorldLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initPresenter();

        presenter.ping();

    }

    private void initViews() {
        helloWorldLabel = findViewById(R.id.helloWorldLabel);
    }

    private void initPresenter() {
        this.presenter = new StartupPresenter(this);
    }

    @Override
    public void pingCallback() {
        helloWorldLabel.setText("The connection with backend jar has been established successfully");
    }

}
