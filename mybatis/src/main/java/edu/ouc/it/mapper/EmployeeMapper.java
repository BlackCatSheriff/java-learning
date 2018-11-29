package edu.ouc.it.mapper;

import edu.ouc.it.domain.Employee;

import java.util.List;

/**
 * EmployeeMapper
 *
 * @author skyUnv
 * created on 2018/11/4 23:14
 */
public interface EmployeeMapper {
    int add(Employee employee);
    Employee get(long id);
    List<Employee> list();
    List<Employee> list_rude();
}
