import com.alibaba.fastjson.JSON;
import edu.ouc.it.domain.Department;
import edu.ouc.it.domain.Employee;
import edu.ouc.it.mapper.DepartmentMapper;
import edu.ouc.it.mapper.EmployeeMapper;
import edu.ouc.it.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Many2OneTest
 *
 * @author skyUnv
 * created on 2018/11/4 23:22
 */
public class Many2OneTest {
    @Test
    public void save(){
        SqlSession session = MyBatisUtil.openseesion();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);

        Department d = new Department();
        d.setName("bank-end");
        Employee em = new Employee();
        em.setName("sty");
        em.setDept(d);

        System.out.println("before save: "+d.getId());
        departmentMapper.add(d);
        System.out.println("after save: "+d.getId());
        employeeMapper.add(em);

        session.commit();
        session.close();
    }


    @Test
    public void get(){
        SqlSession session = MyBatisUtil.openseesion();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.get(5L);
        System.out.println(employee);
//        System.out.println(employee.getClass().getName());
//        employee.getName();
//        System.out.println(employee.getClass().getName());

//        System.out.println(employee.getDept());
        session.close();
    }


    @Test
    public void list(){
        SqlSession session = MyBatisUtil.openseesion();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> es = employeeMapper.list();
        for (Employee e:es){
            System.out.println(e.getName()+"   "+e.getDept().getName());
        }
    }

    @Test
    public void list_rude(){
        SqlSession session = MyBatisUtil.openseesion();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> es = employeeMapper.list_rude();
        for (Employee e:es){
            System.out.println(e.getName()+"   "+e.getDept().getName());
        }
    }
}
