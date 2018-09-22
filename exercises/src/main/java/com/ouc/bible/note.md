[TOC]



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

1. java 中只能有一个 public 类，如果有多个 class 那么这些 class 包外是看不到的，这些类一般是为了辅助 Public 类而生的
2. Java中的每个类都会输出 .class 文件

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

3. **内部类拥有外围类的所有元素访问权**

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

2. 可以使用嵌套类测试外围类，发布的时候只要删除 outerclass$innerclass 即可，传统的放在外围类里面的main函数影响外围类的字节码

   ```java
   public class TestBed{
       public void foo(){};
       public static class Tester{
           public static void main(String[] args){
               TestBed tb = new TestBed();
               t.foo();
           }
       }
   }
   ```

3. 回调函数：相当于传递给处理函数一个函数指针，当处理完事务的时候调用一下函数指针继续处理。在java种内使用类实现一个接口的方式使得这个类 this 当作函数指针

   ```java
   
   interface CallBack{
       void doit(String answer);
   }
   
   class A implements  CallBack{
   
       public void foo(){
           System.out.println("this is a ordinary function!");
           new B().deal("1+1=？", this);
       }
   
       //doit 是一个回调函数
       public void doit(String answer) {
           System.out.println("this is a callback function! & answer: "+answer);
       }
   }
   
   
   class B{
       public void deal(String question, CallBack callBack){
           System.out.println("copy question!");
           callBack.doit(question+"--->done");
       }
   }
   
   public class CallbackSample {
       public static void main(String[] args) {
          A a = new A();
          a.foo();
       }
   }
   //output
   this is a ordinary function!
   copy question!
   this is a callback function! & answer: 1+1=？--->done
   ```

   也可使用内部类完成, 因为 内部类可以享用外围类的所有元素，因此完全可以替代一个外围类的函数

   ```java
   class A {
   
       public void foo(){
           System.out.println("this is a ordinary function!");
           new B().deal("1+1=？", getCallBackRef());
       }
   
       private class Closure implements CallBack{
           public void doit(String answer) {
               System.out.println("this is a callback function! & answer: "+answer);
           }
       }
   
       public CallBack getCallBackRef(){return new Closure();}
   
   }
   ```

## 10.9 内部类继承

1. 内部类的构造函数必须连接到指向其外围类对象的引用，单独继承内部类也不必须完成这步操作。

2. 使用特殊的语法构建这个隐藏的外围类引用

   ```java
   class WithInner{
       public class Inner{
           private int a;
           Inner(int a) {
               this.a = a;
           }
       }
   }
   public class InheritInner extends WithInner.Inner {
   
       public InheritInner(int a, WithInner withInner) {
           withInner.super(a);//这里必须这样写
       }
   }
   
   ```

3. 继承包括内部类的外部类里面定义了同名称的内部类与父类中的内部类没有关系，因为属于不同的命名空间

## 10.10 内部类标识符

1. 普通内部类： Outer\$Inner.class
2. 匿名类： Outer\$1.class   Outer\$2.class

# 第十一章 持有对象

1. 常用接口：

   ```java
   List<Integer> ll = Arrays.asList(1,2,3,4);  //底层是数组，不可以调用 ll.add/delete
   Arrays.<T>asList();   				       //指明 T 作为list的明确类型
   list.sublist(start,end);						  //取出片段
   list.containsAll(list)                           //与查找列表内容的顺序无关
   Collections.sort();                          //排序
   COllections.shuffle();                      //打乱
   Collections.addAll();					//批量添加
   list.retainAll();						  //交集
   Collections.emptyList();					//获取一个空list
   
   
   ```

2. 迭代器：

   - 通用迭代器: Iterator
   - List 的加强迭代器： ListIterator 
     - 可以双向移动，hasNext(), hasPrevious()
     - 可以访问当前元素的前一个位置后一个位置的元素索引
     - list.listIterator(n); 创建指向第n个的迭代器
       - list.listIterator(); 创建指向list首部元素的迭代器
       - list.listIterator(size()-1); 创建指向list尾部元素的迭代器

   > 注：
   >
   > 迭代器长度为 0....size .因此0没有前驱，size没有后继。但是通常意义的数组最后一个元素（即 size-1 ）是有后继的，为size
   >
   > 

