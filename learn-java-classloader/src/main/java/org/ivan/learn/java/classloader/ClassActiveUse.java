package org.ivan.learn.java.classloader;

import java.util.Random;

/**
 * 类的主动和被动使用
 * 主动引用
 *     - new，直接引用
 *     - 访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作（接口不能赋值）
 *     - 调用类的静态方法
 *     - 反射某个类
 *     - 初始化一个子类，父类会被初始化
 *     - 启动类：比如 java HelloWorld
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−11-04 21:23
 **/
public class ClassActiveUse {
    /**
     * 静态代码块
     */
    static {
        System.out.println("ClassActiveUse");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 主动引用
         */
        //new对象
        new Object();
        //访问类的静态变量
//        System.out.println(Obj.salary);
//        System.out.println(Obj.printSalary());
        //访问接口的静态变量
//        System.out.println(I.a);
        //反射某个类
//        Class.forName("org.ivan.learn.java.classloader.Obj");

        //子类的变量，会引起父类的初始化
//        System.out.println(Child.age);

        /**
         *被动引用
         */
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

interface I {

    int a = 10;
}