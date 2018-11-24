package com.marcindziedzic.osiolek2.createNewNet;

import tin.p2p.controller.Controller;
import tin.p2p.controller.ControllerGUIInterface;

public class CreateNewNetPresenter implements CreateNewNetContract.Presenter {

    private final CreateNewNetContract.View view;

    private Controller backend;

    CreateNewNetPresenter(CreateNewNetContract.View createNewNetActivity) {
        this.view = createNewNetActivity;
    }

    @Override
    public void disconnectFromNet() {
        backend.disconnectFromNet(new ControllerGUIInterface.DisconnectCallback() {
            @Override
            public void onDisconnectSuccess() {
                view.showDisconnectSuccessfully();

            }

            @Override
            public void onDisconnectFailure() {
                view.showDisconnectFailure();

            }
        });
    }


}
