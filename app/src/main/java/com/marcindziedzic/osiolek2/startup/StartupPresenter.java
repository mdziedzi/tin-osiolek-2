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
        // todo
    }

    @Override
    public void pingCallback() {
        // todo: delete
    }
}
