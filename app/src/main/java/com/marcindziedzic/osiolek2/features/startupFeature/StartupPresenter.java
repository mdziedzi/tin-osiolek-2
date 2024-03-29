package com.marcindziedzic.osiolek2.features.startupFeature;

import android.util.Log;

import com.marcindziedzic.osiolek2.utils.MockFactory;

import java.io.File;

import tin.p2p.controller_layer.ControllerGUIInterface;
import tin.p2p.controller_layer.FrameworkController;


public class StartupPresenter implements StartupContract.Presenter, ControllerGUIInterface {

    private static final String TAG = StartupPresenter.class.getSimpleName();

    private StartupContract.View view;

    private FrameworkController backend;

    StartupPresenter(StartupContract.View view) {
        this.view = view;
        initBackend();
    }

    private void initBackend() {
//        backend = FrameworkController.getInstance(this);
        backend = FrameworkController.getInstance();
    }

    @Override
    public void initListOfPreviousTrustedIPs() {
        view.fillListOfPreviousTrustedIPs(MockFactory.getPreviousTrustedIPs());
    }

    @Override
    public void createNewNet(String password) {
        Log.d(TAG, "createNewNet: ");
        File sdcard = new File("/storage/emulated/0/Download");
        // to this path add a new directory path
        File dir = new File(sdcard.getAbsolutePath() + "/osiolek/");

        backend.createNewNet(password, dir, new CreateNewNetCallback() {
            @Override
            public void onCreateNewNetSuccess() {
                Log.d(TAG, "onCreateNewNetSuccess: ");
                view.goToCreateNewNetActivity();

            }

            @Override
            public void onCreateNewNetFailure() {
                Log.d(TAG, "onCreateNewNetFailure: ");
                view.showCreateNewNetError();

            }

            @Override
            public void onApplicationMainPortUsed() {
                Log.d(TAG, "onApplicationMainPortUsed: ");
                view.showMainPortUsedError();
            }
        });
    }

    @Override
    public void connectToNetByIP(String ip, String password) {
        File sdcard = new File("/storage/emulated/0/Download");
        // to this path add a new directory path
        File dir = new File(sdcard.getAbsolutePath() + "/osiolek/");

        backend.connectToNetByIP(ip, password, dir, new ConnectToNetByIPCallback() {
            @Override
            public void onConnectToNetByIPSucces() {
                view.goToConnectToNetActivity();
            }

            @Override
            public void onConnectToNetByIPReject() {
                view.showConnectToNetRejection();
            }

            @Override
            public void onConnectToNetByIPFailure() {
                view.showConnectToNetFailure();

            }

            @Override
            public void onIPFormatFailure() {
                view.showIpFormatError();
            }
        });
    }


}
