package com.marcindziedzic.osiolek2.startup;

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
    public void ping() {
        backend.ping();

    }

    @Override
    public void pingCallback() {
        view.pingCallback();
    }

}
