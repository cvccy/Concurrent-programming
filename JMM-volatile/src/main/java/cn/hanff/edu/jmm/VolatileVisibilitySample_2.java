package cn.hanff.edu.jmm;

/**
 * @author xhf
 * @time 2020/07/27
 * @description initFlag添加volatile属性，在字段前添加#Lock锁，遵循缓存一致性协议。
 *              volatile可见性
 */
public class VolatileVisibilitySample_2 {
    private volatile boolean initFlag = false;  //
//    static Object object = new Object();

    public void refresh(){
        this.initFlag = true; //普通写操作，(volatile写)
        String threadname = Thread.currentThread().getName();
        System.out.println("线程："+threadname+":修改共享变量initFlag");
    }

    public void load(){

        String threadname = Thread.currentThread().getName();
        int i = 0;
        while (!initFlag){
            i++;
        }
        System.out.println("线程："+threadname+"当前线程嗅探到initFlag的状态的改变"+i);
    }

    public static void main(String[] args){
        VolatileVisibilitySample_2 sample = new VolatileVisibilitySample_2();
        Thread threadA = new Thread(()->{
            sample.refresh();
        },"threadA");

        Thread threadB = new Thread(()->{
            sample.load();
        },"threadB");

        threadB.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }

}
