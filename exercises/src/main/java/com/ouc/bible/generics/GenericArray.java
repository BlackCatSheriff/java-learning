package com.ouc.bible.generics;

import java.lang.reflect.Array;

/**
 * GenericArray
 *
 * @author skyUnv
 * created on 2018/9/22 14:41
 */

class Gen<T>{}

public class GenericArray<T> {
    private T[] gia;        //注意这里底层数据类型是 T
    private int cnt=0;

    @SuppressWarnings("unchecked")
    public GenericArray(Class<T> type, int sz){
        gia= (T[])Array.newInstance(type,sz);
    }

    public void put(T o){
        gia[cnt++]=o;
    }

    public T get(int idx){
        return gia[idx];
    }

        public static void main(String[] args) {
        int size=10;
        GenericArray<String> gai=new GenericArray<String>(String.class,size);

        for (int i = 0; i < size; i++) {
            gai.put( Integer.toString(i+1));
        }

        System.out.println(gai.get(0));
        System.out.println(gai.get(0).getClass().getSimpleName());

    }

}



//
//package com.ouc.bible.generics;
//
///**
// * GenericArray
// *
// * @author skyUnv
// * created on 2018/9/22 14:41
// */
//
//class Gen<T>{}
//
//public class GenericArray<T> {
//    private T[] gia;
//    private int cnt=0;
//    public GenericArray(int sz) {
//        this.gia = (T[])new Object[sz];
//    }
//
//    public void put(T obj){
//        gia[cnt++]=obj;
//    }
//
//    public T get(int idx){
//        return (T)gia[idx];
//    }
//
//    public static void main(String[] args) {
//        int size=10;
//        GenericArray<Integer> gai=new GenericArray<Integer>(size);
//
//        for (int i = 0; i < size; i++) {
//            gai.put(i+1);
//        }
//
//        System.out.println(gai.get(0));
//        System.out.println(gai.get(0).getClass().getSimpleName());
//
//    }
//
//}
