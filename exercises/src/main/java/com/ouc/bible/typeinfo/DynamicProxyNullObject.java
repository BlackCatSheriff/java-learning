package com.ouc.bible.typeinfo;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * DynamicProxyNullObject
 *
 * @author skyUnv
 * created on 2018/8/16 9:39
 */

//所有类要实现的接口
interface Robot {
    String name();
}

//作为标记接口
interface Null {
}

class NullRobotProxyHandler implements InvocationHandler {

    private String nullName;
    private Robot proxied = new NullRobot();

    private class NullRobot implements Robot, Null {
        public String name() {
            return nullName;
        }
    }

    public NullRobotProxyHandler(Class<? extends Robot> type) {
        nullName = type.getSimpleName() + " NullRobot!";
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);
    }
}


class NullRobot {
    public static Robot createNullRobot(Class<? extends Robot> type) {
        return (Robot) Proxy.newProxyInstance(NullRobot.class.getClassLoader(), new Class[]{Robot.class}, new NullRobotProxyHandler(type));
    }
}

//一个 robot 实现
class HelloRobot implements Robot {
    private String name;

    public HelloRobot(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}

//另一个 robot 实现
class ByeRobot implements Robot {
    private String name;

    public ByeRobot(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}

public class DynamicProxyNullObject {

    public static void main(String[] args) {
        Robot[] robots = {
                new HelloRobot("User hello bb"),
                NullRobot.createNullRobot(ByeRobot.class),
        };

        for (Robot robot : robots) {
            System.out.println(robot.name());
        }


    }

}
