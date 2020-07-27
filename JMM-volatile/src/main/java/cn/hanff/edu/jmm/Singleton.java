package cn.hanff.edu.jmm;

public class Singleton {

    /**
     * 查看汇编指令
     * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
     * 安装Hsdis插件
     */
    private volatile static Singleton myinstance;

    public static Singleton getInstance() {
        if (myinstance == null) {
            synchronized (Singleton.class) {
                if (myinstance == null) {
                    myinstance = new Singleton();//对象创建过程，本质可以分文三步 1.申请空间  3.地址复制给变量 2.实例化
                    //发生指令重排导致
                    //
                }
            }
        }
        return myinstance ;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
