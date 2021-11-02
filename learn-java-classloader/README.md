1. ClassLoader介绍

   是加载类的类，抽象类，负责将一个二进制文件读取，解析，初始化类的数据

2. 类加载的三个阶段

- 加载：查找并加载类的二进制数据

- 链接
    - 验证：确保被加载的类的正确性，魔术因子，什么版本编译
    - 准备：为类的景太变态分配内存，并将其初始化为默认值
    - 解析：将对象的符号引用转化为直接引用：指针或者
- 初始化：为类的静态变量赋值正确的初始值

3. Java使用类的方式

- 主动使用

    - new，直接引用
    - 访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作（接口不能赋值）
    - 调用类的静态方法
    - 反射某个类
    - 初始化一个子类，父类会被初始化
    - 启动类：比如 java HelloWorld

- 被动使用

  除上述6个以外，其余的都是被动使用，不会导致类的初始化，例如访问final修饰的，创建对象数组等。

- 案例：

  主动和被动使用分析

  ```java
  public class ClassActiveUse {
  
      static {
          System.out.println("ClassActiveUse");
      }
  
      public static void main(String[] args) throws ClassNotFoundException {
        //new 一个对象
        new Obj();
        //直接使用接口的变量
        System.out.println(I.a);
        //访问对象的静态变量
         System.out.println(Obj.salary);
         Obj.printSalary();
         //反射
         Class.forName("com.wangwenjun.concurrent.classloader.chapter1.Obj");
         //子类的变量，会引起父类的初始化
         System.out.println(Child.age);
  
        /**
        *被动引用
        /
        // (1)通过子类访问父类的static变量，不会导致子类的初始化.
         System.out.println(Child.salary);
  
        //  (2）定义引用数组，不会初始化类
         Obj[] arrays = new Obj[10];
  
  
        //  (3)final修饰的常量会在编译期间放到常量池中，不会初始化类
         System.out.println(Obj.salary);
        //  (4)final修饰的复杂类型，在编译期间无法计算得出，会初始化类
          System.out.println(Obj.x);
      }
  
  
  }
  class Obj {
  
      public static final long salary = 100000L;
  
      public static final int x = new Random().nextInt(100);
  
      static {
          System.out.println("Obj 被初始化.");
      }
  
  
      public static void printSalary() {
          System.out.println("========Obj=printSalary");
      }
  }
  
  
  class Child extends Obj {
  
      public static int age = 32;
  
      static {
          System.out.println("Child 被初始化.");
      }
  }
  ```

  一个数据的例子，说明类加载的过程

  ```java
  public class Singleton {
  
      //private static Singleton instance = new Singleton();
  
      public static int x = 0;
  
      public static int y;
    
      private static Singleton instance = new Singleton();
  		/**
  		* 第一种情况，创建类在第三行，输出x=1 y=1
  		* 第二种情况，创建类在第一行，输出x=0 y=1
  		*/
     //原因分析 第一种情况
      /**
       * 类的链接-准备阶段 给静态变量赋值默认值 
       * int x = 0;
       * int y = 0;
       * instance = null;
       * 类的初始化阶段 给类的静态变量赋值正确的初始化值
       * x = 0;
       * y = 0;
       * new Singleton()
       *   x++ x=1
       *   y++ y=1
       */
  
    //原因分析 第二种情况
      /**
       * 类链接-准备阶段 给类静态变量赋值默认值
       * 1.instance = null;
       * 2.x = 0;
       * 3.y = 0;
       * <p>
       * 类初始化阶段 给类静态变量赋值正确的初始值
       * <p>
       * instance = new Singleton();
       * x++=>x=1
       * y++=>y=1
       * <p>
       * x = 0;
       * y = 1;
       */
  
      private Singleton() {
          x++;
          y++;
      }
  
      public static Singleton getInstance() {
          return instance;
      }
  
      public static void main(String[] args) {
          Singleton singleton = getInstance();
          System.out.println(singleton.x);
          System.out.println(singleton.y);
      }
  ```

4. 类加载阶段

- 简单来说，就是将class文件中的二进制数据读取到内存中，将其放在方法区，然后堆区中创建一个java.lang.Class对象，用来封装在方法区的数据结构
- 类的加载的最终产物是位于堆区中的class对象
- 加载类的方式
    - 本地磁盘直接加载
    - 内存中直接加载
    - 通过网络加载class  rmi 热部署
    - 从zip，jar等归档文件中加载.class文件
    - 从数据库中提取.class文件
    - 动态编译