## 11.7  LinkedList

1. 底层是链表，适合插入删除，随机访问能力差

2. 获取表头元素：

   | Method     |       列表为空时       |
   | :---------: | :--------------------: |
   | getFirst() | NoSuchElementException |
   | element()  | NoSuchElementException |
   | peek()     |          null          |

3. 删除表头元素:

   |    Method     |       列表为空时       |
   | :-----------: | :--------------------: |
   | removeFirst() | NoSuchElementException |
   |   remove()    | NoSuchElementException |
   |    poll()     |          null          |

4. 删除表尾元素： removeLast()

5. 插入元素：

   - 表头：addFirst()
   - 表尾：
     - add()
     - addLast()

## 11.8 Stack

1. 使用 LinkedList 实现 Stack， 不适用继承方式实现是因为 Stack 不需要暴露 LikedList 的接口，Stack 只要暴露一部分即可。所以使用组合的方式实现。

   ```java
   public class Stack<T> {
       private LinkedList<T> storage = new LinkedList<T>();
       public void push(T v){ storage.addFirst(v);}
       public T pop(){ return storage.poll();}
       public T peek(){ return storage.peek();}
       public boolean isEmpty(){return storage.isEmpty();}
       public String toString(){return  storage.toString();}
   }
   ```

   

## 11.9 Set

1. 测试对象归属集合  `set.contains(o);`

2. 字母序（像单词书一样，从 a 开头的开始罗列，然后在罗列 b 开头） 在构造函数添加比较器

   `new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);`

   降序比较器

   `new PriorityQueue<String>(string.size(),Collections.reverse.Order());`

## 11.10 Map

1.  Map.entrySet() 获取键值对set, 类型为 Map.entry。
2. 获取 key， entry.getKey()
3. 获取 value ， entry.getValue()

## 11.13 Foreach与迭代器

1. 类实现  Iterable 接口中 的 iterator() 方法就可以使用 foreach ，默认实现一个迭代器方法

2. 一个类也可以实现多个 iterable 接口，使用 foreach 语法中 for(Info i : collection) ,collection只要是 iterable 就可以

   ```java
   public class Stack<T> implements Iterable<T> {
       private LinkedList<T> storage = new LinkedList<T>();
       public void push(T v){ storage.addFirst(v);}
       public T pop(){ return storage.poll();}
       public T peek(){ return storage.peek();}
       public boolean isEmpty(){return storage.isEmpty();}
       public String toString(){return  storage.toString();}
       public Iterator<T> iterator() {
           return storage.iterator();
       }
   
       public Iterable<T> reversed(){
           return new Iterable<T>() {
               public Iterator<T> iterator() {
                   return new Iterator<T>() {
                       private ListIterator<T> it  = storage.listIterator(storage.size());
                       public boolean hasNext() {
                           return it.hasPrevious();
                       }
   
                       public T next() {
                           return it.previous();
                       }
   
                       public void remove() {
                           it.remove();
                       }
                   };
               }
           };
       }
   }
   ```

   调用

   ```java
   System.out.println("=====类通过实现接口，调用方法===========");
   for (Integer integer : stack) {
       System.out.println(integer);
   }
   
   System.out.println("=====通过实现返回 iterable 方法，重新定义个迭代器调用方法============");
   
   for (Integer integer : stack.reversed()) {
       System.out.println(integer);
   }
   ```

   

# 第十二章 通过异常处理错误

> 异常最关注的地方应该是异常的命名，必须要望文知意

