package com.ouc.bible.hoding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * ListIteration
 *
 * @author skyUnv
 * created on 2018/8/12 19:23
 */
public class ListIteration {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));
        ListIterator<Integer> it = list.listIterator();
        while(it.hasNext()){
            System.out.println(it.next()+"  "+it.nextIndex()+"  "+it.previousIndex());
        }
    }
}
