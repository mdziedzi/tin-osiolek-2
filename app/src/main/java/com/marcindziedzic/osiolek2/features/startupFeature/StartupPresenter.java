package com.marcindziedzic.osiolek2.features.startupFeature;

import com.marcindziedzic.osiolek2.utils.FSUtil;

import tin.p2p.controller_layer.Controller;
import tin.p2p.controller_layer.ControllerGUIInterface;


public class StartupPresenter implements StartupContract.Presenter, ControllerGUIInterface {

    private StartupContract.View view;

    private Controller backend;

    StartupPresenter(StartupContract.View view) {
        this.view = view;
        initBackend();
    }

    private void initBackend() {
        backend = Controller.getInstance(this);
    }

    @Override
    public void initListOfPreviousTrustedIPs() {
        view.fillListOfPreviousTrustedIPs(FSUtil.getPreviousTrustedIPs());
    }

    @Override
    public void createNewNet(String password) {
        backend.createNewNet(password, new CreateNewNetCallback() {
            @Override
            public void onCreateNewNetSuccess() {
                view.goToCreateNewNetActivity();

            }

            @Override
            public void onCreateNewNetFailure() {
                view.showCreateNewNetError();

            }
        });
    }

    @Override
    public void connectToNetByIP(String ip) { // todo to ma przyjmować jeszcze haslo

        backend.connectToNetByIP(ip, "aaa", new ConnectToNetByIPCallback() {
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

            }
        });
    }


}
