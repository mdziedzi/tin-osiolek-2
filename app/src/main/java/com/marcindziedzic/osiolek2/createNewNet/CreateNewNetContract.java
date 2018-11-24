package com.marcindziedzic.osiolek2.createNewNet;

public interface CreateNewNetContract {

    interface View {
        void showDisconnectSuccessfully();

        void showDisconnectFailure();
    }

    interface Presenter {

        void disconnectFromNet();
    }


}
