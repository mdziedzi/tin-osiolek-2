package com.marcindziedzic.osiolek2.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.marcindziedzic.osiolek2.R;
import com.marcindziedzic.osiolek2.connectToNet.ConnectToNetActivity;
import com.marcindziedzic.osiolek2.createNewNet.CreateNewNetActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StartupContract.View{

    private StartupContract.Presenter presenter;

    private TextView listOfPreviouslyTrustedIPsLabel;

    private ListView list;
    private ArrayAdapter<String> adapter;

    private Button createNewNetButton;

    private View.OnClickListener createNewNetListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.createNewNet();

            startCreateNewNetActivity();

        }
    };

    private AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String ip = (String) parent.getItemAtPosition(position);
            presenter.connectToNetByIP(ip);
            startConnectToNetActivity();
        }
    };

    private void startCreateNewNetActivity() {
        Intent intent = new Intent(MainActivity.this, CreateNewNetActivity.class);
        startActivity(intent);
    }

    private void startConnectToNetActivity() {
        Intent intent = new Intent(MainActivity.this, ConnectToNetActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPresenter();

        initViews();

    }

    private void initPresenter() {
        this.presenter = new StartupPresenter(this);
    }

    private void initViews() {
        listOfPreviouslyTrustedIPsLabel = findViewById(R.id.listOfPreviouslyTrustedIPsLabel);

        list = findViewById(R.id.ipListListView);
        list.setOnItemClickListener(listListener);
        presenter.initListOfPreviousTrustedIPs();

        createNewNetButton = findViewById(R.id.createNewNetButton);
        createNewNetButton.setOnClickListener(createNewNetListener);

    }

    @Override
    public void fillListOfPreviousTrustedIPs(ArrayList<String> previousTrustedIPs) {
        adapter = new ArrayAdapter<>(this, R.layout.ip_list_row, R.id.ipTextView, previousTrustedIPs);
        list.setAdapter(adapter);
    }

}
