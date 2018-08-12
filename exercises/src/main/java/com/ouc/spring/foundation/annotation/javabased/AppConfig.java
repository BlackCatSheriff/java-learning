package com.ouc.spring.foundation.annotation.javabased;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.List;

/**
 * AppConfig
 *
 * @author skyUnv
 * created on 2018/5/10 21:27
 */

//configuration 注解就是让这个类自动生成 xml
//reference: https://blog.csdn.net/javaloveiphone/article/details/52182899

@Configuration
//使用注解的方式载入资源文件,载入的资源文件必须是 spring格式的xml，不能写成 jdbc.properties
//使用 <context:property-placeholder location="jdbc.properties"/> 载入实际properties
@ImportResource("classpath:jdbc.xml")
public class AppConfig {

    //这里存放资源文件载入的属性
    /**
     * 资源文件中 字段
    */
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    //这里有一点坑，如果 @Value(${username}),可能获取到的值并不是配置文件中的 username， 即使配置文件使用的键是 username
    //因为 username 是一个系统关键字，所以使用 ${} 这样变量获取的时候可能获取不到正确的数据，
    //一般username 都是当前登陆用户的用户名
    //因此在配置文件中配置的时候，
    //习惯性的使用 jdbc.username 而不是直接 username
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Autowired
    //搜集所有 Store 的实现类
//    private List<Store> store;
    //搜集泛型 Store<String>类 的数据
    private List<Store<String>> store;

    public AppConfig() {
        System.out.println("IOC init ...");
    }

    public static void main(String[] args) {
        //使用被 @configuration 注解的类作为 xml
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //使用 xml 初始化IOC
//        ApplicationContext applicationContext1 = new ClassPathXmlApplicationContext("spring-beanannotation.xml");

    }

    //这里的 BeanName 就相当于 xml 里面的 ID, 这个方法实现了
    //<bean id="store" class ="com.ouc.spring.foundation.annotation.javabased.StringStore" ></bean>
    //默认 BeanName 是方法名
    @Bean(name = "stringStore", initMethod = "init", destroyMethod = "destroy")
    @Scope(value ="prototype")
    public StringStore getStringStore() {
        return new StringStore();
    }

    @Bean(name = "integerStore", initMethod = "init")
    @Scope
    public IntegerStore getIntegerStore() {
        return new IntegerStore();
    }


    @Bean(name = "jdbcDao1",destroyMethod = "cleanup")
    public JdbcDao jdbcDao(){
        return new JdbcDao(url, username, password);
    }

    @Bean(name = "testStringStore")
    public String testStringStoreList(){
        System.out.println("This is autowired list...");
        if (store != null) {
            for (Store<String> stringStore : store) {
                System.out.println(stringStore.getClass().getName() + ": "+stringStore.hashCode());
            }
        }else
            System.out.println("store list is null!!");
        return new String();
    }
}
