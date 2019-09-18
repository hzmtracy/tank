package com.tank.design.web.design.callback;

/**
 * @description: 回调模式
 * @author hzm
 * @Date 2019/9/17
 * @version V1.0
 */

public class EricStudent implements Student {
    @Override
    public void resolveQuestion(ICallback callback) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.tellTeacher("3");
    }
}
