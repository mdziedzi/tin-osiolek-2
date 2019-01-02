package com.marcindziedzic.osiolek2.features.createNewNetFeature;

import java.util.ArrayList;

public interface CreateNewNetContract {

    interface View {
        void showDisconnectSuccessfully();

        void showDisconnectFailure();

        void showUpdatedListOfNodes(ArrayList<String> arrayList);
    }

    interface Presenter {

        void disconnectFromNet();
    }


}
