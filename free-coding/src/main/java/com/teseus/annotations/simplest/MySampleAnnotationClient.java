package com.teseus.annotations.simplest;

import java.lang.reflect.Method;

public class MySampleAnnotationClient {
    public static void main(String args[]) {
        Method method = null;
        MySampleAnnotationClient mySampleAnnotationClient = null;
        MySampleAnnotation mySampleAnnotation = null;
        try {
            mySampleAnnotationClient = new MySampleAnnotationClient();
            method = mySampleAnnotationClient.getClass().getMethod("myMethod");
            mySampleAnnotation = method.getAnnotation(MySampleAnnotation.class);
            System.out.println(mySampleAnnotation.name());
            System.out.println(mySampleAnnotation.age());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @MySampleAnnotation(name="Sreenivasan Arumugam", age=29)
    public void myMethod() {
        System.out.println("I am the king with the MySampleAnnotation");
    }
}
