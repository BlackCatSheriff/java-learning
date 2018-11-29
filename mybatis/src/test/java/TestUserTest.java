import edu.ouc.it.domain.TestUser;
import edu.ouc.it.mapper.TestUserMapper;
import edu.ouc.it.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * TestUserTest
 *
 * @author skyUnv
 * created on 2018/11/3 17:19
 */
public class TestUserTest {
    @Test
    public void testSave(){
        TestUser tu = new TestUser();
        tu.setAge(11999);
        tu.setName("sty");

        try {
            SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            SqlSession session = sf.openSession();
            TestUserMapper mapper = session.getMapper(TestUserMapper.class);
            mapper.add(tu);
            session.commit();
            session.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUpdate(){
        SqlSession session = null;
        try{
            session = MyBatisUtil.openseesion();
            TestUser tu = new TestUser();
            tu.setId(1L);
            tu.setAge(99999);
            session.update("edu.ouc.edu.mapper.TestUserMapper.update",tu );
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(session!=null)
                session.rollback();
        }finally {
            if(session!=null)
                session.close();
        }

    }

    @Test
    public void testGet(){
        SqlSession  session = MyBatisUtil.openseesion();
        TestUserMapper mapper = session.getMapper(TestUserMapper.class);

        TestUser tu = mapper.get_by_id(1L);
//        TestUser tu = session.selectOne("edu.ouc.edu.mapper.TestUserMapper.get_by_id", 1L);
        System.out.println(tu);

    }

    @Test
    public void testGetTableList(){
        SqlSession session = MyBatisUtil.openseesion();
        TestUserMapper mapper= session.getMapper(TestUserMapper.class);
        List<TestUser> list= mapper.table_list();
        System.out.println(list);
    }


    @Test
    public void testDel(){
        SqlSession session = null;
        try{
            session = MyBatisUtil.openseesion();
            session.delete("edu.ouc.edu.mapper.TestUserMapper.delete_by_id",2L);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(session!=null)
                session.rollback();
        }finally {
            if(session!=null)
                session.close();
        }
    }
}
