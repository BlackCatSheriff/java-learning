package edu.ouc.it.springmvc.entities;

/**
 * UseR
 *
 * @author skyUnv
 * created on 2018/10/20 15:56
 */
public class UseR {
    private Integer id;
    private String name;
    private int age;
    private Address address;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

//    @Override
//    public String toString() {
//        return "UseR{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                ", address=" + address +
//                '}';
//    }


    @Override
    public String toString() {
        return "UseR{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public UseR(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UseR(){

    }
}
