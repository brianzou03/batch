package com.brianzou.app;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class main {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("You need to provide the number of items in program arguments.");
            System.exit(-1);
        }

        int numItems = Integer.parseInt(args[0]);
        if (numItems < 0) {
            System.out.println("The number of items must be a positive integer.");
            System.exit(-1);
        }

        RequestPoolManager requestPoolManager = new RequestPoolManager();
        List<String> itemIDs = new ArrayList<>();

        for (int i = 0; i < numItems; i++) {
            itemIDs.add(UUID.randomUUID().toString());
        }

        Instant start = Instant.now();
        requestPoolManager.start(itemIDs);
        Instant end = Instant.now();

        System.out.println(numItems + " number of item(s).");
        long latency = Duration.between(start, end).getSeconds();
        System.out.println("The program took " + latency + " seconds to run.");
        System.out.println("Per item latency was " + latency / numItems + " seconds.");


    }
}
