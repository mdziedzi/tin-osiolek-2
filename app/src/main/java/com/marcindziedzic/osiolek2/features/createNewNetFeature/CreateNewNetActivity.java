package com.marcindziedzic.osiolek2.features.createNewNetFeature;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.marcindziedzic.osiolek2.R;
import com.marcindziedzic.osiolek2.features.showAllRemoteFiles.ShowAllRemoteFilesActivity;
import com.marcindziedzic.osiolek2.features.startupFeature.MainActivity;

import java.util.ArrayList;

public class CreateNewNetActivity extends AppCompatActivity implements CreateNewNetContract.View {

    private static final String TAG = CreateNewNetActivity.class.getSimpleName();

    private CreateNewNetContract.Presenter presenter;

    private ListView listOfNodes;
    private ArrayAdapter<String> adapter;
    private FloatingActionButton refreshButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_net);

        initPresenter();

        initViews();
    }

    private void initViews() {
        listOfNodes = findViewById(R.id.remoteNodesListView);
        refreshButton = findViewById(R.id.refreshListOfNodesFloatingActionButton);
        refreshButton.setOnClickListener((c) -> presenter.refreshListOfNodes());

    }

    private void initPresenter() {
        this.presenter = new CreateNewNetPresenter(this);
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

    @Override
    public void showDisconnectSuccessfully() {
        Log.d(TAG, "showDisconnectSuccessfully: ");
        runOnUiThread(() -> Toast.makeText(this, "Disconnected Successfully", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void showDisconnectFailure() {
        Log.d(TAG, "showDisconnectFailure: ");
        runOnUiThread(() -> Toast.makeText(this, "Disconnect Failure", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void showUpdatedListOfNodes(ArrayList<String> arrayList) {
        adapter = new ArrayAdapter<>(this, R.layout.ip_list_row, R.id.ipTextView, arrayList);
        runOnUiThread(() -> listOfNodes.setAdapter(adapter));
    }
}
