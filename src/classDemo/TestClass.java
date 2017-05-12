package classDemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by bo.wu on 2017/5/2.
 */
public class TestClass {


    public static void main(String[] args)

    {

        try {

//测试Class.forName()

            Class testTypeForName = Class.forName("classDemo.TestClassType");
            Object o = testTypeForName.newInstance();
            Method m = testTypeForName.getDeclaredMethod("print",String.class);

            for (int i = 0; i<10 ;i++){
                m.invoke(o,String.valueOf(i));
            }

            ClassLoader classLoader =  testTypeForName.getClassLoader();



            System.out.println("testForName---" + testTypeForName);

//测试类名.class

            Class testTypeClass = TestClassType.class;

            System.out.println("testTypeClass---" + testTypeClass);

//测试Object.getClass()

            TestClassType testGetClass = new TestClassType();

            System.out.println("testGetClass---" + testGetClass.getClass());


        } catch (ClassNotFoundException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}

class TestClassType {

//构造函数

    public TestClassType() {

        System.out.println("----构造函数---");

    }

//静态的参数初始化

    static {

        System.out.println("---静态的参数初始化---");

    }

//非静态的参数初始化

    {

        System.out.println("----非静态的参数初始化---");

    }
    //测试反射

    public void  print(String name){
        System.out.println("测试反射的方法" + name);
    }

}


