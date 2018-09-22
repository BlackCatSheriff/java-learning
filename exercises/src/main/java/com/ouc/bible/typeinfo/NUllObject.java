package com.ouc.bible.typeinfo;

import sun.dc.path.PathError;

/**
 * NUllObject
 *
 * @author skyUnv
 * created on 2018/8/16 9:27
 */

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private static class NullPerson extends Person{
        public NullPerson() {
            super("null",0);
        }
    }
    //空对象使用 单例，因为所有对象的类所对应的空对象都是一样的
    public static final NullPerson NULL = new NullPerson();
}

public class NUllObject {

    public static void main(String[] args) {
        Person p = Person.NULL;
        if(p==Person.NULL)
            System.out.println("yeah!");

    }
}
