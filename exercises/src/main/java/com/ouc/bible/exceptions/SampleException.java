package com.ouc.bible.exceptions;

import java.util.Random;

/**
 * SampleException
 *
 * @author skyUnv
 * created on 2018/8/13 16:35
 */

public class SampleException {

    public static void main(String[] args) {
        Random random = new Random(1007);
        int i=0;
        while(true){
            try{
                if(random.nextInt(25)==13){

                    System.out.println("ok!! i="+i);
                    break;
                }
                else
                    throw new NullPointerException();
            }catch (NullPointerException e){
                System.out.println("i= "+(++i));

            }
        }
    }
}
