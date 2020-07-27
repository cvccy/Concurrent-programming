package cn.hanff.edu.jmm;

/**
 * 此时的initflag依旧不可见，但是load()中的同步块可能会导致线程的上下文切换，进而使得线程被再次唤醒时，initFlag再次从主存获取数据；
 * @author xhf
 * @time 2020/07/27
 */
public class VolatileVisibilitySample_1 {
    private boolean initFlag = false;
    static Object object = new Object();

    public void refresh(){
        this.initFlag = true; //普通写操作，(volatile写)
        String threadname = Thread.currentThread().getName();
        System.out.println("线程："+threadname+":修改共享变量initFlag");
    }

    public void load(){
        String threadname = Thread.currentThread().getName();
        int i = 0;
        while (!initFlag){
            synchronized (object){
                i++;
            }
            //i++;
        }
        System.out.println("线程："+threadname+"当前线程嗅探到initFlag的状态的改变"+i);
    }

    public static void main(String[] args){
        VolatileVisibilitySample_1 sample = new VolatileVisibilitySample_1();
        Thread threadA = new Thread(()->{
            sample.refresh();
        },"threadA");

        Thread threadB = new Thread(()->{
            sample.load();
        },"threadB");

        threadB.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }

}
