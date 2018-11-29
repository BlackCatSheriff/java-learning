# 入门

## HelloMybatis

1. 配置 mybatis-config.xml 主要用来配置 数据库连接池和事务管理
2. 给每个 JOPO 配置 mapper，namespace
3. 创建 SqlSessionFactory 获得 SqlSession。
   - SqlSessionFactory 有 SqlSessionFacotoryBuilder.build() 建立，并且传入 mybatis 主配置文件
4. 使用 SqlSeesion 操作对象
5. ID 自增属性，在进行了 insert into 之后自动把原来对象 的 ID 赋值数据库的 ID 号

## Log4j 监控 sql

1. 使用 log4j 

2. 添加 log4j.properties 文件

   ```java
   # Global logging configuration
   log4j.rootLogger=ERROR, stdout
   # MyBatis logging configuration...
   #把后面的包修改为要监控的mapper所在的包(包及其子包的所有mapper的运行过程中的sql和参数)
   log4j.logger.edu.ouc.edu=TRACE
   # Console output...
   log4j.appender.stdout=org.apache.log4j.ConsoleAppender
   log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
   log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
   ```

## 为类型添加别名

1.  在写 mapper 的时候  `parameterType` 全类名很长
2. 因此在 mybatis.xml 中定义 `<typeAliases>`节点
3. 然后 mapper 直接使用 alias 即可

## 配置数据库连接字符串

1. 直接在 mybatis.xml 中的 environment 中配置 property

2. 将上面的 property 用 el 表达式 `${url}` `${dirver}`。。。 

   `#{url, javeType=, jdbcType=}` 应该还有一个 typeHandler 处理数据类型映射

   因此只需要将 数据库连接串属性放在 properties 域里面节可，三种方法

   - 在 mybatis.xml 中定义  properties 节点，里面加入 property 设置
   - 在 properties 节点的属性中 设置 resource 为 db.properties 即可
   - 在 代码 实例化 SqlSessionFactory的时候使用 build("mybatis.xml", property) 方法进行实例化

   > 实际上第二个第三个一样，只是第二种方法不用自己显示用代码读取 resource 



# 使用 Mapper 类实现 SQL binding

1. 初级情况下，调用语句 `seesion.insert("edu.ouc.edu.domain.UserMapper.add", user);` 
2. 这样需要写很长的 标识符来寻找 xml 中的 sql。并且这种静态的字符串不安全，无法进行静态检测
3. 通过观察这个 `标识符`发现，很像一个类+方法.
4. 因此 定义一个接口，然后将所有的方法和 sql 的 ID 相对应
5. 使用 `session.getMapper(class<?> mapperInterface)` 获取一个 Mapper 接口对象的动态代理类，通过调用方法即可实现。
6. mapper 接口编写规范：
   - 类名为 namespace 
   - 方法名名称为 ID
   - 方法参数为 parameterType
   - 返回类型为 resultType，或者 list，map等。。
7. 原理：使用 JDK 的动态代理实现接口，其中 InvocationHandler 中invoke方法 是通过匹配 XML 中 的 `<>`节点实现的静态调用，seesion.select() ... 不是使用的 method.invoke(session, args).

## 使用 mapper 相当于写了 DAO

## ResultMap定义结果集和POJO的映射

1. resultMap 配置
   1. type 代表映射的POJO
   2. id 是映射的名字

2. 内部列配置

   > 主键使用 ID 标签
   >
   > 其他属性使用  result 标签

   1. column:
   2. property:
   3. javaType：就是 java 的数据类型
   4. jdbcYpe: 就是数据库中的类型
   5. typeHandler： 可以自定义类型映射

3. 使用，在 SQL 元素里面 配置 resultMap 不要配置 resultType `这两个互斥 `并且有且仅有一个

## 动态 SQL

1. `<if>` 判断标签，用在任何地方

   - where 的多条件查询，使用 `<where>`处理 少或多的 ADN 
   - update 防止POJO属性为 null  把原来属性更新掉，使用 `<set>` 处理 逗号问题

2. `<where>`  用来搞定 多个 ADN 连接和 头一个 不要 AND 的恶心问题

