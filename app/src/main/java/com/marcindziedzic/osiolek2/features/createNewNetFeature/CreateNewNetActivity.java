package com.marcindziedzic.osiolek2.features.createNewNetFeature;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.marcindziedzic.osiolek2.R;

public class CreateNewNetActivity extends AppCompatActivity implements CreateNewNetContract.View {

    private CreateNewNetContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_net);

        initPresenter();
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

        presenter.disconnectFromNet();
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
