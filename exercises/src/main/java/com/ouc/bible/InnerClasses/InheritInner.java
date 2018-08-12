package com.ouc.bible.InnerClasses;

/**
 * InheritInner
 *
 * @author skyUnv
 * created on 2018/8/12 12:08
 */

class WithInner{
    public class Inner{
        private int a;
        Inner(int a) {
            this.a = a;
        }
    }
}
public class InheritInner extends WithInner.Inner {

    public InheritInner(int a, WithInner withInner) {
        withInner.super(a);
    }
}
