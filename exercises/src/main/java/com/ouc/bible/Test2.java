package com.ouc.bible;

import com.ouc.bible.hoding.Stack;

import java.util.Iterator;

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

public class Test2 {
    public static void main1(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        Iterator<Integer> it = stack.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("=====================");

        for (Integer integer : stack) {
            System.out.println(integer);
        }

        System.out.println("=====================");

        for (Integer integer : stack.reversed()) {
            System.out.println(integer);
        }

        System.out.println("=====================");
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
      A b = new A();


//      B bb = (B)b;
//      bb.ttt();
//
//      B cc = B.class.cast(b);

//        dd.ttt();


//        A aa= aClass.newInstance();
//
//        System.out.println(aClass.getName());
//        System.out.println(aClass.getCanonicalName());
//        System.out.println(aClass.getSimpleName());

//        System.out.println(a1.getSuperclass().getName());
//        for (Method method : aClass.getMethods()) {
//            System.out.println(method.getName());
//        }

    }
}
