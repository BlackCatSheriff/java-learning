package com.ouc.bible;

import com.ouc.bible.hoding.Stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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
        while(it.hasNext()){
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
        while(!stack.isEmpty())
            System.out.println(stack.pop());
    }


    public static void main(String[] args) {
        int a[] = {1,2,3,4,5,6};
//        List<Integer> list = new ArrayList<Integer>(Arrays.asList(a));
//        System.out.println(list);
//        Collections.shuffle(list,new Random(47));
//        System.out.println(list);

//        System.out.println(Arrays.asList(a).size());
        System.out.println(Arrays.asList(new int[]{1,2,3,4}).size());

        Set<Integer> set = new TreeSet<Integer>();
        set.add(1);
//        Collections.shuffle(Arrays.asList(a),new Random(10007));

//        System.out.println(Arrays.toString(a));
    }
}
