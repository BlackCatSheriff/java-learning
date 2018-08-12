package com.ouc.bible.init_cleanup;

/**
 * Enumeration
 *
 * @author skyUnv
 * created on 2018/8/9 16:53
 */
public enum Enumeration {
    D,C,B,A
}

class TestEnum{
    public static void main(String[] args) {
        Enumeration e = Enumeration.A;
//        System.out.println(e);

        for (Enumeration enumeration : e.values()) {
            System.out.println(enumeration+" "+enumeration.ordinal());
        }
    }
}

