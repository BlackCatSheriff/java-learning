package com.ouc.bible.polymorphism;

/**
 * ReferenceCounting
 *
 * @author skyUnv
 * created on 2018/8/10 16:15
 */
class Shared{
    private int refcount=0;
    public void addRef(){refcount++;}
    public void dispose(){
        if(--refcount==0)
            System.out.println("shared dispose!");
    }
}

class Composing{
    private Shared shared;
    private static int counter=0;
    private final long id=counter++;
    public Composing(Shared share){
        this.shared = share;
        this.shared.addRef();
    }

    public void dispose(){
        System.out.println("disposing " + this);
        shared.dispose();
    }
    @Override
    public String toString() {
        return "compose "+id;
    }
}

public class ReferenceCounting {
    public static void main(String[] args) {
        Shared shared = new Shared();
        Composing[] coms = {
                new Composing(shared),
                new Composing(shared),
                new Composing(shared),
                new Composing(shared),
        };
        for (Composing com : coms) {
                com.dispose();
        }
    }
}
