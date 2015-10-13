package com.jiandan.modal;

/**
 * Created by hongweiyu on 2015/10/13.
 *
 * 网络状态模型
 *
 */
public class NetWorkEvent {

    public static final int AVAILABLE = 1;//可用
    public static final int UNAVAILABLE = -1;//不可用

    private int type;

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public NetWorkEvent(int type){
        this.type = type;
    }
}
