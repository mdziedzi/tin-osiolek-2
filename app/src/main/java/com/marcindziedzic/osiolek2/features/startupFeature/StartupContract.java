package com.marcindziedzic.osiolek2.features.startupFeature;

import java.util.ArrayList;

public interface StartupContract {

    interface View {

        void fillListOfPreviousTrustedIPs(ArrayList<String> previousTrustedIPs);

        void goToCreateNewNetActivity();

        void showCreateNewNetError();

        void goToConnectToNetActivity();

        void showConnectToNetRejection();

        void showConnectToNetFailure();

        void showIpFormatError();
    }

    interface Presenter {

        void initListOfPreviousTrustedIPs();

        void createNewNet(String password);

        void connectToNetByIP(String ip, String password);
    }

}
