package org.example.Atto;

import org.example.Atto.Controller.MenuController;
import org.example.Atto.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        DBInit.createCompanyCard();
//        DBInit.createTableProfile();
//        DBInit.createTableCard();
       // DBInit.createAdmin();

      //  ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.atto.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        MenuController menuController = (MenuController) context.getBean("menuController");
//        UserController userController = (UserController) context.getBean("userController");
//        AdminController adminController = (AdminController) context.getBean("adminController");
//     //   DBInit dbInit = (DBInit) context.getBean("dbInit");
        menuController.start();
    }
}