-  创建对象的过程：在堆中创建一个对象，对象的数据在堆中存储，对象的类型数据在方法区，有句柄和指针引用两种方式找到对象的类型数据

5. 链接过程

在加载阶段完成后，虚拟机外部的二进制数据就会按照虚拟机所需要的格式存储在方法区中（数据结构），然后在堆区中创建一个Class对象，这个对象作为程序访问方法区中这些数据接口的外部接口

加载和链接阶段的部分内容是基于交叉进行的，比如一部分代码加载完成就可以进行验证，提高效率

- 验证

  验证的主要目的是确保Class文件中的字节流中包含的信息是否符合虚拟机的要求，并且不会损害到JVM自身的安全，class文件的来源有很多，如果想要恶意损害必须经过验证

  验证不成功抛出VerifyError

  文件格式验证

    - 魔术因子是否正确，OxCAFEBASE
    - 主从版本号是否符合当前虚拟机，不同版本的无法兼容 无法执行
    - 常量池中的常量类型是不是不支持
    - etc

  元数据验证

    -  是否有父类
    - 父类是否允许继承
    - 是否实现了抽象方法
    - 是否覆盖了父类的final字段，不允许的
    - 其它的语义检查

  字节码验证

    - 主要是进行数据流和控制流的分析，不会出现这种情况，在操作栈中放置一个int类型，但是却给了一个long的数据

  符号引用验证

    - 调用一个不存在的方法或者字段 版本不一致等
    - 目的是确保解析动作能正常的执行，如果无法通过符号引用验证将会抛出NoSuch的异常

- 准备

  给类变量分配初始值

- 解析

  虚拟机规范之中并未规定解析阶段发生的具体时间，只要求在执行anewarray，checkcast，getfield，instanceof，invokeinterface，invokespacial，invokestatic，invokevirtual，multianewarray，new，putfield和putstatic这13个用于操作符号引用的字节码指令之前，先对他们所使用的符号引用进行解析。所以虚拟机实现会根据需要来判断，到底是在类被加载器加载时就对常量池（？？？？都在常量池嘛？）中的符号引用进行解析，还是等到一个符号引用将要被使用前才会解析他

    - 类或者接口的解析
    - 字段解析
    - 类方法解析
    - 接口方法解析

6. 初始化

- 类加载过程中的最后一步

- 初始化阶段是执行构造函数<clinit>()方法的过程

- <clinit>()方法是由编译器自动收集类中的所有变量的赋值动作和静态语句块中的语句合并产生的

- 静态语句块中只能访问到定义在静态语句块之前的变量，定义在他之后的变量，只能赋值，不能访问

  ```java
  class MyObject {
  
      public static int x = 10;
  
      static {
          System.out.println(x);
          x = 10 + 1;
  //
          y = 200;
          //非法，只能赋值，不能读取
  //        System.out.println(y);
      }
  
     private static int y = 20;
  
  }

- <clinit>()方法与类的构造函数有点区别，他不需要显示的调用父类的构造函数，虚拟机会保证在子类的<clinit>()执行之前，先执行父类的<clinit>()，因此在虚拟机中首先被执行的是Object的<clinit>()方法

  ```java
  class Parent {
  
      static {
          System.out.println("parent");
      }
  }
  
  class Sub extends Parent {
  
      public static int x = 100;
  
      static {
          System.out.println("child");
      }
  
  }
  ```



- 由于父类的<clinit>()方法要先执行，也就意味着父类定义的静态语句块，要优先于子类

- <clinit>()方法对于一个类来说并不是必须的

- 接口中照样存在<clinit>()方法

- 虚拟机有义务保证<clinit>()方法的线程安全

  ```java
  public class ClinitThreadTest {
  
      public static void main(String[] args) {
  
         System.out.println(System.getProperty("sun.boot.class.path"));
  //
         new Thread(() -> new SimpleObject()).start();
  //
         new Thread(() -> new SimpleObject()).start();
      }
  
      static class SimpleObject {
  
          private static AtomicBoolean init = new AtomicBoolean(true);
  
          static {
              System.out.println(Thread.currentThread().getName() + " I will be initial");
              while (init.get()) {
  
              }
              System.out.println(Thread.currentThread().getName() + " I am finished initial.");
          }
      }
  }
  ```



7.  内存结构

- PC寄存器：线程独享，记录当前执行的指令是什么，不会OOM
- 栈
- 堆：存储对象和数组，线程共享；OOM
- 方法区：线程共享； 存放这每个class的结构和字段信息，包含方法数据，方法和构造方方法代码，运行时常量池。OOM。
- 本地方法栈