1. 处理异常有“终结模式”     “恢复模式”  ，一般使用 “终结模式”， "恢复模式"就是 套一层 while 不停执行 try 直到满足要求

   ```java
   public static void main(String[] args) {
       Random random = new Random(1007);
       int i=0;
       while(true){
           try{
               if(random.nextInt(25)==13){
   
                   System.out.println("ok!! i="+i);
                   break;
               }
               else
                   throw new NullPointerException();
           }catch (NullPointerException e){
               System.out.println("i= "+(++i));
   
           }
       }
   }
   ```

2. 可以配合使用 java.util.logging 哦 

3. 在编写抽象基类、接口时候可以在方法定义的时候 throw 一些异常，但是方法中名没有抛出异常，这种做法是占个位子，以后想抛出异常的时候，客户端不需要修改代码。

4. catch 异常应该范围从小到大，Exception 作为常用异常的基类可以放在最外层。

5. Throwable 常用接口:

   - String getMessage()

   - String getLocalizedMessage()

   - String toString()   返回对异常的简单描述

   - printStackTrace()     输出到 标准出错流  System.err

   - printStackTrace(PrintStream)   输出到指定流

   - printStackTrace(java.io.PrintWriter)   一般输出到字符串流中用于日志记录

   - getClass().getName()    获取抛出异常的类型

   - getStackTrace() 返回一个由栈轨迹中元素构成的数组，栈顶(index=0) 是调用序列的最后一个方法调用(抛出 throw 的语句)

     ```java
             try{
                 throw new Exception("a error!");
             }catch (Exception e){
                 for (StackTraceElement ste : e.getStackTrace()){
                     System.out.println(ste);
                     //ste.getMethod();
                 }
             }
         }
     ```

     

6. 重新抛出异常：在catch 中 throw e ， 那么异常抛到上一级并且中止后面的catch语句。

   - 重新抛出异常，如果直接在catch中 throw e， 那么上一级打印的栈信息将是原来的栈信息
   - 重新抛出异常，如果在catch中 throw **e.fillInStackTrace()**， 那么上一级打印的栈信息将是更新后在新的地点抛出的栈信息。这种写法等价于重新 new 一个 异常对象抛出，所以不会对之前的信息有所保留

7. 异常类对象的清理不需要关系，因为在处理之后对象失去引用指针将会被垃圾回收器回收

8. 异常链：在新生成的异常对象中保留原异常信息。通过下面操作后然后再 throw

   - 通过构造器传入的类： Error(java虚拟机报告系统错误)、Exception、RuntimeException
   - 通过调用 initCause() 方法：除上面三类

9. RuntimeError 不需要处理，但是可以抛出这类异常，事实上这属于编程错误。

10. 异常的限制：派生类不能抛出比基类更为严格的异常

  - 在继承或者实现接口的时候，覆盖的方法抛出一场列表必须只能是原来方法中的异常，当基类中方法与接口方法同名但是基类抛出异常和接口抛出异常不一致的时候，应该遵循基类抛出异常，或者不抛出任何异常。
  - 构造器抛出异常不受限制，但是派生类构造器必须抛出基类构造器的异常

# 第十三章 字符串

1. 不可变String。String对象是只读的。下面例子说明在进行＋运算的时候，重新new了String 对象指向了a

   ```java
   public static void main(String[] args) {
       String a="abc";
       System.out.println(a.hashCode());
       a+="12";
       System.out.println(a.hashCode());
   }
   //output
   96354
   92597763
   ```

2. JVM在编译时候会将 String的加法优化为 StringBuilder.append() ，但是一般我们这么写 a+="ss" 并且放在循环里面，这时候就会出问题，编译器会每次创建一个 StringBuilder 然后调用 toString() ，然后赋值 a。不停的创建 StringBulider 对象。导致严重的内存浪费

3. StringBuffer 是线程安全的，StringBuilder 不是

4. 无意识的调用递归爆栈， 由于使用 + 相连，那么 this会默认调用toString()方法，所以形成了递归。

   ```java
   public String toString() {
       return "addr:"+this;
   }
   ```

   正确：

   ```java
   public String toString() {
       return "addr:"+super.toString();
   }
   ```

