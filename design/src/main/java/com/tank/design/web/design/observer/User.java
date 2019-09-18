package com.tank.design.web.design.observer;

import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * @description: 观察者模式：观察者
 * @author hzm
 * @Date 2019/9/17
 * @version V1.0
 */

@Data
public class User implements Observer {
    public String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("测试用例:" + name + "本次更新的信息" + arg);
    }
}
