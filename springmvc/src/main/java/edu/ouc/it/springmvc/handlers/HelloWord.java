package edu.ouc.it.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HelloWord
 *
 * @author skyUnv
 * created on 2018/10/20 10:28
 */

@Controller
@RequestMapping("/user")
public class HelloWord {

    @RequestMapping("/info")
    public String Hello(){
        System.out.println("hello word");
        return "success";
    }

}
