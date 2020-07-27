package cn.hanff.edu.jmm;

/**
 * 可见行测试
 */
public class VolatileVisibilitySample_0 {
    private boolean initFlag = false;  //initFlag 是将要被读取的存储在贮存中的数据

    public void refresh(){
        this.initFlag = true; //普通写操作，(volatile写)        //改变initflag的值
        String threadname = Thread.currentThread().getName();
        System.out.println("线程："+threadname+":修改共享变量initFlag");
    }

    public void load(){
        String threadname = Thread.currentThread().getName();
        while (!initFlag){ //不停的读取initFlag模拟是否能够嗅探到initFlag的改变，如果能够感知到则跳出循环，打印信息
            //线程在此处空跑，等待initFlag状态改变
        }
        System.out.println("线程："+threadname+"当前线程嗅探到initFlag的状态的改变");
    }

    public static void main(String[] args){

        VolatileVisibilitySample_0 sample = new VolatileVisibilitySample_0();
        /**
         * 启动两个线程，线程A模拟当线程B read initFlag的值到工作内存中后修改initFlag的信息
         */

        Thread threadB = new Thread(()->{
            sample.load();
        },"threadB");

        Thread threadA = new Thread(()->{
            sample.refresh();
        },"threadA");

        threadB.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }

}
