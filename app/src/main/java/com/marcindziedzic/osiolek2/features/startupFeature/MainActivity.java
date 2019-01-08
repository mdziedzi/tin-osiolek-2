package com.marcindziedzic.osiolek2.features.startupFeature;

import android.Manifest;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marcindziedzic.osiolek2.R;
import com.marcindziedzic.osiolek2.features.createNewNetFeature.CreateNewNetActivity;
import com.marcindziedzic.osiolek2.features.showAllRemoteFiles.ShowAllRemoteFilesActivity;
import com.marcindziedzic.osiolek2.reusableFragments.PasswordDialog;
import com.marcindziedzic.osiolek2.reusableFragments.PasswordDialogType;
import com.marcindziedzic.osiolek2.utils.FSUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements StartupContract.View,
        PasswordDialog.NoticeDialogListener, AddIpDialog.AddIpDialogListener, PasswordDialog.ConnectToNetDialogListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private StartupContract.Presenter presenter;

    private TextView listOfPreviouslyTrustedIPsLabel;

    private ListView list;
    private ArrayAdapter<String> adapter;

    private FloatingActionButton createNewNetButton;

    private ArrayList<String> listOfIps;

    private FloatingActionButton addNewIpButton;

    private View.OnClickListener createNewNetListener = v -> showPasswordDialog(PasswordDialogType.NEW_NET);

    private String selectedIp;

    private AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedIp = (String) parent.getItemAtPosition(position);
            showPasswordDialog(PasswordDialogType.CONNECT_TO_NET);
        }
    };

    private void startCreateNewNetActivity() {
        Intent intent = new Intent(this, CreateNewNetActivity.class);
        startActivity(intent);
    }

    private void startConnectToNetActivity() {
        Intent intent = new Intent(MainActivity.this, ShowAllRemoteFilesActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPresenter();

        initViews();

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission
                .READ_EXTERNAL_STORAGE};
        requestPermissions(permissions, 0);


    }

    private void initPresenter() {
        this.presenter = new StartupPresenter(this);
    }

    private void initViews() {
        listOfPreviouslyTrustedIPsLabel = findViewById(R.id.listOfAllRemoteFilesLabel);

        list = findViewById(R.id.ipListListView);
        list.setOnItemClickListener(listListener);
        list.setOnItemLongClickListener((parent, view, position, id) -> {
            String ip = (String) parent.getItemAtPosition(position);
            listOfIps.removeIf(s -> s.equals(ip));
            saveIpsToSharedPrefs(MainActivity.this);
            fillListOfPreviousTrustedIPs(listOfIps);
            return true;
        });
        presenter.initListOfPreviousTrustedIPs();

        createNewNetButton = findViewById(R.id.createNewNetButton);
        createNewNetButton.setOnClickListener(createNewNetListener);

        addNewIpButton = findViewById(R.id.floatingActionButton);
        addNewIpButton.setOnClickListener(v -> showAddIpDialog());

    }

    private void showAddIpDialog() {
        DialogFragment dialog = new AddIpDialog();
        dialog.show(getFragmentManager(), "AddIpDialogFragment");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Granted.
                    FSUtil.initializeAppDirectory();
                    FSUtil.getFilesFromLocalDirectory(new File("/storage/emulated/0/Download/osiolek/"), new
                            ArrayList<File>(10));
                } else {
                    //Denied.
                    Toast.makeText(this, "No permission for storage", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void fillListOfPreviousTrustedIPs(ArrayList<String> previousTrustedIPs) {
//        adapter = new ArrayAdapter<>(this, R.layout.ip_list_row, R.id.ipTextView, previousTrustedIPs);
        listOfIps = (ArrayList<String>) getIpsFromSharedPrefs(this);
        if (listOfIps == null) {
            listOfIps = new ArrayList<>();
        }
        adapter = new ArrayAdapter<>(this, R.layout.ip_list_row, R.id.ipTextView, listOfIps);
        list.setAdapter(adapter);
    }

    @Override
    public void goToCreateNewNetActivity() {
        Toast.makeText(this, "Network has been created successfully", Toast.LENGTH_LONG).show();
        startCreateNewNetActivity();
    }

    @Override
    public void showCreateNewNetError() {
        Log.i(TAG, "showCreateNewNetError: ");
        runOnUiThread(() -> Toast.makeText(this, "Error while creating new net.", Toast
                .LENGTH_SHORT).show());
    }

    @Override
    public void goToConnectToNetActivity() {
        startConnectToNetActivity();
    }

    @Override
    public void showConnectToNetRejection() {
        Log.i(TAG, "showConnectToNetRejection: ");
        runOnUiThread(() -> Toast.makeText(this, "WRONG PASSWORD", Toast.LENGTH_LONG).show());
    }

    @Override
    public void showConnectToNetFailure() {
        Log.e(TAG, "showConnectToNetFailure: ");
        runOnUiThread(() -> Toast.makeText(this, "Connect to net FAILURE", Toast.LENGTH_SHORT)
                .show());
    }

    @Override
    public void showIpFormatError() {
        Log.i(TAG, "showIpFormatError: ");
        runOnUiThread(() -> Toast.makeText(this, "Ip format is bad!", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void showMainPortUsedError() {
        Log.d(TAG, "showMainPortUsedError: ");
        runOnUiThread(() -> Toast.makeText(this, "Main app port is used!", Toast.LENGTH_SHORT).show());
    }

    private void showPasswordDialog(PasswordDialogType passwordDialogType) {        // Create an instance of the
        // dialog fragment
        // and show it
        DialogFragment dialog = new PasswordDialog(passwordDialogType);
        dialog.show(getFragmentManager(), "PasswordDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        Log.d(TAG, "onDialogPositiveClick: ");
        EditText passwordField = Objects.requireNonNull(dialog.getDialog()).findViewById(R.id.password);
//        Toast.makeText(this, "Pass is set as: " + passwordField.getText().toString(), Toast.LENGTH_SHORT).show();
        presenter.createNewNet(passwordField.getText().toString());
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //do nothing
    }

    private void saveIpsToSharedPrefs(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listOfIps); //tasks is an ArrayList instance variable
        prefsEditor.putString("listOfIps", json);
        prefsEditor.apply();
    }

    public List<String> getIpsFromSharedPrefs(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context
                .getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("listOfIps", "");
        listOfIps = gson.fromJson(json, new TypeToken<ArrayList<String>>() {
        }.getType());
        return listOfIps;
    }


    @Override
    public void onIpDialogPositiveClick(DialogFragment dialog) {
        EditText newIp = Objects.requireNonNull(dialog.getDialog()).findViewById(R.id.ipNumberTextEdit);
        listOfIps.add(newIp.getText().toString());
        saveIpsToSharedPrefs(this);
    }

    @Override
    public void onIpDialogNegativeClick(DialogFragment dialog) {
        // do nothing
    }

    @Override
    public void onConnectToNetDialogPositiveClick(DialogFragment dialogFragment) {
        EditText password = Objects.requireNonNull(dialogFragment.getDialog()).findViewById(R.id
                .password);
        presenter.connectToNetByIP(selectedIp, password.getText().toString());
    }

    @Override
    public void onConnectToNetDialogNegativeClick(DialogFragment dialogFragment) {
        // do nothing
    }
}
