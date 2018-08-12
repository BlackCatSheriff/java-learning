package com.ouc.bible.InnerClasses;

/**
 * CallbackSample
 *
 * @author skyUnv
 * created on 2018/8/12 10:31
 */

interface CallBack{
    void doit(String answer);
}

class A {

    public void foo(){
        System.out.println("this is a ordinary function!");
        new B().deal("1+1=？", getCallBackRef());
    }

    private class Closure implements CallBack{
        public void doit(String answer) {
            System.out.println("this is a callback function! & answer: "+answer);
        }
    }

    public CallBack getCallBackRef(){return new Closure();}

}


class B{
    public void deal(String question, CallBack callBack){
        System.out.println("copy question!");
        callBack.doit(question+"--->done");
    }
}

public class CallbackSample {
    public static void main(String[] args) {
       A a = new A();
       a.foo();
    }
}
