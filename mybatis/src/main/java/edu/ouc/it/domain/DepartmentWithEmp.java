package edu.ouc.it.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Department
 *
 * @author skyUnv
 * created on 2018/11/4 23:12
 */
public class DepartmentWithEmp {
    private long id;
    private String name;
    private List<EmployeeWithoutDept> ls = new ArrayList<EmployeeWithoutDept>();

    public List<EmployeeWithoutDept> getLs() {
        return ls;
    }

    public void setLs(List<EmployeeWithoutDept> ls) {
        this.ls = ls;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
