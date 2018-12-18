package com.marcindziedzic.osiolek2.utils;

import java.util.ArrayList;

public class FSUtil {

    public static ArrayList<String> getPreviousTrustedIPs() {
        ArrayList<String> ips = new ArrayList<>();
        ips.add("192.168.0.26");
        ips.add("127.0.0.1");
        ips.add("192.168.0.10");
        return ips;
    }

}
