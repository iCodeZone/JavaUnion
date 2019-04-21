public class Main implements Runnable {
    private String name;
    private Object prev;// 前一个线程持有的对象锁
    private Object self;// 自身的对象锁

    private Main(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Main pa = new Main("A", c, a);
        Main pb = new Main("B", a, b);
        Main pc = new Main("C", b, c);

        new Thread(pa).start();
        Thread.sleep(100);
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    count--;
                    self.notify();// 释放自身对象锁，唤醒下一个等待线程
                }
                try {
                    prev.wait();// 释放prev对象锁，终止当前线程。等待循环结束后再次被唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}