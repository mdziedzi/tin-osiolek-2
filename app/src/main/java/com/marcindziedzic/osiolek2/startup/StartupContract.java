package com.marcindziedzic.osiolek2.startup;

public interface StartupContract {

    interface View {

        /**
         * Testing purposes. Give feedback after ping() was called.
         */
        void pingCallback();

    }

    interface Presenter {

        /**
         * It's just for testing purposes. It sends ping request.
         */
        void ping();

    }

}
