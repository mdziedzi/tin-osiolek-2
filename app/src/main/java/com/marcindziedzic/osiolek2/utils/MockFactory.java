package com.marcindziedzic.osiolek2.utils;

import java.util.ArrayList;
import java.util.List;

public class MockFactory {

    public static ArrayList<String> getPreviousTrustedIPs() {
        ArrayList<String> ips = new ArrayList<>();
        ips.add("192.168.0.26");
        ips.add("127.0.0.1");
        ips.add("192.168.0.10");
        return ips;
    }

    public static List<String[]> getMockedListOfFilesInNet() {
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new String[]{"name" + i, "size" + i, "owner" + i});
        }
        return list;
    }

    public static ArrayList<String> getListOfNodes() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("name" + i);
        }
        return list;
    }
}
