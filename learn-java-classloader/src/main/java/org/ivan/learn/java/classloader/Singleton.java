package org.ivan.learn.java.classloader;

/**
 * 类加载整个过程
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−11-04 21:51
 **/
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
}