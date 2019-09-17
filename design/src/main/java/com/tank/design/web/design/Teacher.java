package com.tank.design.web.design;

/**
 * @description: TODO
 * @author hzm
 * @Date 2019/9/17
 * @version V1.0
 */

public class Teacher implements ICallback {
    private Student student;

    public Teacher(Student student) {
        this.student = student;
    }

    public void askQuestion(Student student){
        student.resolveQuestion(this);
    }

    @Override
    public void tellTeacher(String answer) {
        System.out.println("ok, I know you answer" + answer);
    }
}
