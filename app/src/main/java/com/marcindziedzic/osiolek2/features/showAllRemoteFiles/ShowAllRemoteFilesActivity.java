package com.marcindziedzic.osiolek2.features.showAllRemoteFiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.marcindziedzic.osiolek2.R;
import com.marcindziedzic.osiolek2.features.createNewNetFeature.CreateNewNetActivity;
import com.marcindziedzic.osiolek2.features.startupFeature.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ShowAllRemoteFilesActivity extends AppCompatActivity implements ShowAllRemoteFilesContract.View {

    private ListView listOfAllRemoteFiles;
    private ShowAllRemoteFilesContract.Presenter presenter;
    private FloatingActionButton refreshButton;
    private ArrayAdapter adapter;
    private SwipeRefreshLayout swipeLayout;

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

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.filesSwipeRefresh);
        swipeLayout.setOnRefreshListener(() -> presenter.refreshFileList());
    }

    @Override
    public void showListOfFilesInNet(List<String[]> filesInfo) {
        adapter = new FileListViewAdapter(this, R.layout.file_row_layout, (ArrayList<String[]>) filesInfo);
        listOfAllRemoteFiles.setAdapter(adapter);

        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // case jesli bedzie ich wiecej
        switch (item.getItemId()) {
            case R.id.listOfAllRemoteFilesMenuItem:
                startActivity(new Intent(this, ShowAllRemoteFilesActivity.class));
                break;
            case R.id.listOfRemoteNodesMenuItem:
                startActivity(new Intent(this, CreateNewNetActivity.class));
                break;
            case R.id.disconnectMenuItem:
                presenter.disconnectFromNet();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