5. String 常用操作

   | 方法                         | 参数                                                     | 应用                                                         |
   | :--------------------------- | :------------------------------------------------------- | :----------------------------------------------------------- |
   | 构造器                       | String、StringBuilder、StringBuffer、char 数组、byte数组 | 创建String对象                                               |
   | length()                     |                                                          | String中字符个数                                             |
   | charAt()                     | int 索引(0开始)                                          | 获取索引出字符                                               |
   | getChars()、getBytes()       |                                                          | 复制部分字符/字节到指定数组                                  |
   | equals()、equalsIgnoreCase() | 与之比较的Srting                                         | 比较字符是否相同，区分大小写和不区分大小写                   |
   | compareTo()                  | 与之比较的Srting                                         | 返回-1，0，1。按照字典序比较                                 |
   | contains()                   | 与之比较的 CharSequence 类型                             | 是否包含某字符串                                             |
   | contentEquals()              | 与之比较的 CharSequence 类型或者 StringBuffer            | 如果参数内容与该String的内容完全一致。相比equals方法，这个关注的是内容，equals犯法还会进行类型比较 |
   | regionMatcher()              |                                                          | 比较两个字符串的两个区域是否一样                             |
   | startsWith()                 |                                                          | 是否以参数开头的字符串                                       |
   | endsWith()                   |                                                          | 是否以参数结尾的字符串                                       |
   | indexOf(),lastIndexOf()      |                                                          | 返回包含参数的索引位置，没有返回-1，lastIndexOf 倒找         |
   | subString（）                |                                                          | 截取指定区域字符串                                           |
   | concat（）                   |                                                          | 连接字符换，返回一个新的 String 对象                         |
   | repalce（）                  |                                                          | 替换，如果没有替换那么返回原始String对象，否则返回替换后的新对象 |
   | toLowCase(), toUpperCase()   |                                                          | 大小写转换。发生转换返回新对象，没有变化返回原始对象         |
   | trim()                       |                                                          | 删除首位空，返回新对象，否则返回原始对象                     |
   | valueOf()                    |                                                          | 对象到字符串转换函数                                         |
   | intern（）                   |                                                          | 在运行时将 String 对象放入常量池                             |

6. 格式化输出：

   - System.out.format("%s %d ...", str, int); 和C用法一样，System.out.printf() 也可以，使用方法一样

   - 使用 Formatter 类：

     ```java
     Formatter f = new Formatter(System.out);
     f.format("%s %d %.2f","test",100,111.9);
     ```

     Formatter 构造器一般传入 PrintStream(), System.out 就是一个PirntStream，OutputStream 和 File

   - 格式控制符

     | 格式控制符 | 解释       |
     |:----------: |: ----------: |
     | d          | 十进制整形 |
     |c|Unicode 字符|
     |b|boolean值|
     |s|String|
     |f|浮点数|
     |e|科学计数法|
     |x|正数十六进制|
     |h|散列码十六进制|

     %b 对于 非boolean 对象，非null都是 true 包括 0 也是 true，使用了包装类型

   - 使用 String.format() 函数，是一个静态函数按照 格式化列表 返回一个 String 对象

7. 扫描输入：Scanner ，默认使用 空格 分割， 也可使用 正则表达式自定义

# 第十四章 类型信息

## 14.2 Class 对象

1. 每个 .class 文件在加在到 JVM 中都对应一个 Class  对象。这个 Class 对象拥有这个类的所有完整信息，用于这个类对象的所有创建。

2. 获取 Class 对象

   ```java
   //通过类名获取
   Class<?> a1 = Class.forName("com.ouc.bible.A");
   //通过已知对象获取
   Class<? extends A> aClass = a.getClass();
   //通过 类来获取类的信息
   class aaClass = A.class;		//使用 .class 还不会抛出异常信息
   //通过 Class 对象创建类
   aClass.newInstance();
   
   ```

