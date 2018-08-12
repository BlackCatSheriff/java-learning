# 第五章 初始化与清理

## 5.5 清理：终结处理和垃圾回收

1. java对象并非总是被垃圾回收：

   - 对象可能不被回收
   - 垃圾回收不等于析构
   - 垃圾回收只与内存相关，因此想清理和内存无关的事情应当明确的使用一个函数来执行操作

2. finalize 函数

   - 用途：
     - 清理申请的空间native
     - 验证终结条件，在 finalize 中输出判断对象在被清理的时候是否满足某种条件，以此发现一些BUG

   - java中所有依靠java创建的对象也就是new的对象全部会由垃圾回收机制来处理，但是使用非new方法申请的内存无法被垃圾回收器监控，主要就是 native 方法，这些方法中如果使用 malloc 分配了内存但是没有 free 那么永远会占用内存，因此需要在 finalize 函数中调用 native 方法来释放空间
   - finalize 函数工作原理：一旦垃圾回收器准备释放对象占用的控件，首先将会调用 finalize 函数，并且在**下一次**实行垃圾回收时候才会真正回收对象占用的空间
   - finalize 函数绝对不可以当作析构函数来使用，使用其实很局限就是清理 native 方法，其他清理与内存无关的动作定义明确的函数来执行

3. 垃圾回收：

   - 垃圾回收只与内存有关，唯一原因就是为了回收程序不在使用的内存空间
   - 垃圾回收不总是会发生，因为进行清理工作也会消耗时间，如果jvm内存没有耗尽那么不会去进行垃圾回收。

4. JVM 垃圾回收：

   - 采取自适应模式，从堆栈或静态存储区扫描所有引用（可以避免 标记法的无法清理循环引用的缺陷，因为循环引用在这种方式下不会被扫描到，直接被清理掉），使用  标记-清扫 & 停止-复制 方式
   - 当对象十分稳定的时候，使用 标记-清扫， 在这个过程中检测如果有太多的碎片空间产生，那么就换用 停止-复制 模式
   - JVM 空间分配采用类似于 传送带 的分配方式，分配新空间的时候只需要移动堆指针即可，但是由于垃圾空间导致这个传送带很长，所以有了垃圾回收器的帮助，在进行垃圾空间回收的同时会重新排布对象，使得活对象紧密排布这样就可以避免频繁的内存页调入调出。

## 5.6 成员初始化

1. 类成员的基本数据类型可以不显示初始化有默认值，引用默认为null
2. 函数里面的基本类型必须初始化才可以通过编译
3. 可以直接在类成员使用  xx=xx 进行初始化， xx=func() 

## 5.7 构造器初始化

1. 构造器初始化在自动初始化之后进行，自动初始化就是 上面类成员直接使用 等号赋值或者使用默认值进行的初始化行为
2. 先初始化静态成员后初始化非静态成员，与定义顺序无关
3. 显式的静态初始化，使用静态块执行，但是静态块的执行晚于等号直接赋值

```java
    static int b;
    static int a=0xff;
    static {
        System.out.println(b);
        System.out.println(a);
        b=88;
    }
//output
0
255
```

## 5.8 数组初始化

1. 基本类型： `int [] a={1,2,3};`
2. 类类型：
   - ```String[] a = {new String("1"),new String("1"),}```
   - `String[] a = new String[]{"str1","str2"}` 这种方式可以直接当作函数参数使用
3. 可变参数列表：可以 在函数定义 Objects[] 数组作为参数，这样就达到可变参数的目的，因为所有的对象都继承于这个基类

