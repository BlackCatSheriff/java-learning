package com.ouc.bible.typeinfo;

import java.util.*;

/**
 * RecurseCountClasses
 *
 * @author skyUnv
 * created on 2018/8/15 9:49
 */

class TypeCounter{
    private Map<Class<?>, Integer> map = new LinkedHashMap<Class<?>, Integer>();
    private Class<?> baseType;
    public TypeCounter(Class<?> baseType){
        this.baseType = baseType;
    }

    public void count(Object obj){
        Class<?> type = obj.getClass();
        if(!baseType.isAssignableFrom(type))
            throw new RuntimeException(type+" is not in "+baseType+" family!");
        classCount(type);
    }

    private void classCount(Class<?> clazz){
        Integer cnt = map.get(clazz);
        map.put(clazz, cnt==null?1:cnt+1);
        Class<?> superClass = clazz.getSuperclass();
        if(superClass!=null && baseType.isAssignableFrom(superClass))
            classCount(superClass);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for(Map.Entry<Class<?>,Integer> pair : map.entrySet()){
            sb.append(pair.getKey().getSimpleName());
            sb.append("=");
            sb.append(pair.getValue());
            sb.append(", ");
        }
        sb.delete(sb.length()-2,sb.length());
        sb.append("}");
        return sb.toString();
    }
}


class BaseClass{ }

class FirstClass extends BaseClass{}

class SecondClass extends  FirstClass{}



public class RecurseCountClasses {

    public static void main(String[] args) {
        Random random = new Random(47);
        List<BaseClass> objs = new LinkedList<BaseClass>();
        List<Class<? extends BaseClass>> allTypes = Arrays.asList(
          BaseClass.class,
          FirstClass.class,
          SecondClass.class
        );
        for (int i = 0; i < 20; i++) {
            try {
                objs.add(allTypes.get(random.nextInt(100007)%allTypes.size()).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }


        /////begin
        TypeCounter tc = new TypeCounter(BaseClass.class);
        for (BaseClass obj : objs) {
            tc.count(obj);
        }

        System.out.println(tc);


        System.out.println(
                BaseClass.class.isAssignableFrom(BaseClass.class.getSuperclass())
                );
//        tc.count(1);



    }

}
