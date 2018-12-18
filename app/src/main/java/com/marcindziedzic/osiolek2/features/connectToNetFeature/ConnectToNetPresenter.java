package com.marcindziedzic.osiolek2.features.connectToNetFeature;


import tin.p2p.controller_layer.Controller;
import tin.p2p.controller_layer.ControllerGUIInterface;

public class ConnectToNetPresenter implements ConnectToNetContract.Presenter {

    private final ConnectToNetContract.View view;

    private Controller backend;

    ConnectToNetPresenter(ConnectToNetActivity connectToNetActivity) {
        this.view = connectToNetActivity;
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
