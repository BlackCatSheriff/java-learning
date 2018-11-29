import edu.ouc.it.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * UserTest
 *
 * @author skyUnv
 * created on 2018/11/3 16:14
 */
public class UserTest {
    @Test
    public void testSave(){
        SqlSessionFactory sf;
        User user = new User("syu","1@qq.com" ,16 ,new Date());
        try {
            // 启动框架
            sf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            // 获取 session 对象
            SqlSession seesion  = sf.openSession();
//            获取 xml 模板 ， 放入对象
            seesion.insert("edu.ouc.edu.domain.UserMapper.add", user);
            // 提交事务
            seesion.commit();
            // 关闭连接
            seesion.close();




        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
