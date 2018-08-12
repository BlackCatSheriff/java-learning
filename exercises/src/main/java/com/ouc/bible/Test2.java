package com.ouc.bible;

import java.util.ArrayList;

/**
 * Test2
 *
 * @author skyUnv
 * created on 2018/8/10 14:59
 */

class A{
    private int id;

    public A(int id) {
        this.id = id;
    }

    public A() {

    }

    public int getId() {
        return id;
    }
}

class B extends A {
    private int id;

    public int getId() {
        return id;
    }

    public B(int id) {
        super();

        this.id = id;
    }
}


class C extends A{
    public C(int id) {
        super(id);
    }
}

public class Test2 {
    public static void main(String[] args) {
        ArrayList<A> ar = new ArrayList<A>();
        ar.add(new A(2));
        ar.add(new B(2));
        ar.add(new C(2));



    }
}
