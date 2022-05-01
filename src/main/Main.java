package main;

import util.CallCentre;
import util.Calls;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {

        //creating new blocking queue where clients will be contained
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        //starting a call centre
        CallCentre.start(5, queue);

        //simulation of calls from clients
        Calls calls = new Calls(queue, 9);
        Thread clientThread = new Thread(calls);
        clientThread.start();

    }
}
