package com.mshan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App implements Runnable
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println( "Hello World!" );
        ExecutorService executorService = new ThreadPoolExecutor(1, 10, 0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>());
        for(int i=0; i < 10; i++) {
            executorService.submit(new App());
        }


        executorService.awaitTermination(10l,TimeUnit.SECONDS);

        for(int i=0; i < 10; i++) {
            executorService.submit(new App());
        }


        executorService.awaitTermination(10l,TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println("Shutting down");
    }

    @Override
    public void run() {
       System.out.println("I am running");
    }
}