3. 使用 class.forName() 与 .class 加载类信息的区别：

   - .class 生成的 Class 引用不进行获取类的初始化动作，尽可能的使用“惰性”，在必要时进行初始化。但是使用 class.forName() 会在调用完这个方法后就对获取类进行初始化以及父类构造等等
   - 如果是一个  final staic 的编译器常量（写死在代码中的，没有使用 random 这种的），那么不进行初始化也可以读取，但是仅仅是 static 或 final 不可以，必须在使用前进行初始化。

4. 使用 Class.newInstance()创建对象，必须有默认构造函数。

5. 数组是一个父类为 Object 的对象

6. 假如  Circle 是 Shape 的派生类

   ```java
   //这个语句编译不过，因为 尽管 Circle 是 Shape 的派生类，但是 Class<Shape> 现在是 Class类型，那么 class<Circle> 与 Class<Shape> 没有继承关系，因此编译失败
   Class<Shape> c = Circle.class;
   
   //正确写法就是下面，并且可以构建基类对象
   Class<? extends Shape> c = Cricle.class; 
   Shape aShape = a.newInstance(0);  //向上转型构建对象
   
   //还可以指向 向上 超类，但是不能构建 派生类超类对象，因为无法获知超类的类型
   Class<? super Circle> c= c.getSuperClass();
   Object o = c.newInstance();   //只能构建 Object 对象
   ```

7. 强制类型转换：

   ```java
   A b = new B();
   
   B bb = (B)b;
   
   B cc = B.class.cast(b);
   
   ```

## 14.3 类型转换前先做检查

1.  动态的 instanceof： Class.isInstachce  

2. 检查类型是否是属于类族：`baseType.class.isAssignableFrom(aTpe.class)`

   注：这个 baseType 作为类图中最顶端的框，所以 Object 作为参数传递进来会返回 false（除baseType是Object）

3. 结合 动态  instanceof 和 isAssignable 可以递归统计类族确定对象的个数

4. 类型是否相等比较：instanceof  Class.isInstance(obj)       

   ​					obj.getClass() == Class.classs  obj.get.getClass().equals(Class.class)

   > 注：
   >
   > -  instanceof  Class.isInstance(obj)  这两种方式的结果相同
   >
   > - obj.getClass() == Class.classs  obj.get.getClass().equals(Class.class) 这两种方式的结果相同
   >
   > - 区别: 上面一组表明**你是这个类族的么?**（考虑继承）  
   >
   >   ​          下面一组表明**你和我严格相等么？**（不考虑继承）

   

## 14.4 反射

1. 使用 Class 中的方法创建对象 getConstructor(Class...).newInstance()、 getDeclaredConstructor(Class...).newInstance()

   > 区别：
   >
   > - getConstructor 只能获取 public 修饰的构造器
   > - getDeclaredConstructor  可以获取所有声明的构造器(private,  protected, public)

   注: `两个方法的参数列表都是可变参数列表`

## 14.7 动态代理

