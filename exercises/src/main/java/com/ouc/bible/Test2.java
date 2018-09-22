package com.ouc.bible;

import java.util.LinkedList;
import java.util.List;

/**
 * Test2
 *
 * @author skyUnv
 * created on 2018/8/10 14:59
 */

class A {
    private int id;

    public A(int id) {
        this.id = id;
    }
public A(){}

    public int getId() {
        return id;
    }
}

class B extends A {
    private int id;

    public int getId() {
        return id;
    }
public B(){}
    public B(int id) {
        super(id);
        this.id = id;
    }

    public void ttt(){
        System.out.println("BBB special!");
    }

}


class C extends A {
    public C(int id) {
        super(id);
    }
}


class D extends C{
    public D(int id) {
        super(id);
    }
}

class E extends D{
    public E(int id) {
        super(id);
    }
}

class TT{
    public int a;

    @Override
    public String toString() {
        return "TT{" +
                "a=" + a +
                '}';
    }
}

class FX<T>{
    T var;

    public FX(T var) {
        this.var = var;
    }





}


public class Test2 {

    public static void change(TT t){
        t.a=100;
        t=new TT();
        t.a=66;

    }

    public static void f(Integer i){
        i=8;
    }

    public  static void ff(String as){
        as="heloo";
    }



    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
    List<? super C> ls=new LinkedList<A>();
    List<? extends C> lss=new LinkedList<C>();

    lss.add(new D(456));



        ls.add(new D(12));
        ls.add(new C(1));
        ls.add(new E(1));
        ls.add(new B());
        ls.add(new A(123));



    }
}
