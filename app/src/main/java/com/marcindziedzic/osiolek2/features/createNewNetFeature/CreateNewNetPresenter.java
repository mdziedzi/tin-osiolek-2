package com.marcindziedzic.osiolek2.features.createNewNetFeature;


import tin.p2p.controller_layer.FrameworkController;

public class CreateNewNetPresenter implements CreateNewNetContract.Presenter {

    private final CreateNewNetContract.View view;

    private FrameworkController backend;

    CreateNewNetPresenter(CreateNewNetContract.View createNewNetActivity) {
        this.view = createNewNetActivity;
    }

    @Override
    public void disconnectFromNet() {
//        backend.disconnectFromNet(new ControllerGUIInterface.DisconnectCallback() {
//            @Override
//            public void onDisconnectSuccess() {
//                view.showDisconnectSuccessfully();
//
//            }
//
//            @Override
//            public void onDisconnectFailure() {
//                view.showDisconnectFailure();
//
//            }
//        });
    }


}
