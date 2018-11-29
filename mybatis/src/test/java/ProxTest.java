import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProxTest
 *
 * @author skyUnv
 * created on 2018/11/4 8:10
 */
public class ProxTest {

    static class HelloImpl implements Hello{

        public void say() {
            System.out.println(this.getClass().getSimpleName()+"=== say");
        }

        public int count() {
            System.out.println(this.getClass().getSimpleName()+"==== count");
            return 0;
        }

        public void set(int i){
            System.out.println(this.getClass().getSimpleName()+"======"+i);
        }
    }

    static class ProxyInvocationHandler implements InvocationHandler{

        private Hello target;

        public ProxyInvocationHandler(Hello hello) {
            this.target = hello;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


            System.out.println("------插入前置通知代码-------------");
            //执行相应的目标方法
            Object rs = method.invoke(target,args);
            System.out.println("------插入后置处理代码-------------");
            return rs;

        }
    }

    public static void main(String[] args) {
            Hello hello = (Hello) Proxy.newProxyInstance(ProxTest.class.getClassLoader(),
                    new Class[]{Hello.class},new ProxyInvocationHandler(new HelloImpl()));

            hello.say();
            hello.count();
            hello.set(10);

            Hello hello1 = new HelloImpl();


    }
}
