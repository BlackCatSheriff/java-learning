package com.ouc.bible.exceptions;

/**
 * WhoCalled
 *
 * @author skyUnv
 * created on 2018/8/13 19:03
 */
public class WhoCalled {
    static void f(){
        try{
            throw new Exception("a error!");
        }catch (Exception e){
            for (StackTraceElement ste : e.getStackTrace()){
                System.out.println(ste);
            }
        }

    }

    static void g(){f();}
    static void h(){g();}

    public static void main(String[] args) {
        f();
        System.out.println("================================");
        g();
        System.out.println("================================");
        h();
    }
}
