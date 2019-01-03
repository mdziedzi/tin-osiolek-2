package com.marcindziedzic.osiolek2.features.showAllRemoteFiles;

import java.util.List;

public class ShowAllRemoteFilesContract {

    interface View {
        void showListOfFilesInNet(List<String[]> filesInfo);
    }

    interface Presenter {
        void refreshFileList();

        void disconnectFromNet();
    }

}
