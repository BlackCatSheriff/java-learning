package com.ouc.bible.hoding;


import java.util.*;

/**
 * AddGroups
 *
 * @author skyUnv
 * created on 2018/8/12 14:02
 */

class A extends Object{
    private String name;

    public A(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
public class AddGroups {
    public static void main(String[] args) {
        List<A> ll = new ArrayList<A>();
        Random random = new Random(47);
        ll.add(new A("1"));
        ll.add(new A("2"));
        ll.add(new A("3"));
        ll.add(new A("4"));
        ll.add(new A("1"));
        A a=ll.get(0);
        ll.remove(a);

        System.out.println(ll.size());
//        System.out.println(ll.indexOf(a));


    }


}
