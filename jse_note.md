# 常用类
## String
* implements java.io.Serializable, Comparable<String>, CharSequence
    1.可序列化
    2.可比较
* private final char value[]
    1.不可变字符序列
    2.底层数据结构：final char[]
* 通过字面量声明的字符串，字符串常量保存在常量池中，常量池不会储存两个相同的字符串
* String与基本数据类型的转换：parseXXX(String str)；String.valueOf()
* String与Char[]的转换：String.toCharArray()；new String(Char[] char)
* String与Byte[]的转换：String.getBytes()；new String(Byte[] byte)
### StringBuffer(1.0)
* 可变字符序列，则长度可变
* 线程安全
### StringBuilder(1.5)
* 可变字符序列，则长度可变
* 线程不安全，效率较高，推荐使用
* 底层数据结构：char[]
* 常用方法
```
1. 增：StringBuilder append()
2. 删：StringBuilder delete(int start, int end)
3. 改：StringBuilder replace(int start, int end, String str)
4. 插：StringBuilder insert(int offset, String str)
5. 翻：StringBuilder reverse()
6. 改：setCharAt(int index, char ch)
```
## Time&Date
```
1.System.currentTimeMillis()：返回long，获取当前时间戳(距离1970/1/1/00:00:00)
```
#### java.util.Date
```
1. Date()
2. Date(long date)
3. String toString() //Tue Mar 02 17:27:01 CST 2021
4. long getTime() //1614677221636
```
#### java.sql.Date
* 对应数据库中日期类型
```
1.Date(long date)
// java.util.Date转java.sql.Date
2.java.sql.Date date = new java.sql.Date(java.util.Date.getTime())
```
#### java.text.SimpleDateFormat
* 日期<-->文本 
```
// 日期->字符串
// 空参构造器
SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat();
Date date1 = new Date();
String dateStr1 = simpleDateFormat1.format(date1);
System.out.println(date1); //Wed Mar 03 21:12:22 CST 2021
System.out.println(dateStr1); //21-3-3 下午9:12

// 字符串->日期
String datestr2 = "2021-3-3 上午12:30";
try {
    Date dateParse = simpleDateFormat1.parse(datestr2);
    System.out.println(dateParse); //Wed Mar 03 00:30:00 CST 2021
} catch (ParseException e) {
    e.printStackTrace();
}

// 自定义格式，使用带参构造器
SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MMMM-dd hh:mm:ss");
System.out.println(simpleDateFormat2.format(date1)); //2021-三月-03 09:18:43
```
#### Calenda日历类
* Calenda是一个抽象类，具体实现类为GregorianCalendar
```
// 实例化两种方法
GregorianCalendar calendar1 = new GregorianCalendar();
Calendar calendar2 = Calendar.getInstance();
// 方法测试
System.out.println(calendar1.get(Calendar.DAY_OF_MONTH)); //3
System.out.println(calendar2.get(Calendar.DAY_OF_WEEK)); //4 注：第一天为星期日
// set()修改Calendar
calendar1.set(Calendar.DAY_OF_MONTH,1);
System.out.println(calendar1.get(Calendar.DAY_OF_MONTH)); //1
// add()修改Calendar
calendar1.add(Calendar.DAY_OF_MONTH,1);
System.out.println(calendar1.get(Calendar.DAY_OF_MONTH)); //2
// Calendar->Date
System.out.println(calendar2.getTime()); //Wed Mar 03 21:30:35 CST 2021
// Date->Calendar
calendar1.setTime(new Date());
System.out.println(calendar1.get(Calendar.DAY_OF_MONTH)); //3
```
## Time&Date(jdk1.8)
* 旧时间日期类因为可变性、偏移性、格式化()等弊端被舍弃
* java.time 包及其子包
#### Local 时间日期类
```
// now() 实例化
LocalDate localDate1 = LocalDate.now();
LocalTime localTime1 = LocalTime.now();
LocalDateTime localDateTime1 = LocalDateTime.now();
System.out.println(localDate1); //2021-03-03
System.out.println(localTime1); //21:59:09.504
System.out.println(localDateTime1); //2021-03-03T21:59:09.504
// of() 指定时间实例化
LocalDate localDate2 = LocalDate.of(2021, 3, 3);
System.out.println(localDate2); //2021-03-03
// getXXX() 获取指定时间信息
System.out.println(localDate1.getYear()); //2021
System.out.println(localTime1.getHour()); // 22
System.out.println(localDateTime1.getDayOfMonth()); //3
```
#### Instant
* 瞬时点，可用于记录应用的时间戳
```
// now() 实例化
Instant instant1 = Instant.now();
// 本初子午线标准时间
System.out.println(instant1); //2021-03-03T14:09:10.098Z
// 时间戳 实例化
Instant instant2 = Instant.ofEpochMilli(new Date().getTime());
System.out.println(instant2); //2021-03-03T14:11:16.223Z
```
#### DateTimeFormatter 
* 格式化/解析时间、日期
##比较器
* 用于解决引用类型数据的比较问题，主要是 > 或 <
* 自然排序：实现Comparable接口，重写compareTo(T o)
```java
class Student implements Comparable{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 实现Student名字比较排序
    @Override
    public int compareTo(Object o) {
        if(o instanceof Student){
            Student p = (Student)o;
            return this.name.compareTo(p.getName());
        }else throw new RuntimeException("比较类型不一致");
    }
}
```
* 定制排序：实现Comparator接口，重写compare(T o1, T o2)，创建一个比较器
* 当类型没有实现Comparable接口又不方便修改代码，或者已实现的排序规则不适合当前需求
```
public static void main(String[] args) {
    Good[] goodList = new Good[6];
    goodList[0] = new Good("phone",3459);
    goodList[1] = new Good("fish",28);
    goodList[2] = new Good("pen",2);
    goodList[3] = new Good("book",32);
    goodList[4] = new Good("macBook",8459);
    goodList[5] = new Good("aBook",8459);
    Arrays.sort(goodList, new Comparator<Good>() {
        // 使用匿名类创建一个比较器
        @Override
        public int compare(Good o1, Good o2) {
            if (o1 instanceof Good && o2 instanceof Good) {
                if (o1.getPrice() > o2.getPrice())
                    return 1;
                else if (o1.getPrice() < o2.getPrice())
                    return -1;
                else return (o1.getName().compareTo(o2.getName()));
            }else throw new RuntimeException("类型不一致");
        }
    });
    System.out.println(Arrays.toString(goodList));
}
```
## System
* 
```
1. public static native long currentTimeMillis()：返回时间戳
2. public static void exit(int status)：退出当前程序，返回0表示正常退出
3. public static String getProperty(String key)：用于获取系统中属性名为key的属性值
   常用于获取 java.version、java.home、os.version、os.name、user.name、user.home、user.dir        @Test
        public void test1(){
            System.out.println(System.getProperty("java.version")); //1.8.0_111
            System.out.println(System.getProperty("java.home")); //D:\installs\JDK\jdk1.8.0_111\jre
            System.out.println(System.getProperty("os.name")); //Windows 10
            System.out.println(System.getProperty("os.version")); //10.0
            System.out.println(System.getProperty("user.name")); //lzd
            System.out.println(System.getProperty("user.home")); //C:\Users\lzd
            System.out.println(System.getProperty("user.dir")); //D:\affairs\github\JSE
        }
```
#### Comparable,Comparator比较
* Comparable是定义在需要比较的类型的内部，即它属于这个类型；Comparator可以看作一个专门用于比较的类，它在类型外部；
Comparable的好处从设计时就规定了比较规则，一劳永逸。Comparator的好处是灵活，可以对设计时未考虑比较的类型进行比较排序。
## Math
* 一系列用于数学计算的静态方法
#### BigInteger
* 表示任意精度的不可变整数，根据字符串构造对象
#### BigDecimal
* 表示任意精度、不可变、带符号的十进制定点数




