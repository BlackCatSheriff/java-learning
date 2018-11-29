package edu.ouc.it.springmvc.handlers;

import edu.ouc.it.springmvc.entities.Address;
import edu.ouc.it.springmvc.entities.UseR;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.nio.cs.US_ASCII;

import java.util.Date;
import java.util.Map;

/**
 * RequestMappingTest
 *
 * @author skyUnv
 * created on 2018/10/20 11:37
 */


@Controller
@RequestMapping(value = "/RequestMapping")
public class RequestMappingTest {

    public static final String SUCCESS = "success";

    @RequestMapping(value = "/TestMethod", method = RequestMethod.POST)
    public String TestMethod(){
        System.out.println("TestMethod");
        return "success";
    }

    @RequestMapping(value = "/TestParam/{id}/{age}" )
    public String TestParam(@PathVariable("id") Integer id, @PathVariable("age") Integer age ){

        System.out.println(id+"--"+age);
        return "success";
    }



    @RequestMapping(value = "/TestRest/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String TestRestPUT(@PathVariable("id") Integer id){
        System.out.println("put:"+id);
        return "success";
    }


    @RequestMapping(value = "/TestRest/{id}", method = RequestMethod.POST)
    public String TestRestPOST(@PathVariable("id") Integer id){
        System.out.println("POST:"+id);
        return SUCCESS;
    }


    @RequestMapping("/TestPojo")
    public String TestPojo(@ModelAttribute("pojo_class") UseR user1){
        System.out.println("pojo" + user1);
        return SUCCESS;
    }

    @RequestMapping("/TestRender")
    public ModelAndView TestRender(){
        ModelAndView modelAndView = new ModelAndView(SUCCESS);
        modelAndView.addObject("time",new Date());
        Address address= new Address();
        address.setCity("青岛");
        address.setProvince("山东");

        modelAndView.addObject("addr",address);
        return modelAndView;
    }

    @ModelAttribute
    public void getUser1(@RequestParam(value="id", required=false) Integer id,
                        Map<String, Object> map){
        System.out.println("查询数据库得到对象"+"id==="+id);
        if(id!=null){
        UseR user = new UseR(1,"sty",12);
        map.put("hhh", user);
        System.out.println("exec");
        }
    }

    @RequestMapping("/TestModelAttribute")
    public String TestModelAttribute(@ModelAttribute("hhh") UseR user1){
        System.out.println("update:" + user1);

        return SUCCESS;
    }

    @RequestMapping("/jump")
    public String jump(){
        return SUCCESS;
//        return "redirect: ../111/222";
    }


}
