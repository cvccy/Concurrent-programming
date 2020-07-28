package com.cvccy.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MovieTiket implements Delayed {
    //延迟时间
    private final long delay;
    //到期时间
    private final long expire;
    //数据
    private final String msg;
    //创建时间
    private final long now;

    public long getDelay() {
        return delay;
    }

    public long getExpire() {
        return expire;
    }

    public String getMsg() {
        return msg;
    }

    public long getNow() {
        return now;
    }

    /**
     * @param msg 消息
     * @param delay 延期时间
     */
    public MovieTiket(String msg , long delay) {
        this.delay = delay;
        this.msg = msg;
        expire = System.currentTimeMillis() + delay;    //到期时间 = 当前时间+延迟时间
        now = System.currentTimeMillis();
    }

    /**
     * @param msg
     */
    public MovieTiket(String msg){
        this(msg,1000);
    }

    public MovieTiket(){
        this(null,1000);
    }



    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
