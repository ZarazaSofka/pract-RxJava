package virt3;

import virt3.first.Monitoring;
import virt3.fourth.FileHandler;
import virt3.second.NumberHandler;
import virt3.third.FriendSearch;

public class Main {

    public static void main(String[] args) {
        Monitoring.start();
        NumberHandler.start();
        FriendSearch.start();
        FileHandler.start();
    }
}
