package edu.ouc.it.mvc_crud_no_sql.entities;

/**
 * Department
 *
 * @author skyUnv
 * created on 2018/10/21 16:41
 */
public class Department {
    private Integer id;
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
