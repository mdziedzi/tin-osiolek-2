package com.marcindziedzic.osiolek2.features.connectToNetFeature;

public interface ConnectToNetContract {

    interface View {
        void showDisconnectSuccessfully();

        void showDisconnectFailure();
    }

    interface Presenter {
        void disconnectFromNet();
    }

}
