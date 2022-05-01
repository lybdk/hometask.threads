package main;

import util.CallCentre;
import util.Calls;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();

        CallCentre.start(5, queue);

        Calls calls = new Calls(queue, 9);
        Thread clientThread = new Thread(calls);
        clientThread.start();

    }
}