# IO(输入输出)
### 定义与作用
* I/O技术是用于处理设备间数据传输的技术，如读/写文件，网络通讯等。Java中，数据的输入/输出以“流”的形式的方式进行
* 所谓输入/输出是相对的，对于程序员，占位通常是Java程序
* 流的分类
    * 数据单位：字节流，字符流
    * 流向：输入流，输出流
    * 角色：节点流，处理流（处理流是在节点流的基础上优化了的数据流）
* 抽象基类
    * InputStream，OutpuStream
    * Reader，Writer
### File类
* java.io.File：文件和文件路径的抽象表示，与平台无关

* File类能创建、删除、重命名文件(夹)，但访问文件内容需要通过IO流

* Java访问系统中一个文件(夹)，需要一个File对象，但Java中的一个File对象，可能没有真实存在的文件(夹)与之对应

*  package com.toJSE.toIO.file.File_test


### 各种IO流
##### 字节流
* FileInputStream
* FileOutpuStream
##### 字符流
* FileReader
* FileWriter
##### 缓存流
* BufferedFileInputStream
* BufferedFileOutpuStream
* BufferedFileReader
* BufferedFileWriter
##### 转换流
* InputStreamReader
* OutpuStreamReader
##### 对象流
* ObjectInputStream
* ObjectOutpuStream
##### 数据流
* DataInpuStream
* DataOutpuStream
##### 标IO流
* I/O
##### 打印流
* PrintStream
* PrintWriter
##### 序列化机制
* 允许把Java程序中的对象转化为与平台无关的二进制流，从而将这种二进制流保存磁盘或者通过网络传输。
* 将二进制流通过反序列化就得到Java对象
* 序列化的作用是将实现了Serializable接口的对象转化为字节数据，使其可以保存、传输

