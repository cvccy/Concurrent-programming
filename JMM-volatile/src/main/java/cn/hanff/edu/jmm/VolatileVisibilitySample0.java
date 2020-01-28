package cn.hanff.edu.jmm;

public class VolatileVisibilitySample0 {
    private boolean initFlag = false;

    public void refresh(){
        this.initFlag = true; //普通写操作，(volatile写)
        String threadname = Thread.currentThread().getName();
        System.out.println("线程："+threadname+":修改共享变量initFlag");
    }

    public void load(){
        String threadname = Thread.currentThread().getName();
        while (!initFlag){
        }
        System.out.println("线程："+threadname+"当前线程嗅探到initFlag的状态的改变");
    }

    public static void main(String[] args){


        VolatileVisibilitySample sample = new VolatileVisibilitySample();

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
