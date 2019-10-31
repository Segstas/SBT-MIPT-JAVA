package week7.classwork7;

public class Barrier {
    private final int maxThreadCount;
    private int currentThreadCount;

    public Barrier(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            currentThreadCount++;
            while (currentThreadCount <= maxThreadCount) {
                this.wait();
            }
            this.notifyAll();
        }
    }
}

