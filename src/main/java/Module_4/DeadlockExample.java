package Module_4;

public class DeadlockExample {

    private static final Object RESOURCE_1 = new Object();
    private static final Object RESOURCE_2 = new Object();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1: Пытается захватить resource 1");
            synchronized (RESOURCE_1) {
                System.out.println("Thread 1: Захватил resource 1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Пытается захватить resource 2");
                synchronized (RESOURCE_2) {
                    System.out.println("Thread 1: Захватил resource 2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2: Пытается захватить resource 2");
            synchronized (RESOURCE_2) {
                System.out.println("Thread 2: Захватил resource 2");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: Пытается захватить resource 1");
                synchronized (RESOURCE_1) {
                    System.out.println("Thread 2: Захватил resource 1");
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}