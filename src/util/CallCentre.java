package util;

import domain.Operator;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Executors;

public class CallCentre {
    //path to property file with information about operators.
    private static final String PATH = "resources/OperatorInfo.properties";

    //private constructor
    private CallCentre() {
    }

    /*Method start() starts work of call centre.
     * ExecutorService and array of Futures are created.
     * Each element of futures is thread of operator.
     * When all threads of operators will end work, executorService end work too
     * and there will be a message that call centre end work
     */
    public static void start(int valueOfOperators, LinkedBlockingQueue<Object> clients) {
        ExecutorService executorService = Executors.newFixedThreadPool(valueOfOperators);
        CompletableFuture<?>[] futures = new CompletableFuture<?>[valueOfOperators];
        System.out.println("Call Centre has been started work!\n");

        for (int i = 0; i < valueOfOperators; i++) {
            try {
                futures[i] = CompletableFuture.runAsync(new Operator(Util.getProperty(PATH, "name", i),
                                Util.getProperty(PATH, "id", i), clients),
                        executorService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        CompletableFuture.allOf(futures)
                .thenRun(() -> {
                    System.out.println("\nCall Centre has been ended work!");
                    executorService.shutdown();
                });

    }


}


