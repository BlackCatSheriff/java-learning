import edu.ouc.it.domain.DepartmentWithEmp;
import edu.ouc.it.domain.EmployeeWithoutDept;
import edu.ouc.it.mapper.DepartmentWithEmpMapper;
import edu.ouc.it.mapper.EmployeeWithoutDeptMapper;
import edu.ouc.it.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * One2ManyTest
 *
 * @author skyUnv
 * created on 2018/11/5 19:20
 */
public class One2ManyTest {
    @Test
    public void save(){
        SqlSession session = MyBatisUtil.openseesion();
        EmployeeWithoutDeptMapper employeeMapper = session.getMapper(EmployeeWithoutDeptMapper.class);
        DepartmentWithEmpMapper departmentMapper = session.getMapper(DepartmentWithEmpMapper.class);

        DepartmentWithEmp d = new DepartmentWithEmp();
        d.setName("bank-end");

        EmployeeWithoutDept e1 = new EmployeeWithoutDept();
        e1.setName("sty");
        EmployeeWithoutDept e2 = new EmployeeWithoutDept();
        e2.setName("sty");

        d.getLs().add(e1);
        d.getLs().add(e2);

        departmentMapper.add(d);
        employeeMapper.add(e1);
        employeeMapper.add(e2);

        for(EmployeeWithoutDept employeeWithoutDept : d.getLs()){
            employeeMapper.updateRelationship(employeeWithoutDept.getId(), d.getId());
        }

        session.commit();
        session.close();
    }


    @Test
    public void get(){
        SqlSession session = MyBatisUtil.openseesion();
        DepartmentWithEmpMapper departmentWithEmpMapper = session.getMapper(DepartmentWithEmpMapper.class);
        System.out.println(departmentWithEmpMapper.get(12L).getLs());
        session.close();
    }


    @Test
    public void get_join(){
        SqlSession session = MyBatisUtil.openseesion();
        DepartmentWithEmpMapper departmentWithEmpMapper = session.getMapper(DepartmentWithEmpMapper.class);

        System.out.println(departmentWithEmpMapper.get_join(12L).getLs());
        session.close();
    }


}
