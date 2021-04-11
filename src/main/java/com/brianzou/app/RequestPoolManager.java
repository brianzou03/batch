package com.brianzou.app;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RequestPoolManager {
    public void start(List<String> itemIDs) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorServiceHttpClient = Executors.newFixedThreadPool(5);


        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(60))
                .executor(executorServiceHttpClient)
                .build();


        List<SingleCallable> todo = new ArrayList<>();


        for (int i = 0; i < itemIDs.size(); i++) {
            SingleCallable callable = new SingleCallable();
            callable.setSequence(i);
            callable.setID(itemIDs.get(i));
            callable.setHttpClient(httpClient);
            todo.add(callable);
        }

        try {
            List<Future<Result>> answers = executorService.invokeAll(todo);
            for (Future<Result> answer : answers) {
                try {
                    System.out.println(answer.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        executorService.shutdown();
        executorServiceHttpClient.shutdown();

        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
            executorServiceHttpClient.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
