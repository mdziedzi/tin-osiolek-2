package com.marcindziedzic.osiolek2.startup;

import java.util.ArrayList;

public interface StartupContract {

    interface View {

        void fillListOfPreviousTrustedIPs(ArrayList<String> previousTrustedIPs);
    }

    interface Presenter {

        void initListOfPreviousTrustedIPs();

        void createNewNet();

    }

}
