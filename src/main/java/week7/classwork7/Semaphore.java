package week7.classwork7;

public class Semaphore {
    private final int maxThreadCount;
    private volatile int currentThreadCount;

    public Semaphore(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
    }

    public void lock() throws InterruptedException {
        synchronized (this) {
            currentThreadCount++;
            while (currentThreadCount > maxThreadCount) {
                this.wait();
            }
        }
    }

    public void unlock() throws InterruptedException {
        synchronized (this) {
            currentThreadCount--;
            this.notify();
        }

    }
}
