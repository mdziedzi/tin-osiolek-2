package com.marcindziedzic.osiolek2.connectToNet;

public interface ConnectToNetContract {

    interface View {
        void showDisconnectSuccessfully();

        void showDisconnectFailure();
    }

    interface Presenter {
        void disconnectFromNet();
    }

}
