package org.example.Atto;

import org.example.Atto.Controller.AdminController;
import org.example.Atto.Controller.MenuController;
import org.example.Atto.Controller.UserController;
import org.example.Atto.DBInit.DBInit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        DBInit.createCompanyCard();
        DBInit.createTableProfile();
        DBInit.createTableCard();
       // DBInit.createAdmin();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.atto.xml");
        MenuController menuController = (MenuController) context.getBean("menuController");
        UserController userController = (UserController) context.getBean("userController");
        AdminController adminController = (AdminController) context.getBean("adminController");
     //   DBInit dbInit = (DBInit) context.getBean("dbInit");
        menuController.start();
    }
}