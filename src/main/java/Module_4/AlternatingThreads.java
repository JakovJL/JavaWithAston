package Module_4;

public class AlternatingThreads {

    private static final Object lock = new Object();
    private static boolean isFirstThreadTurn = true;

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {

                    while (!isFirstThreadTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print("1 ");

                    isFirstThreadTurn = false;

                    lock.notifyAll();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (isFirstThreadTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("2");

                    isFirstThreadTurn = true;

                    lock.notifyAll();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.setDaemon(true);
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Программа завершена");
    }
}