3. `<where> <set>` 都是通过 `<trim>` 进行实现的

   - `<trim>`

     - prefix: 在内容前面加上

     - prefixOverrides： 去掉 与之匹配的值

     - suffix： 在内容后面加上

     - suffixOverrides: 去掉与之匹配的值

       ```xml
       <trim prefix="" prefixOverrides="" suffix="" suffixOverrides=""></trim>
       ```

   - `<where>`

     - ```xml
       <trim prefix="where" prefixOverrides="AND |OR "></trim>
       ```

   - `<set>`

     - ```xml
       <trim prefix="set" suffixOverides=","></trim>
       ```

4. `<choose> <when> <oherwise>` 这三个相当于  switch 语句，选择一个

5. `<foreach>` 使用 在 SQL 的 IN 操作符常用

   - 属性介绍

     - collection ： 集合名称
     - iterm:  #{it} 获取每个元素
     - open： 开始字符 E.G. `"("`
     - colse： 结束字符 E.G. `")"`
     - se'parator: 分割字符

   - E.G  select * from where age in (1,2,3)

     ```xml
     <select>
     select * form 
         <where>
             <foreach collection="ages" item="it" open="(" close=")" separator=",">
                 #{it}
             </foreach>
     	</where>
     </select>
     ```

## 特殊字符

| 原来字符 | 转移后字符 |
| :------: | :--------: |
|    >    |    `&gt;`    |
|>=|`&gt;=`|
|<|`&lt;`|
|<=|`&lt;=`|