# Exception(异常)
### 定义与作用
* 程序运行中，错误是不可避免的，客户端不按标准使用软件的情形也时常发生。这种程序执行不正常的情形，就是异常
* 程序运行在异常出现时会立刻停止，如果不即时处理，程序就会结束。
* 异常处理可以让程序有更好的容错性，加强程序的健壮性。异常处理可以帮助程序员编写代码、保证程序在恶劣的环境中尽可能运行、给用户提示错误
### 各种异常与应用场景
* 异常分类
```
java.lang.Throwable
    |----java.lang.Error:严重错误，一般不编写处理代码
    |----java.lang.Exception:可以进行处理的一般异常
         |----编译时异常(checked)：编译期间可以发现的异常，必须处理
              |----FileNotFoundException
              |----ClassNotFoundException
         |----运行时异常(unchecked)：运行时才能发现的异常，一般不处理
              |----NullPointerException
              |----ClassCastException
              |----ArrayIndexOutofBoundsException
              |----NumberFormatException
              |----InputMismatchException
              |----ArithmeticException
```
* 自定义异常
    * 继承于现有异常：Exception、RuntimeException
    * 提供全局常量：static final long serialVersionUID（序列化版本号）

# Enum(枚举)
### 定义与作用
* 类的对象只有有限个且确定的
* 当需要一组常量时，常使用枚举类
### 自定义枚举类（了解）
### 使用 Enum 关键字创建枚举类（jdk1.5）
### enum类的主要方法：
* enum.values()
* enum.valueof(String name)
* enum.tostring()
### enum类实现接口
* 情形1：直接在枚举类中实现接口方法
* 情形2：分别在各个对象常量中实现接口方法

# Annotation(注解)
### 定义与作用
* 注解是在 jdk1.5 时增加的
* 注解相当于代码的标记，这些标记可在编译、类加载、运行时被读取调用并进行处理
* 注解可以帮助程序员在不改变原代码的情况下，新增补充信息。代码分析工具、开发工具、部署工具可以根据补充信息进行验证或部署
* 注解可以像修饰符一样使用，可用于修饰包、类、构造器、方法、成员变量、参数、局部变量的声明
### 3个基本注解
* @override：校验是否重写父类/接口方法
* @Deprecated：标识当前结构已经较落后，已被新的结构取代，但仍然可用
* @SuppressWarning：抑制警告，如 @SuppressWarnings("unused")
### 自定义注解
* 直接参照 @SuppressWarning
* 自定义注解自动继承接口 java.lang.annotation.Annotation
* Annotation 中成员变量以无参方法形式声明，类型只能是8种基本类型、String、Class、enum、Annotation及以上类型的数组
* 如果只有一个成员变量，参数名一般为 value
* 如果注解含有配置参数且未设置 default 默认值，使用时一定要指定参数值。如果只有一个参数，可以省略 "value="
* 没有成员变量的 Annotation 称为标记，含有成员变量的 Annotation 称为元数据
* 自定义注解必须搭配注解的信息处理流程才有意义
### 元注解
* 元注解是用于修饰注解的注解，即在注解定义时使用的注解
* RetentionPolicy：修饰 Annotation 的生命周期
    * RetentionPolicy.SOURCE 编译时会被抛弃，即 .class 文件不包含这个注解
    * RetentionPolicy.CLASS 默认，保留到 .class 文件
    * RetentionPolicy.RUNTIME，保留至被加载到内存中，只有这种注解可以被反射读取
