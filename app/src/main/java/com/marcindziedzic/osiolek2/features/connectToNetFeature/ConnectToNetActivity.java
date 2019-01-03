package com.marcindziedzic.osiolek2.features.connectToNetFeature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.marcindziedzic.osiolek2.R;
import com.marcindziedzic.osiolek2.features.createNewNetFeature.CreateNewNetActivity;
import com.marcindziedzic.osiolek2.features.showAllRemoteFiles.ShowAllRemoteFilesActivity;
import com.marcindziedzic.osiolek2.features.startupFeature.MainActivity;

public class ConnectToNetActivity extends AppCompatActivity implements ConnectToNetContract.View {

    private ConnectToNetContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_net);

        initPresenter();
    }

    private void initPresenter() {
        this.presenter = new ConnectToNetPresenter(this);
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
        //todo
    }

    @Override
    public void showDisconnectFailure() {
        //todo
    }


}
