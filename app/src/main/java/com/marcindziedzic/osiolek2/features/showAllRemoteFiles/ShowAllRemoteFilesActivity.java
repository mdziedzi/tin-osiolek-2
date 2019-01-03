package com.marcindziedzic.osiolek2.features.showAllRemoteFiles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.marcindziedzic.osiolek2.R;

import java.util.ArrayList;
import java.util.List;

public class ShowAllRemoteFilesActivity extends AppCompatActivity implements ShowAllRemoteFilesContract.View {

    private ListView listOfAllRemoteFiles;
    private ShowAllRemoteFilesContract.Presenter presenter;
    private Button refreshButton;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_remote_files);

        initPresenter();

        initViews();
    }

    private void initPresenter() {
        presenter = new ShowAllRemoteFilesPresenter(this);
    }

    private void initViews() {
        listOfAllRemoteFiles = findViewById(R.id.listOfAllRemoteFilesListView);
        refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(v -> presenter.refreshFileList());
    }

    @Override
    public void showListOfFilesInNet(List<String[]> filesInfo) {
        adapter = new FileListViewAdapter(this, R.layout.file_row_layout, (ArrayList<String[]>) filesInfo);
        listOfAllRemoteFiles.setAdapter(adapter);
    }
}
