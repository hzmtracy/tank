package com.tank.design;

import com.tank.design.web.design.callback.EricStudent;
import com.tank.design.web.design.callback.Student;
import com.tank.design.web.design.callback.Teacher;
import com.tank.design.web.design.observer.OfficialAccount;
import com.tank.design.web.design.observer.User;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DesignApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void observerTest(){
        //被观察的角色
        OfficialAccount officialAccount = new OfficialAccount();

        //观察者
        User userBob = new User("WeChat User-Bob");
        User userTom = new User("WeChat User-Tom");
        User userMe = new User("WeChat User-Me");

        //将观察者注册到可观察对象的观察者列表中
        officialAccount.addObserver(userBob);
        officialAccount.addObserver(userTom);
        officialAccount.addObserver(userMe);

        //发布消息
        officialAccount.publishNewInfo("...新内容...");
        officialAccount.deleteObserver(userMe);
        System.out.println("##########################################");
        officialAccount.publishNewInfo("...新内容123...");

    }

    @Test
    public void testCallback() {
        Student student = new EricStudent();
        Teacher teacher = new Teacher(student);

        teacher.askQuestion(student);

    }

}