```java
    public static void main(String[] args) {
        //se5 之前
        new Test().print_arr(new String[]{"xixi","haha"});
        new Test().print_arr(new Object[]{"xixi",1,6.7,new Integer(99)});
		//se6
        new Test().print_arr("xixi",1,6.7,new Integer(99));
        new Test().print_arr(new Object[]{"xixi",1,6.7,new Integer(99)});
    }

	// java se5 之前
    void print_arr(Object[] objs){
        for (Object obj : objs) {
            System.out.print(obj+" ");
        }
        System.out.println();
    }

	// java se6 开始，函数调用的时候不需要显示声明数组，逗号分割即可
    void print_arr(Object... objs){
        for (Object obj : objs) {
            System.out.print(obj+" ");
        }
        System.out.println();
    }
    //output
    xixi haha 
    xixi 1 6.7 99 	
```

# 第六章 访问控制权限

## 6.1 包

1. .java 中只能有一个 public 类，如果有多个 class 那么这些 class 包外是看不到的，这些类一般是为了辅助 Public 类而生的
2. .Java中的每个类都会输出 .class 文件

## 6.2 Java 访问权限修饰词

1. 包访问控制权限类似于 friendly ，在包范围内随意组合类并且方便访问
2. protected 提供**包访问**权限，即相同包内的其他类可以访问 protected 元素

# 第七章 复用类

1. 代理模式是介于继承和组合之间的，外部对象看起来调用的方法是原来本身的方法名，其实是代理对象的。这种做法可以更加灵活的限制调用者可以调用的函数范围相比继承来讲。

   ```java
   public class Test {
       private Enumeration e=new Enumeration();
   
       public String name() {
           return e.name();
       }
   
       public int ordinal() {
           return e.ordinal();
       }
   }
   // 其中 Test 作为Enumeration 的代理类， Test的name和ordinal方法都是原来 Enumeration的方法，但是外部调用者并不知情
   ```

2. 一定要有必要的清理方法 dispose ，主要是处理除内存外的一切清理工作。内存处理交给垃圾回收器

3. java 中派生类不会隐藏父类的同名方法，他们共同形成重载形式（与C++不同），因此如果明确想覆写而不是重载的话可以使用注解 @override, 这个注解可以排查是否是覆写若不是覆写编译器会给出提示

4. 在设计类的时候属性最好设计为 private 保证随意更改底层实现的权力，可以通过控制方法的 public 或 protected 来提供给外部或者类族使用

5. 继承和组合：is-a  has-a ，是否需要向上转型

6. final 关键字：

   - 基本类型 final ： 表示是一个常量数值
   - 类类型 final： 表示是一个常引用，不可变更指向。但是指向的内容可以改变
   - final 可定义空值，必须在构造函数进行初始化
   - final 可以作为函数参数
   - private 隐含的是一个 final 方法
   - final 方法相比没有final的可以提高一点性能，final方法由动态绑定变为静态绑定

# 第八章 多态

1. 构造器的构造顺序:  域变量全部置零-继承类构造（构造中存在多态则使用子类的函数）-组合类构造-本类构造器

   - 从下面示例可以得出，编写构造函数的时候，尽可能快速、安全的完成对象的初始化不要有额外操作。或者只能调用基类的 final 函数（包括private函数）

   ```java
   class Shape{
       public Shape() {
           System.out.println("before draw..");
           draw();
           System.out.println("after draw..");
   
       }
       public void draw(){
           System.out.println("shape draw!");
       }
   }
   
   class Circle extends Shape{
       private int radius=1;
   
       public Circle(int r){
           radius=r;
           System.out.println("Circle radius is " + r);
           System.out.println("Circle constructed!");
       }
       public void draw(){
           System.out.println("Circle draw!! radius is " +radius);
       }
   }
   
   public class Test {
       public static void main(String[] args) {
           new Circle(5);
       }
   }
   //output
   before draw..
   Circle draw!! radius is 0 //值得注意的是，这里调用的是 circle的draw，不是shape的，发生多态,并且 raduis 是 0，不是 5，因为此时 circle 的构造函数还没进行，因此是默认的零
   after draw..
   Circle radius is 5		//此使 circle 的构造函数开始运作，复制 radius 是 5
   Circle constructed!
   ```

   

