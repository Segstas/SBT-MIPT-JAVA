package week7.homework7;

import java.util.concurrent.Callable;

public class Task<T> implements Runnable {
    private final Callable<? extends T> callable;
    private boolean isCatchException = false;
    private boolean isReadyForWork = true;
    private T ansver;
    private Exception callableException;

    Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    private T get() {
        if (isReadyForWork) {
            synchronized (this) {
                if (isReadyForWork) {
                    try {
                        ansver = callable.call();
                        isCatchException = false;
                    } catch (Exception e) {
                        callableException = e;
                        isCatchException = true;
                    }
                    isReadyForWork = false;
                }
            }
        } else {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!isCatchException) {
            return ansver;
        } else throw new RuntimeException("Exception in get method", callableException);
    }

    @Override
    public void run() {
        System.out.println(this.get());
    }
}