* Target：指定注解可用于修饰哪些类型
    * 默认可以修饰一切合理的类型
* Documented：指定该注解会保留至 javadoc文档，默认不保留
* Inherited：表示该注解具有继承性，默认没有继承性
* Repeatable(1.8)：可重复注解,Repeatable 元注解参数中的注解的生命周期与可修饰类型必须与被修饰注解一致
* 类型注解(1.8)：Target注解新增两个常量
    * ElementType.PARAMETER：注解能标志在类型变量的声明中
    * ElementType.TYPE_USE：注解能标志在任何使用类型的语句中

# Reflection(反射)
### 定义与作用
* 类的本质是数据
* 当class被加载到内存时，JVM会为被加载的类创建一个Class实例与之对应
* 因为只有注解为Runtime的类能被获取Class实例，所以Class对象也成为运行时类的对象
* 反射就是通过运行时类的对象获取运行时类的数据：字段、方法、构造器、注解等
* 原本Java是静态语言，即需要在编写代码时确定所有的代码，但反射为Java体统了动态性，可以在JVM运行时动态获取相应的类
### 类的加载
* javac.exe -> 生成 .class 字节码文件
* java.exe -> 解释执行字节码文件，将文件加载到内存中，此过程就是类的加载
* 加载到内存中的类叫"运行时类"，"运行时类"就是Class的实例
### 加载过程
* 加载：将字节码文件加载到内存，并创建 Class对象
* 连接：为类变量分配内存与默认初始值
* 初始化：执行类变量赋值动作和静态代码块
### 类加载器
* 将类文件加载到内存中的工具
* 类加载器分类（由顶向下）：
    1. 引导类加载器：加载核心类库

    2. 拓展类加载器：加载JRE/lib/ext中的Jar包
    3. 系统类加载器：加载自定义类
    4. 自定义加载器
* 可以使用 ClassLoader 加载 properties 配置文件 (一般用集合类Properties加载)

# Collection(集合)
### 定义与作用
* Java集合是存储数据的容器（此时的存储指在内存层面）
* Array数组的弊端：
    1. 初始化后长度确定且不能更改
    2. 定义时限定了储存的数据类型
    3. 数组提供的方法有限，且对于增、添、删等操作较不便且效率低
    4. 数组元素有序且可重复
* 集合中各种容器类具有各种各样的储存结构与方法，可以适应许多应用场景
### 集合框架
```
集合
    |----Collection(interface)：储存单列数据
         |----List(interface)(1.2)：储存有序、可重复数据，动态数组
              |----ArrayList(1.2)：List接口主要实现类，线程不安全，效率高；Object[]存储
              |----LinkedList(1.2)：适合频繁删除、插入操作的数据集，双向链表存储
              |----Vector(1.0)：List接口古老实现类，线程安全，效率低；Object[]存储
         |----Set(interface)：储存无序、不可重复数据 (注：Set 接口没有定义新的方法)
              |----HashSet：Set接口主要实现类，线程不安全；可存储 null 值
              |----LinkedHashSet：HashSet的子类，遍历内部数据时可按照添加顺序进行遍历
              |----TreeSet：按照元素指定的属性进行排序
    |----Map(interface)(1.2)：储存双列数据即键值对数据(key,value)
         |----HashMap(1.2)：Map接口主要实现类，线程不安全，效率高；可储存 null 的 key 和 value
              |----LinkedHashMap(1.4)：与LinkedHashSet类似，具有前后指针，适合频繁遍历操作
         |----TreeMap(1.2)：按照 key 的自然排序和定制排序，保证(key,value)有序
         |----Hashtable(1.0)：Map接口古老实现类，线程安全，效率低；不能储存 null 的 key 和 value
              |----Properties(1.1)：用来处理配置文件(.properties)，key与value都是String类型
工具类：Collection
```