2. 手动写的 dispose 方法必须和 C++ 中析构顺序一样，首先把自己的东西销毁了然后一定要 **调用super.dispose()** ,然后逐级销毁直到基类。

3. 共享对象的清理示例：

    ```java
    class Shared{
        private int refcount=0;
        public void addRef(){refcount++;}
        public void dispose(){
            if(--refcount==0)	//只有当前对象的所有共享清楚了才可以销毁
                System.out.println("shared dispose!");
        }
    }
    
    class Composing{
        private Shared shared;
        private static int counter=0;
        private final long id=counter++;	//通过每次创建对象进行初始化操作完成 id 编号，顺带计数器自增
        public Composing(Shared share){
            this.shared = share;
            this.shared.addRef();			//必须调用这个语句
        }
    
        public void dispose(){
            System.out.println("disposing " + this);
            shared.dispose();
        }
        @Override
        public String toString() {
            return "compose "+id;
        }
    }
    
    public class ReferenceCounting {
        public static void main(String[] args) {
            Shared shared = new Shared();
            Composing[] coms = {
                    new Composing(shared),
                    new Composing(shared),
                    new Composing(shared),
                    new Composing(shared),
            };
            for (Composing com : coms) {
                    com.dispose();
            }
        }
    }
    //output
    disposing compose 0
    disposing compose 1
    disposing compose 2
    disposing compose 3
    shared dispose!
    ```

4. 协变返回类型：覆盖父类的方法的返回值可以返回父类的其他派生类的类型不必和父类返回类型一样

    ```java
    class A{}
    class AA extends A{}
    class B{
        A foo(){}
    }
    class C extends B{
        AA foo(){}			//覆写父类的 foo 方法可以返回 AA ， 不用必须返回 A
    }
    ```

# 第九章 接口

1. 接口中域变量默认 public static final， 方法是 public static 

2. 接口的域变量可以是非常量表达式，例如  a = new Random(100).nextInt(10); 只要在装载的时候是确定值就是完成了静态变量的初始化工作。

3. 接口嵌套，当实现某个接口的时候不需要实现嵌套在其内部的任何接口，而且 private 接口不能在定义它的类之外被实现。例如可以类嵌套实现嵌套接口实例化：

   ```java
   interface I1{
       void foo1();
       interface I2{
           void foo2();
       }
   }
   
   class I1Dimpl implements I1{
       public void foo1(){}
       class I2Impl implements I1.I2{
           public void foo2() {}
       }
   }
   ```

# 第十章 内部类

1. 定义内部类的类可以像正常类一样使用内部类，外部想使用内部类需要类型 OuterClass.InnerClass ，通过外部类的方法返回一个内部类的引用来初始化这个类型

2. 非 static 内部类隐式保存了外部类的引用

3. 内部类拥有外围类的所有元素访问权

4. 迭代器模式

    ```java
    // 迭代器接口
    interface Selector {
        boolean end();
        Object current();
        void next();
    }
    
    public class Sequence2 {
        private Object[] items;
        private int next = 0;
        public Sequence2(int size) { items = new Object[size]; }
        public void add(Object x) {
            if(next < items.length)
                items[next++] = x;
        }
        //通过内部类实现这个接口，不打乱外围类的功能业务
    	//并且内部类对外围类所有成员具有访问权限
        private class SequenceSelector implements Selector {
            private int i = 0;
            public boolean end() { return i == items.length; }
            public Object current() { return items[i]; }
            public void next() { if(i < items.length) i++; }
        }
        //获取迭代器
        public Selector selector() {
            return new SequenceSelector();
        }
        public static void main(String[] args) {
            Sequence2 sequence = new Sequence2(10);
            for(int i = 0; i < 10; i++)
                sequence.add(Integer.toString(i));
            Selector selector = sequence.selector();
            while(!selector.end()) {
                System.out.print(selector.current() + " ");
                selector.next();
            }
        }
    }
    ```

