package com.marcindziedzic.osiolek2.startup;

import java.util.ArrayList;

public interface StartupContract {

    interface View {

        void fillListOfPreviousTrustedIPs(ArrayList<String> previousTrustedIPs);

        void goToCreateNewNetActivity();

        void showCreateNewNetError();

        void goToConnectToNetActivity();

        void showConnectToNetRejection();

        void showConnectToNetFailure();
    }

    interface Presenter {

        void initListOfPreviousTrustedIPs();

        void createNewNet();

        void connectToNetByIP(String ip);
    }

}