1. 使用 JDK 提供的动态代理方法，是实现 InvocationHandler 接口。可以实现代理远程加载类

   ```java
   interface Interface{
       void doSomething();
       void somethingElse(String arg);
   }
   
   class RealObject implements Interface{
   
       public void doSomething() {
           System.out.println("doSomething");
       }
   
       public void somethingElse(String arg) {
           System.out.println("somethingElse: "+arg);
       }
   }
   
   class DynamicProxyHandler implements InvocationHandler{
       private Object proxied;
   
       public DynamicProxyHandler(Object proxied) {
           this.proxied = proxied;
       }
   
       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
   
           //可以进行方法过滤
           if(method.getName().equals("somethingElse"))
               return null;
   //            System.out.println("catch the method of somethingElse!");
           return  method.invoke(proxied,args);
       }
   }
   
   public class SimpleDynamicProxy {
       public static void main(String[] args) {
           Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),new Class[] {Interface.class},new DynamicProxyHandler(new RealObject()));
           proxy.doSomething();
           proxy.somethingElse("999999");
       }
   }
   //output
   doSomething
   //下面这俩个输出被 代理过滤掉了
   //catch the method of somethingElse!
   //somethingElse: 999999
   ```

   ## 14.8 空对象

   1. 空对象是一个类的特殊对象，相比 NULL 来讲，在使用的时候不需要检测是否是null 直接调用不会抛出异常。

   2. 针对单一对象的空对象示例;

      ```java
      class Person{
          private String name;
          private int age;
      
          public Person(String name, int age) {
              this.name = name;
              this.age = age;
          }
      
          private static class NullPerson extends Person{
              public NullPerson() {
                  super("null",0);
              }
          }
          //空对象使用 单例，因为所有对象的类所对应的空对象都是一样的
          public static final NullPerson NULL = new NullPerson();
      }
      
      public class NUllObject {
      
          public static void main(String[] args) {
              Person p = Person.NULL;
              if(p==Person.NULL)
                  System.out.println("yeah!");
      
          }
      }
      //output
      yeah!
      ```

   3. 为实现某接口的所有对象创建空对象

      使用动态代理的方式，并且 定义一个 NUll接口 作为标记

      ```java
      //所有类要实现的接口
      interface Robot{
          String name();
      }
      
      //作为标记接口
      interface Null{}
      
      class NullRobotProxyHandler implements InvocationHandler{
      
          private String nullName;
          private Robot proxied = new NullRobot();
          private class NullRobot implements Robot, Null{
              public String name() {
                  return nullName;
              }
          }
      
          public NullRobotProxyHandler(Class<? extends Robot> type) {
              nullName = type.getSimpleName()+" NullRobot!";
          }
      
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              return method.invoke(proxied,args);
          }
      }
      
      
      class NullRobot {
          public static Robot createNullRobot(Class<? extends Robot> type){
              return (Robot)Proxy.newProxyInstance(NullRobot.class.getClassLoader(),new Class[] {Robot.class}, new NullRobotProxyHandler(type));
          }
      }
      
      //一个 robot 实现
      class HelloRobot implements Robot{
          private String name;
      
          public HelloRobot(String name) {
              this.name = name;
          }
      
          public String name() {
              return name;
          }
      }
      
      //另一个 robot 实现
      class ByeRobot implements Robot{
          private String name;
      
          public ByeRobot(String name) {
              this.name = name;
          }
      
          public String name() {
              return name;
          }
      }
      
      public class DynamicProxyNullObject {
      
          public static void main(String[] args) {
              Robot[] robots = {
                      new HelloRobot("User hello bb"),
                      NullRobot.createNullRobot(ByeRobot.class),
              };
      
              for (Robot robot : robots) {
                  System.out.println(robot.name());
              }
          }
      
      }
      //output
      User hello bb
      ByeRobot NullRobot!
      ```

# 第十五章 泛型

1. 泛型方法 ： \<T\> 放在返回类型之前

2. 基本类型的泛型在使用过程中被自动包装。例如在方法调用参数传递的时候。

3. 类型推断只在赋值操作有效

   ```java
   <K, V> Map<K, V> map() {
       return new HashMap<K, V>();
   }
   Map<K, V> mmap = map(); //此时调用函数 map() 后发生了类型推断
   ```

   使用上面例子中的 map 函数的返回值作为另一个函数的参数时候不可以，编译器不会进行类型推断。需要在方法名前面使用 <> 声明准确类型

4. 泛型的擦除问题。  LIst\<String\>  和   List\<Integer> 的 className 都是  List。因此尖括号中如果是类族继承关系，但是使用 List 之后是没有多态性质的。就是因为擦除的问题。擦除后认为是 Class 对象，不具备任何具体信息。

5. 显示工厂就是这个工厂类有 create 方法专门根据特定构造函数  new 对象，隐式的工厂就是使用 class.newInstance () 方法产生对象

