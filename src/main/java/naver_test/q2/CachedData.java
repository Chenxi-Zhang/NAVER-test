package naver_test.q2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedData {

    private volatile Object data;
    private DataSource dataSource;
    private ReentrantReadWriteLock lock;

    public CachedData(DataSource dataSource) {
        this.dataSource = dataSource;
        this.lock = new ReentrantReadWriteLock();
    }

    public void processCachedData() {
        lock.readLock().lock();
        if (!isValid()) {
            try {
                lock.readLock().unlock();
                lock.writeLock().lock();
                dataSource.updateCachedData(this);
                doSomething(data);
                lock.readLock().lock();
            } finally {
                lock.writeLock().unlock();
            }
        }
        try {
            doSomething(data);
        } finally {
            lock.readLock().unlock();
        }
    }

    synchronized private boolean isValid() {
        return true;
    }

    private void doSomething(Object data) {

    }

    private static class DataSource {
        void updateCachedData(CachedData cachedData) {

        }
    }
}
