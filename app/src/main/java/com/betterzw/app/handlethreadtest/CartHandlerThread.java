package com.betterzw.app.handlethreadtest;

import android.os.Handler;
import android.os.HandlerThread;


/**
 * Please specify the function of this class
 *
 * @author zhengwu
 * @date 2016/1/5
 */
public class CartHandlerThread {
    private Handler handler;
    private HandlerThread handlerThread;

    private final Object LOCK = new Object();

    private static CartHandlerThread instance;

    public static CartHandlerThread getInstance(){
        if(instance == null){
            instance = new CartHandlerThread();
        }
        return instance;
    }

    protected void enqueue(Runnable runnable){
        if(handler == null){
            handlerThread = new HandlerThread("CartThread");
            handlerThread.start();

            handler = new Handler(handlerThread.getLooper());
        }

        handler.post(runnable);
    }

    protected void quit(){
        handler.removeCallbacksAndMessages(null);
        handlerThread.quit();
        handlerThread = null;
        handler = null;
    }
}
