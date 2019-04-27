import static java.lang.Thread.sleep;

class A {
    /*
    1、静态的synchronized方法和非静态的synchronized不同步
    public synchronized void a() throws InterruptedException {
        for(int i=1;i<=10;i++) {
            sleep(100);
            System.out.print(1);
        }
    }

    public synchronized static void b() throws InterruptedException {
            for(int i=1;i<=10;i++) {
                sleep(100);
                System.out.print(2);
            }

    }*/

    /*
    静态synchronized方法相当于锁class
    public synchronized static void a() throws InterruptedException {
        synchronized (A.class){
            for (int i = 1; i <= 10; i++) {
                sleep(100);
                System.out.print(1);
            }
        }
    }

    public synchronized static void b() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            sleep(100);
            System.out.print(2);
        }

    }*/

    // 非静态synchronized方法相当于锁this
    public void a() throws InterruptedException {
        synchronized (this) {
            for (int i = 1; i <= 10; i++) {
                sleep(100);
                System.out.print(1);
            }
        }
    }

    public synchronized void b() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            sleep(100);
            System.out.print(2);
        }

    }

}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        new Thread(() -> {
            try {
                a.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        sleep(100);
        new Thread(() -> {
            try {
                a.b();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}