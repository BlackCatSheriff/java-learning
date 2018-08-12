package com.ouc.bible.init_cleanup;

/**
 * FinalizeFunc
 *
 * @author skyUnv
 * created on 2018/8/9 10:32
 */
public class FinalizeFunc {
    boolean isEmpty;

    public FinalizeFunc(boolean status){
        isEmpty = status;
    }

    public void full(){
        isEmpty=true;
    }

    protected void finalize(){
        if(!isEmpty){
            System.out.println(this.hashCode()+":not empty");
        }
    }

    /*
    * 直接 new 的这个对象没有指针指向它，所以 gc 会回收野对象
    * 而f1 有指向暂时不会回收
    * */
    public static void main(String[] args) {
        FinalizeFunc f1=new FinalizeFunc(false);
        System.out.println("f1:" + f1.hashCode());
//        f1.full();
        System.out.println("new:"+new FinalizeFunc(false).hashCode());


        f1=new FinalizeFunc(false);

        System.gc();



    }


}
