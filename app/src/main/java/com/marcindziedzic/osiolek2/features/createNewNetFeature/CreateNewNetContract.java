package com.marcindziedzic.osiolek2.features.createNewNetFeature;

public interface CreateNewNetContract {

    interface View {
        void showDisconnectSuccessfully();

        void showDisconnectFailure();
    }

    interface Presenter {

        void disconnectFromNet();
    }


}