6. 创建泛型数组：在给数组分配空间 的时候用 object 先填充，因为没有 new T() 这种用法,或者使用 class的new instance方法来搞定。在获取对象的时候强转一下就OK

   一种方法

   ```java
   package com.ouc.bible.generics;
   
   /**
    * GenericArray
    *
    * @author skyUnv
    * created on 2018/9/22 14:41
    */
   
   class Gen<T>{}
   
   public class GenericArray<T> {
       private Object[] gia; //注意这里底层数据类型是 Object
       private int cnt=0;
       public GenericArray(int sz) {
           this.gia = new Object[sz];
       }
   
       public void put(T obj){
           gia[cnt++]=obj;
       }
   
       public T get(int idx){
           return (T)gia[idx];
       }
   
       public static void main(String[] args) {
           int size=10;
           GenericArray<Integer> gai=new GenericArray<Integer>(size);
   
           for (int i = 0; i < size; i++) {
               gai.put(i+1);
           }
   
           System.out.println(gai.get(0));
           System.out.println(gai.get(0).getClass().getSimpleName());
           
       }
   
   }
   
   ```

   另一种方法:

   ```java
   
   public class GenericArray<T> {
       private T[] gia;        //注意这里底层数据类型是 T
       private int cnt=0;
   
       @SuppressWarnings("unchecked")
       public GenericArray(Class<T> type, int sz){
           gia= (T[])Array.newInstance(type,sz);
       }
   
       public void put(T o){
           gia[cnt++]=o;
       }
   
       public T get(int idx){
           return gia[idx];
       }
   
           public static void main(String[] args) {
           int size=10;
           GenericArray<String> gai=new GenericArray<String>(String.class,size);
   
           for (int i = 0; i < size; i++) {
               gai.put( Integer.toString(i+1));
           }
   
           System.out.println(gai.get(0));
           System.out.println(gai.get(0).getClass().getSimpleName());
   
       }
   
   }
   ```

7. 边界：由于泛型擦除了 类型信息，我们想要调用方法只能使用 Object 已有的，不能满足我们的需求因此，我们要给泛型添加边界，让其具有更强的方法调用能力。具体 `Class< T extends baseClass>`

   ```java
   public static void writ(List<? super Apple> apples){
       apples.add(new Apple());
       apples.add(new Jonathan());// Jonathan是 apple 的子类
   }
   // apples 可以是 Apple 基类(包括 Apple)的List 类型
   // 但是 apples.add 只能添加 Apple 的子类型，这样才符合类型的静态检测。不能添加 Apple的父类型，否则 这个 apples 就向上敞开一个口子，随便加入类型了。
   ```

   List<? extends Class> list, 向这个样的 list 中添加 任何都是不行的，只有null是合法的，但是并无意义。因此需要用 super 来解决这个问题。

   super 这种可以实现将 Apple 对象放入 LIst\<Fruit\> 中。

   






































































































































































# 易错点

1. Arrays.asList()
   -  `System.out.println(Arrays.asList(new Integer[]{1,2,3,4}).size());` 输出 4
   - `System.out.println(Arrays.asList(new int[]{1,2,3,4}).size());` 输出1，被认为是一个**指向数组的指针**
   - 基本类型和类类型不一样







# 总结

1. 构造器是静态方法
2. Java中除了 static 和 final（private 也是final） 方法是前期绑定，其他都是动态绑定
3. final 方法相比没有final的可以提高一点性能，final方法由动态绑定变为静态绑定
4. 异常不能作为重载条件也就是不能作为判别不同方法的依据，不用方法主要是依据方法名和方法的参数决定
5. 可以使用 final 保护 public 对象不被修改，是只读属性。（原因：使用构造函数赋值 final 后就不能改了！） 
6. 动态 instanceof 和 静态的关键字 instanceof`动态instanceof  class.instanceof(obj)` 
7. 







# 小技能

1. 创建泛型数组使用 (T [])Array.newInstance(clazz, count);  `前面的 T[] 是因为泛型的擦除原因`
2. 

