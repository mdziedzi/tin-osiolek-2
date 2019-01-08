package com.marcindziedzic.osiolek2.features.showAllRemoteFiles;

import java.util.stream.Collectors;

import tin.p2p.controller_layer.FrameworkController;

public class ShowAllRemoteFilesPresenter implements ShowAllRemoteFilesContract.Presenter {
    private final ShowAllRemoteFilesContract.View view;
    private final FrameworkController backend;

    public ShowAllRemoteFilesPresenter(ShowAllRemoteFilesContract.View view) {
        this.view = view;
        backend = FrameworkController.getInstance();
    }

    @Override
    public void refreshFileList() {

//        view.showListOfFilesInNet(MockFactory.getMockedListOfFilesInNet());

        backend.getListOfFilesInNet((fileListInfo) -> {
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
}
