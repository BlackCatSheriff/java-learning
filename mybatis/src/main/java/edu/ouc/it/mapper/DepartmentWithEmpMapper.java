package edu.ouc.it.mapper;

import edu.ouc.it.domain.DepartmentWithEmp;

/**
 * DepartmentMapper
 *
 * @author skyUnv
 * created on 2018/11/4 23:13
 */
public interface DepartmentWithEmpMapper {
    void add(DepartmentWithEmp employee);
    DepartmentWithEmp get(long id);
    DepartmentWithEmp get_join(long id);

}