SQL concat 连接字符串(str,str,str)  e.g. concat(‘%’，#{sss},'%')

## 提取SQL

1. `<sql>`  复用代码，include 即可

## 分页查询

1. 由于 seesion 的方法只能传入一个对象，然后在 xml 中使用对象的不同属性进行传值
2. 因此 分页啥的都要有对象  xxxQueryObject

## 可以在 Mapper接口 中用注解方式达到和 xml 一样的效果，但是很丑陋

# 进阶

## 常用 annotation

1. @Alias("")  在 POJO 上使用，相当于在 mybaits.xml 的 配置别名 typeAlias. 但是使用需要配置如何被框架扫描到才行，一般和 spirng 集成使用。单独只打一个标签是不可以的

## mapper.xml 中方法参数

1. SQL Seion 中的 insert/select/update/delete 只能传入一个

2. 默认形况下 sql 标签的 #{} 查询方式：

   - 在入参的属性中查询
   - 如果查不到直接使用 E.G. where id= xxx，这时  #{xxxxxxx} 随便写

3. 配合 mapper 接口使用，不需要  parameterType

4. 想传入多个参数

   > \#{xxx} 默认是隐式调用了 传入对象.xxx 相当于有个 root 对象

   - 封装为一个对象

   - 用 `Map<String, Object>`

     - 使用 的时候 直接 #{key}

   - 在 mapper 的接口参数中，直接使用多个参数，但是需要用 @Param("") 修饰每一个

     - 原理： Mybatis 帮助我们包装为  map；

   - 对比:

     Object 中有 userName 属性

     add(Object a) 和 add(@Param("ob") Object  a)

     在mapper.xml 中 

     第一个使用 #{userName}  获取值

     第二个使用#{ob.userNmae} 而不是 #{userName }

     原因：

     - 第一个 隐式root对象 是 a
     - 第二个 隐式root对象是 被 Mybatis 处理过的 Map 对象，因此需要先使用 key 获取value才能调用 userName


## Many2One

1. 保存操作：

   - 在一个事务中进行
   - 先保存 one 方
   - 然后保存 many 方
   - many 方的外键会被自动 赋值 one 方插入后的id

2. 查询操作

   - 需要在 复杂对象的  resultMap 中添加 association 节点。配置 one 的信息

   - association 节点里面可以继续配置 one 的详细信息，如果 one 是一个复杂对象那么嵌套 association 即可

   - select 属性 是关键，实现了自动组装复杂对象。

     - 内部使用 column 的 值 作为参数调用 了 select 方法

     ```xml
         <resultMap id="employee_mapping" type="edu.ouc.it.domain.Employee">
             <id property="id" column="id" />
             <result property="name" column="name" />
             <association property="dept" column="dept_id" javaType="edu.ouc.it.domain.Department"  select="edu.ouc.it.mapper.DepartmentMapper.get" />
         </resultMap>
     ```


## 懒加载

1. 默认情况下没有懒加载

2. 在 mybatis.xml 中配置 

   ```xml
       <settings>
           <!-- 配置懒加载，关联对象 -->
           <setting name="lazyLoadingEnabled" value="true"/>
       </settings>
   ```

3. 只有 one 属性被使用的时候才会加载，否则不会加载

4. 但是 默认的 toString 函数调用会加载 one

5. 因此需要配置 那些方法会触发延迟对象加载，默认触发延迟加在方法 `equals clone hasCode toString`

## N+1 问题

1. 对于查询结果是列表的嵌套对象。

2. 如果在配置 resultMap 的时候 使用了 association 的 select 属性。

3. 那么主对象和内嵌对象是分开进行查询，然后组装为一个对象

4. 所以，如果一个 复杂对象含有 3 个子对象，并且主对象有1000个，那么需要进行的 sql 查询次数 = 3*1000 +1   (3是查询内嵌对象消耗的SQL，1是查询主对象list的一句)

5. 上面的问题就叫做 N+1

6. 优化方法：

   - 由于 N+1问题是 内嵌对象和主对象分开查询然后组装造成的SQL次数增多
   - 所以，我们 自己写 SQL 一次就拿到 主对象的所有属性 然后自己配置 数据表映射到主对象的关系即可
   - 通过优化，就可以 只需要 1 句 SQL 即可
   - 代码：
     - resultMap 不要 select 子属性
     - SQL 用 join 即可

   ```xml
       <resultMap id="employee_list_mapping" type="edu.ouc.it.domain.Employee">
           <id property="id" column="id" />
           <result property="name" column="name" />
          <association property="dept" javaType="edu.ouc.it.domain.Department">
              <id column="did" property="id"/>
              <result column="dname" property="name"/>
          </association>
       </resultMap>
   
       <select id="list" resultMap="employee_list_mapping">
           select e.id, e.name, e.dept_id, d.id as did, d.name as dname
            from emploee e left join dept d on e.dept_id = d.id;
       </select>
   ```

## 一级缓存

1. 默认有一级缓存，生命周期在 session 中。想要使用就需要保证在一个 session下操作
2. 因此在面对大量数据的时候，必须每次查询之后清理 一级缓存，否则 内存不够
3. 使用 cleanCache 用来清楚一级缓存中的所有数据

## Many2One

添加

1.  数据库表结构还是 主外键

2. POJO 保存的时候不用管 外键，因此外键可空

3. 保存完然后 update 外键关系


获取：

1. 主要解决 one 对象的映射问题，主要是  构造  many 对象
2. 在 ResultMap 里面添加 `<collection>` 节点，使用 select 内联查询，查询参数就是 one 的 id，因此 在 column 里面写上 id。然后调用 select 内联查询得到结果。注意 collection 里面的对象类型使用  ofType 定义不是 javaType 定义。

## many2one   one2many many2many 都用两种组装方法
- 使用  select 标签 内联查询进行组装，多次查询嵌套对象
- 使用  join SQL 直接获得，然后进行结果映射

## 多对多处理思路
- 保存：首先分别保存对象，然后保存关系
	- 多对多保存需要额外一张表
- 获取：两种方式 构建映射关系

## 二级缓存

1. 一般对于 读 >>> 写 才开启

2. 需要在 mapper 中单独配置 `<cache/>` 

3. 开启了 二级缓存 那么这个对象的 所有 select 都会享受 二级缓存

4. POJO 必须实现 序列化接口

5. Mybatis 中 对表格的任何操作都会刷新二级缓存

   - 例如：读一个对象； 添加了一个 ；又读相同对象，没有使用缓存，全部都被刷新了。没有 get 缓存级别，都是 query 缓存级别

6. 缓存的控制：

   - 在 select 中的 get 级别开启 userCache=true
   - 关闭 所有 query 级别的 useCache=false
   - update delete 必须刷新，使用默认就可以
   - insert into 不刷缓存 flushCache=false，默认都是开启刷新

7. 使用第三方二级缓存：

   在 `<cache/>` 属性的 type 中配置 第三方类进行处理

   放入指定的区块，在 第三方的 cache 的 name 中 填入 mapper 的 namespace

8. 利用第三方真正实现 二级缓存：

   核心：更新什么就只刷新缓存中的那一部分

   方法：

   - 将 update delete 设置为 flushCache = false
   - 然后 在代码中 update 或者 delete 某个对象的时候，先拿到这个缓存块然后手动删除或者更新这一个对象，其他都不变
   - 

