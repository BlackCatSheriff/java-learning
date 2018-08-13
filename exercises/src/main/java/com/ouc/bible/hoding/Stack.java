package com.ouc.bible.hoding;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Stack
 *
 * @author skyUnv
 * created on 2018/8/12 20:51
 */
public class Stack<T> implements Iterable<T> {
    private LinkedList<T> storage = new LinkedList<T>();
    public void push(T v){ storage.addFirst(v);}
    public T pop(){ return storage.poll();}
    public T peek(){ return storage.peek();}
    public boolean isEmpty(){return storage.isEmpty();}
    public String toString(){return  storage.toString();}
    public Iterator<T> iterator() {
        return storage.iterator();
    }

    public Iterable<T> reversed(){
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private ListIterator<T> it  = storage.listIterator(storage.size());
                    public boolean hasNext() {
                        return it.hasPrevious();
                    }

                    public T next() {
                        return it.previous();
                    }

                    public void remove() {
                        it.remove();
                    }
                };
            }
        };
    }
}


