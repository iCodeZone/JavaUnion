
public class Main {
    public static void main(String[] args) {
        final Main test1 = new Main();
        final Main test2 = new Main();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test1.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test2.method2();
            }
        }).start();
    }

    // 修饰类方法，由于静态方法属于类，而不属于对象。所以本质上是对类的同步
    private static synchronized void method1() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    private static synchronized void method2() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }
}