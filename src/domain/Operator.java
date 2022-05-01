package domain;

import util.Util;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

public class Operator implements Runnable {
    private String name;
    private int id;
    Object someClient;
    LinkedBlockingQueue<Object> clients;

    //Constructor
    public Operator(String name, String id, LinkedBlockingQueue<Object> clients) {
        this.name = name;
        this.id = Integer.parseInt(id);
        this.clients = clients;
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Override methods of class Object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return Objects.equals(name, operator.name) && Objects.equals(id, operator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "operator " + name + " (id: " + id + ") ";
    }

    /*Each operator will take clients from BlockingQueue and hold them some time.
     *After this time operator will be free and check are there any other clients in BlockingQueue
     *If there are no clients, operator will end his work.
     *If operator finds another client he will repeat operation with this client.
     */
    @Override
    public void run() {
        try {
            System.out.println("Operator " + getName() + " (" + getId() + ") " + " is free now");
            someClient = clients.take();
            System.out.println("\nOperator " + getName() + " is connected with " + someClient);
            Thread.sleep(Util.time());
            System.out.println("\nOperator " + getName() + " ended call with " + someClient);

            if (clients.isEmpty()) {
                System.out.println("Operator " + getName() + " ended work.");
            } else {
                run();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
