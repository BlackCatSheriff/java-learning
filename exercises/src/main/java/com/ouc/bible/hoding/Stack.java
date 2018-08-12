package com.ouc.bible.hoding;

import java.util.LinkedList;

/**
 * Stack
 *
 * @author skyUnv
 * created on 2018/8/12 20:51
 */
public class Stack<T> {
    private LinkedList<T> storage = new LinkedList<T>();
    public void push(T v){ storage.addFirst(v);}
    public T pop(){ return storage.poll();}
    public T peek(){ return storage.peek();}
    public boolean isEmpty(){return storage.isEmpty();}
    public String toString(){return  storage.toString();}
}


