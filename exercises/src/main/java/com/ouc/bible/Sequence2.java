package com.ouc.bible;


interface Service{
    void foo();
}

interface Factory{
    Service getService();
}

class Service1Impl implements Service{
    public void foo() {
        System.out.println("this is a service1 foo func!");
    }
    //使用 Static 保证只有一个 工厂
    public static Factory factory = new Factory() {
        public Service getService() {
            return new Service1Impl();
        }
    };
}

class Service2Impl implements Service{
    public void foo() {
        System.out.println("this is a service2 foo func!");
    }
    //使用 Static 保证只有一个 工厂
    public static Factory factory = new Factory() {
        public Service getService() {
            return new Service2Impl();
        }
    };
}

public class Sequence2 {

    public static void deal(Factory factory){
        Service s = factory.getService();
        s.foo();
    }

    public static void main(String[] args) {
        deal(Service1Impl.factory);
        deal(Service2Impl.factory);
    }
}