5. 直接建立了public 内部类的语法：`Sequence2.Inner i = new Sequence2().new Inner();`

    **创建内部类之前一定要有外部类，因为内部类隐含有外部类的指针，这样才能满足内部类对外部类的所有元素的访问权限** 因此有 new Outerclass().new InnerClass();

    如果内部类是静态的则不需要构建外围类：`Sequence2.Inner i = new Sequence2.Inner();`

6. 内部类返回外围类 OuterClass.this

7. this 使用：单独使用 this 根据调用方法的位置确定 this 的对象，其实类似于 python 中方法中的 self 即调用这个函数的对象的类型。 如果内部类想返回外围类引用则需要 OuterClass.this 返回

    ```java
    public class Sequence2 {
    
        public class Inner{
            Inner(){
                System.out.println("Inner class!");
            }
            //返回 Inner 类的引用
            public String getClassName(){
                return this.getClass().getName();
            }
            //返回 外围类 的引用
            public String getOuterClassName(){
                return Sequence2.this.getClass().getName();
            }
        }
    
    	//返回 这个方法所在的类的引用，即 Sequence2 
        public String getClassName(){
            return this.getClass().getName();
        }
    
    }
    ```

8. 通过定义 protected | private 内部类实现接口的方式隐藏进口的实现细节，在外围类提供得到内部类的引用函数。使用这种内部类不存在扩展接口，因为就算扩展了外部也无法调用。

9. 可以在方法中使用内部类，完全隐藏了接口的实现

    ```java
     public TestFuncInner getFuncInner(){
                class FuncInnerClass implements TestFuncInner{
                    public void foo() {
                        System.out.println("impl func inner class!!");
                    }
                }
                return new FuncInnerClass();
            }
    ```

## 10.6 匿名内部类

1. 匿名内部类可以访问创建他的外围变量，当直接在匿名类中使用的时候外围传递的变量必须是 **final**  的。

   ```java
   public AInterface foo(final int x){//这里的 X 必须是 final 的，因为内部类直接用来赋值
       return new AInterface(){
         private int i = x;	//这里可以直接使用，x为i 赋值
           ....
       };
   }
   ```

   ```java
   abstract class Base{
   	int x;
       Base(int x){
       	this.x= x;    
       }
       abstract public void foo();
   }
   
   public Base foo(int x){//这里的 x 不需要final，因为内部类没有直接使用而是传递给了基类的构造函数
       return new Base(x) {
         public void foo(){}  
       };
   }
   ```

   

2. 匿名类实现工厂方法

   ```java
   
   interface Service{
       void foo();
   }
   
   interface Factory{
       Service getService();
   }
   
   class Service1Impl implements Service{
       public void foo() {
           System.out.println("this is a service1 foo func!");
       }
       //使用 Static 保证只有一个 工厂
       public static Factory factory = new Factory() {
           public Service getService() {
               return new Service1Impl();
           }
       };
   }
   
   class Service2Impl implements Service{
       public void foo() {
           System.out.println("this is a service2 foo func!");
       }
       //使用 Static 保证只有一个 工厂
       public static Factory factory = new Factory() {
           public Service getService() {
               return new Service2Impl();
           }
       };
   }
   
   public class Sequence2 {
   
       public static void deal(Factory factory){
           Service s = factory.getService();
           s.foo();
       }
   
       public static void main(String[] args) {
       deal(Service1Impl.factory);
       deal(Service2Impl.factory);
       }
   }
   ```

## 10.7 嵌套类

1. 嵌套类就是 static 内部类
2. 



























































































































































































# 总结

1. 构造器是静态方法
2. Java中除了 static 和 final（private 也是final） 方法是前期绑定，其他都是动态绑定
3. final 方法相比没有final的可以提高一点性能，final方法由动态绑定变为静态绑定