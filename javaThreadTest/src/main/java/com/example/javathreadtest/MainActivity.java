package com.example.javathreadtest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    
    private CyclicBarrier barrier=new CyclicBarrier(3, new Runnable() {
        
        @Override
        public void run() {
            Log.e("CyclicBarrier", "放行");
        }
    });
    
    FutureTask<String> futureTask=new FutureTask<String>(new Callable<String>() {

        @Override
        public String call() throws Exception {
            return null;
        }
    });
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 12; i++) {
            new Thread(){

                @Override
                public void run() {
                    super.run();
                    try {
                        print();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
                
            }.start();
        }
        
    }

    private void print() throws InterruptedException, BrokenBarrierException {
        barrier.await();
        Log.e(Thread.currentThread().getName(), "start");
//        Log.e(Thread.currentThread().getName(), "stop");
    }
    
}
