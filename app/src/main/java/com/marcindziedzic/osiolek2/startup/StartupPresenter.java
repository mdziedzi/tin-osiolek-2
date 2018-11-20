package com.marcindziedzic.osiolek2.startup;

import com.marcindziedzic.osiolek2.util.FSUtil;

import tin.p2p.controller.Controller;
import tin.p2p.controller.ControllerGUIInterface;

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
    public void createNewNet() {
        backend.createNewNet();
    }

    @Override
    public void connectToNetByIP(String ip) {
        backend.connectToNetByIP(ip);
    }

    @Override
    public void onCreateNewNetSuccess() {
        // todo
    }

    @Override
    public void onCreateNewNetFailure() {
        // todo
    }

    @Override
    public void onConnectToNetByIPSucces() {
        // todo
    }

    @Override
    public void onConnectToNetByIPReject() {
        // todo
    }

    @Override
    public void onConnectToNetByIPFailure() {
        // todo
    }
}
