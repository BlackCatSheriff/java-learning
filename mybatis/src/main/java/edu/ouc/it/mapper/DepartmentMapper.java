package edu.ouc.it.mapper;

import edu.ouc.it.domain.Department;

/**
 * DepartmentMapper
 *
 * @author skyUnv
 * created on 2018/11/4 23:13
 */
public interface DepartmentMapper {
    void add(Department employee);
    Department get(long id);
}
