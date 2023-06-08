package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Professor professor =(Professor) context.getBean("professor");
        Professor professor1 =(Professor) context.getBean("professor1");
        Professor professor2 =(Professor) context.getBean("professor2");
        Lesson lesson1 =(Lesson) context.getBean("lesson1");
        Lesson lesson2 =(Lesson) context.getBean("lesson2");
        Lesson lesson =(Lesson) context.getBean("lesson");
//        System.out.println(professor1);
//        System.out.println(professor2);
//        System.out.println(lesson1);
//        System.out.println(lesson2);
        System.out.println(lesson);


    }
}