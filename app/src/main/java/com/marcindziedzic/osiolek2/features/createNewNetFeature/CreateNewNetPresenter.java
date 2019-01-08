package com.marcindziedzic.osiolek2.features.createNewNetFeature;


import java.util.ArrayList;

import tin.p2p.controller_layer.ControllerGUIInterface;
import tin.p2p.controller_layer.FrameworkController;

public class CreateNewNetPresenter implements CreateNewNetContract.Presenter,
        ControllerGUIInterface.ListOfNodesViewer {

    private final CreateNewNetContract.View view;

    private FrameworkController backend;

    CreateNewNetPresenter(CreateNewNetContract.View createNewNetActivity) {
        this.view = createNewNetActivity;
        backend = FrameworkController.getInstance();
        backend.registerListOfNodesViewer(this);
    }

    @Override
    public void disconnectFromNet() {
        backend.endOfProgram();
    }


    @Override
    public void onListOfNodesUpdated(ArrayList<String> arrayList) {
        view.showUpdatedListOfNodes(arrayList);
    }
}
