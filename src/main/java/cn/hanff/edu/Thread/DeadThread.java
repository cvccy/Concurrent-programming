package cn.hanff.edu.Thread;

public class DeadThread {
    private final static String resource_a = "A";
    private final static String resource_b = "B";

    public static void deadLock() {
        Thread tA = new Thread(new Runnable() {
            public void run() {
                synchronized (resource_a) {
                    System.out.println("get resource a");
                    try {
                        Thread.sleep(2000);
                        synchronized (resource_b) {
                            System.out.println("get resource b");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread tB = new Thread(new Runnable() {
            public void run() {
                synchronized (resource_b) {
                    System.out.println("get resource b");
                    synchronized (resource_a) {
                        System.out.println("get resource a");
                    }
                }
            }
        });
        tA.start();
        tB.start();
    }

    public static void main(String[] args) {
        deadLock();
    }
}
