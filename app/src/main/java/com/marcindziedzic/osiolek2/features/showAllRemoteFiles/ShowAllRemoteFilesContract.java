package com.marcindziedzic.osiolek2.features.showAllRemoteFiles;

import java.util.List;

public class ShowAllRemoteFilesContract {

    interface View {
        void showListOfFilesInNet(List<String[]> filesInfo);

        void notifyFileDownloaded(String s);

        void onFileNoLongerAviable(String s);
    }

    interface Presenter {
        void refreshFileList();

        void disconnectFromNet();

        void downloadFile(int position);
    }

}
