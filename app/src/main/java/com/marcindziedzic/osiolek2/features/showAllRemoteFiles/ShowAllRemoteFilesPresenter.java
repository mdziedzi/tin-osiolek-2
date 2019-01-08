package com.marcindziedzic.osiolek2.features.showAllRemoteFiles;

import android.util.Log;

import java.util.ArrayList;
import java.util.stream.Collectors;

import tin.p2p.controller_layer.ControllerGUIInterface;
import tin.p2p.controller_layer.FrameworkController;

public class ShowAllRemoteFilesPresenter implements ShowAllRemoteFilesContract.Presenter {

    private static final String TAG = ShowAllRemoteFilesActivity.class.getSimpleName();
    
    private final ShowAllRemoteFilesContract.View view;
    private final FrameworkController backend;
    private ArrayList<ArrayList<String>> remoteFiles;

    public ShowAllRemoteFilesPresenter(ShowAllRemoteFilesContract.View view) {
        this.view = view;
        backend = FrameworkController.getInstance();
    }

    @Override
    public void refreshFileList() {

//        view.showListOfFilesInNet(MockFactory.getMockedListOfFilesInNet());

        backend.getListOfFilesInNet((fileListInfo) -> {
            remoteFiles = fileListInfo;
            if (fileListInfo != null) {
                view.showListOfFilesInNet(
                        fileListInfo.stream()
                                .map(fileInfo -> new String[]{fileInfo.get(0), fileInfo.get(2), fileInfo.get(3)})
                                .collect(Collectors.toList())
                );
            }
        });
    }

    @Override
    public void disconnectFromNet() {
        backend.endOfProgram();
    }

    @Override
    public void downloadFile(int position) {
        String fileName = remoteFiles.get(position).get(0);
        Log.d(TAG, "downloadFile: fileName" + fileName);
        String fileHash = remoteFiles.get(position).get(1);
        Log.d(TAG, "downloadFile: fileHash" + fileHash);

        backend.getFileFromNet(fileName, fileHash, new ControllerGUIInterface.FileDownloadingCallback() {
            @Override
            public void onFileDownloaded(String s) {
                view.notifyFileDownloaded(s);
            }

            @Override
            public void onFileNoLongerAvailable(String s) {
                view.onFileNoLongerAviable(s);
            }
        });
    }
}
