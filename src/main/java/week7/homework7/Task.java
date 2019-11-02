package week7.homework7;

import java.util.concurrent.Callable;

public class Task<T> implements Runnable {
    private final Callable<? extends T> callable;
    private volatile boolean readyForThread = true;
    private volatile boolean isFinished = false;
    private T answer;
    private Exception callableException;

    Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    private T get() {
        if (!isFinished) {
            try {
                lockThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!isFinished) {
                try {
                    answer = callable.call();
                } catch (Exception e) {
                    callableException = e;
                } finally {
                    isFinished = true;
                }
            }
            try {
                unlockThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (callableException == null) {
            return answer;
        } else throw new RuntimeException("Exception in get method", callableException);
    }

    public void run() {
        System.out.println(this.get());
    }

    private void lockThread() throws InterruptedException {
        synchronized (this) {
            while (!readyForThread) {
                this.wait();
            }
            readyForThread = false;
        }
    }

    private void unlockThread() throws InterruptedException {
        synchronized (this) {
            readyForThread = true;
            this.notify();
        }
    }
}