### Collection
#### Collcteion方法
```
1. add(Object e)：添加元素
2. addall(Collection c)：添加参数所指容器的全部元素
3. size()：获取元素个数
4. clear()：清空容器元素
5. isEmpty()：判断为空
6. contains(Object e)：判断包含某个元素(内容)，equals()判断
7. containsAll(Collection c)：判断容器是否包含参数所指容器的全部元素
8. remove(Object e)：删除某个元素，删除成功返回ture，否则返回false，equals()判断
9. removeAll(Collection c)：删除容器中参数所指容器含有的全部元素，即删去交集，equals()判断
10. retainAll(Collection c)：保留交集，equals()判断
11. equals(Collection c)：判断两个容器元素是否相同，是否判断顺序分情况
12. hashCode()：返回容器的哈希值
13. toArray()：集合 -> object数组，返回数组
14. toArray(T[ ] a)
15. iterator()：返回iterator实例，用于遍历集合元素
```
#### 迭代器遍历集合 Iterator
```
* Collection.iterator() 返回容器类的迭代器，并默认指向容器第一位元素的上一位
* Iterator.hashNext() 判断指针下一位是否有元素，有则返回ture，否则返回false
* Iterator.next.next() 指针向下移，并返回所指向位置的元素
* Iterator.remove() 从容器中删除当前指针指向的元素
* for each 是 jdk1.5 新增特性，也是一个语法糖，它本质也是迭代器，可以遍历集合和数组
    * 注：for each 无法给元素赋值，因为它是取出元素并将值传递给自定义局部变量
```
#### List方法
```
1. add(int index, E element)：在指定位置添加元素 
2. addAll(int index, Collection c)：在指定位置添加第二个参数所指容器的所有元素
3. indexOf(Object o)：返回指定元素在容器中首次出现的下标
4. lastIndexOf(Object o)：返回指定元素在容器中最后一次出现的下标
5. remove(int index)：删除指定位置的元素，后面的元素前移
6. set(int index,E element)：修改指定位置的元素为第二个参数的指引
7. subList(int fromIndex, int toIndex)：截取参数所指范围的元素，作为一个List返回，左闭右开
```
##### Set方法
* Set没有定义新的方法
* Set中判断元素相同使用 equals()
* 向 Set 中添加元素的类一定要重写 hashCode()、equals()，且 hashCode() 于 equals() 尽可能一致
* 如果不重写 equals() 方法，则使用 Object.equals()，而 Object.equals() 使用的是 "==" 的返回值
* Object.hashcode() 调用底层代码返回随机值，如果不重写则失去意义
##### HashSet 添加元素 a 的过程
1. 调用 a.HashCode() 得到哈希值并经过计算处理得到 a 在数组中的储存位置(即索引位置)
2. 当位置上没有元素时直接添加成功
3. 当位置上有元素或以链表形式存在的多个元素时，比较哈希值
    1.哈希值不同，在链表末尾添加元素
    2.哈希值相同，调用 a.equals()：true则添加失败，false则在链表末尾添加元素
##### LinkedHashSet
* 与 hashSet 相似，但元素在其内部储存中，会附加两个引用指向前后添加的元素
* 这种结构使得 LinkedHashSet 在频繁遍历中效率高于 HashSet
##### TreeSet
* TreeSet 中添加的元素必须属于同一个类
* TreeSet 适合用于数据排序
    * 自然排序：实现 Comparable 接口
    * 定制排序：Comparator 内部临时类
