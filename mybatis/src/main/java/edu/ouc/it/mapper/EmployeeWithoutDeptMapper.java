package edu.ouc.it.mapper;

import edu.ouc.it.domain.Employee;
import edu.ouc.it.domain.EmployeeWithoutDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EmployeeMapper
 *
 * @author skyUnv
 * created on 2018/11/4 23:14
 */
public interface EmployeeWithoutDeptMapper {
    int add(EmployeeWithoutDept employee);
    EmployeeWithoutDept get(long id);
    void updateRelationship(@Param("empId") long empId, @Param("deptId") long deptId);
}
