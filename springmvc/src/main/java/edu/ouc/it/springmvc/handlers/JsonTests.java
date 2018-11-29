package edu.ouc.it.springmvc.handlers;

import edu.ouc.it.mvc_crud_no_sql.entities.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * JsonTests
 *
 * @author skyUnv
 * created on 2018/10/26 18:46
 */

@Controller
public class JsonTests {

    @ResponseBody
    @RequestMapping("/JsonTest")
    public List<Employee> findALlEmp2(){
        List<Employee> empList = new ArrayList<Employee>();
        for(int i =0;i<10;i++){
            empList.add(new Employee(i+100, "sty","1@qq.como",i%2));
        }
        System.out.println(empList);
        return empList;
    }

}
