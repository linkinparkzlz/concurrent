package com.zou.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CountExample {

    private static int threadNumber = 200;
    private static int clientNumber = 5000;


    private static long count = 0;

    public static void main(String[] args) {

        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //信号量
        final Semaphore semaphore = new Semaphore(threadNumber);

        for (int index = 0; index < clientNumber; index++) {

            executorService.execute(() -> {

                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

        executorService.shutdown();

        System.out.println(count);

    }


    //加法计数操作
    private static void add() {

        count++;
    }

}




















































