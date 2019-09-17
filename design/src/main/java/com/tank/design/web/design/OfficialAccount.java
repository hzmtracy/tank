package com.tank.design.web.design;

import java.util.Observable;

/**
 * @description: 观察者模式：被观察者
 * @author hzm
 * @Date 2019/9/17
 * @version V1.0
 */

public class OfficialAccount extends Observable {

    /**
     * 要给观察者的信息
     * @param info
     */
    public void publishNewInfo(String info){
        // 标识这个Observable对象已经改变，更具体来将就是把Observable中属性changed置为true
        setChanged();

        /**
         * 在通知所有观察者之前，需要判断Observable中属性changed是否为true，如若不为则不会发出通知。
         * 具体可以看源码，蛮好理解的。
         */
        notifyObservers(info);;
    }
}