* 自然排序中，比较两个对象是否相同，compareTo()返回0，不再是equals()
* 定制排序中，比较两个对象是否相同，compare()返回0，不再是equals()
### Map
```
1. HashMap.put(K key, V value)：添加元素，如果已有key值存在则覆盖旧value
2. HashMap.remove(Object key)：删除指定key的键值对
3. HashMap.clear()：清空容器键值对
4. HashMap.get(Object key)：获取指定key的value
5. HashMap.containsKey(Object key)：判断是否含有指定的key
6. HashMap.containsValue(Object value) //判断是否含有指定的value
7. HashMap.equals(Object o)：判断两个Map是否相等,即所有键值对相同，与元素添加顺序无关
8. HashMap.isEmpty()：判断Map是否为空
```
#### HashMap
##### LinkedHashMap
#### TreeSet
* 与TreeSet相比，根据 key 排序
#### Hashtable
* 古老类，尽可能不适用
##### Properties
* 用来处理配置文件(.properties)，key与value都是String类型
### Collections
* Collections 是操作Collection和Map的工具类
#### 几个常用 Collections 方法
```
1. Collections.reverse(List list)：反转list集合元素
2. Collections.shuffle(List list)：随机排序list集合元素，即洗牌
3. Collections.sort(List list)：根据自然顺序排列list集合元素
4. Collections.sort(list, Comparator)：根据定制排序排列list集合元素
5. Collections.frequency(Collection c, Object o)：返回集合中指定元素的出现次数
6. copy(List dest, List src)：复制list集合所有元素到另一个list集合中
7. Collections.synchronized***()：将list、map类转化为线程安全并返回
```
# Generic(泛型)
### 定义与作用
* 为什么有泛型？
    1. jdk1.5 之前集合容器类参数任意化设计为Object类型，需要程序员预知参数类型
    2. 没有类型检查，不安全
    3. 强制转型可能出错
* 所谓泛型：就是在定义类、接口时通过一个标识符表示类中某个属性、返回值、参数的类型，这个类型在类使用时显式指定
* 泛型方法：方法声明含有只在方法代码块有效的泛型，如 <T> T[] toArray(T[] a)
* 泛型继承
```
1. A与B是子父类，G<A>与G<B>是完全并列的，没有子父类关系
2. G与T是子父类，G<A>与T<A>是子父类关系
```
* 泛型通配符
```
1. 无界通配G< ? > ：能取出所有数据，类型为Object，但不能存数据
2. 上界通配G< ? extends A> ：可作为G<A>、G<B>的父类，A是B的父类
3. 下界通配G< ? super A>：可作为G<A>、G<B>的父类，A是B的子类
```

#  Thread(多线程)
### 创建线程的方式
1. 继承Thread，重写Thread.run()方法,当这个run只是临时使用时可以使用匿名类
2. 实现Runnable接口，重写Runnable.run()方法，再使用这个类作为参数构造Thread
    * 注：多线程的启动，都只能通过Thread.start()，这个方法既开启进程也执行run()方法；Thread.run()实质也是重写Runnable.run()，Thread也实现了Runnable接口
3. 实现Callable接口，重写call()方法，与Runnable相比具有以下优势：可以有返回值；方法可以抛异常；支持泛型返回值；需要借助FutureTask类，比如获取返回值
4. 线程池：提前创建多个线程放在线程池中，使用时直接获取，用完再放回线程池中，可以节省许多资源
    * 线程池优势：
        * 提高响应速度(减少创建线程时间)
        * 节省资源消耗(重复利用线程)
        * 便于线程管理(corePoolSize、maximumPoolSize、keepAliveTime...)
    * 创建线程池方式：
        1. Executors
        2. ExecutorService
### 同步安全问题
* synchronized(同步监视器)：同步代码块
  
    * 同步监视器可以是任何一个对象，但操作相同数据的对象应该共享相同的同步监视器
* synchronized：同步方法 ：本质仍然使用同步监视器
  
    * 静态方法使用当前类.class作为同步监视器；非静态方法使用this作为同步监视器
* 同步锁(1.5) Lock接口，ReentrantLock实现类：与synchronized相比，Lock需要手动启动/释放锁
* 改变线程状态
    1. sleep()使线程进入阻塞状态一定时间，不释放同步监视器
    2. wait()使线程一直处于阻塞状态并释放同步监视器，直到被notify()唤醒
    3. notify()会唤醒优先级高的一个线程，notifyAll()会唤醒所有wait()的线程
    * wait()、notify()、notifyAll()只能在同步代码块/方法中调用，即只能被同步监视器调用；sleep可在任何范围调用
### 死锁
* 定义：不同线程分别占用着对方需要的同步资源，都在等待对方释放资源，形成的僵持状态
* 解决方法：专门的算法、原则；减少同步资源的定义；尽量避免嵌套同步

