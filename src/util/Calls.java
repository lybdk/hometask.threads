package util;

import domain.Client;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class Calls implements Runnable {
    LinkedBlockingQueue<Object> clients;
    private final int countOfClients;

    //path to property file with information about operators.
    private static final String PATH = "resources/ClientInfo.properties";

    public Calls(LinkedBlockingQueue<Object> clients, int countOfClients) {
        this.clients = clients;
        this.countOfClients = countOfClients;
    }

    //new clients are created and queued.
    @Override
    public void run() {
        try {
            //call delay
            Thread.sleep(700);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (int i = 0; i < countOfClients; i++) {
            Client client = null;
            try {
                client = new Client(Util.getProperty(PATH, "name", i),
                        Util.getProperty(PATH, "country", i),
                        Util.getProperty(PATH, "city", i), clients);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("\n" + client + " called and waiting for the operator");
                //all new clients will be contained to BlockingQueue
                clients.put(client);
                Thread.sleep(800);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
