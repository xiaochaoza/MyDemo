package com.fzzz.design_mode.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-19
 * update:
 */
public class MyReflection{
    public static void main(String[] args) {
        try {
            //获得类文件
            Class<MyAnnoTest> myAnnoTestClass = MyAnnoTest.class;
            //通过类查找方法，output是要调用的方法名字，new Class[]{}为所需要的参数。空则不是这种
            Method method = myAnnoTestClass.getMethod("output", new Class[]{});
            //是否有类型为MyAnnotation类型的注解
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                //获得注解
                MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
                //调用注解内容
                System.out.println(myAnnotation.hello());
                System.out.println(myAnnotation.world());
            }
            System.out.println("---------------------");
            //获得所有注解，必须是runtime类型的
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //打印注解名字
                System.out.println(annotation.annotationType().getName());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
