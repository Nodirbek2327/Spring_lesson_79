package org.example.Atto.Container;


import org.example.Atto.Repo.AdminRepo;
import org.example.Atto.Repo.ProfileRepo;

import java.util.Random;
import java.util.Scanner;

public class ComponentContainer {
    public static Scanner stringScanner = new Scanner(System.in);
    public static Scanner intScanner = new Scanner(System.in);
    public static Scanner longScanner = new Scanner(System.in);
//    public static ProfileService profileService = new ProfileService();
    public static ProfileRepo profileRepo = new ProfileRepo();
//    public static UserController userController = new UserController();
//    public static UserService userService = new UserService();
//    public static UserRepo userRepo = new UserRepo();
//    public static ProfileDto profileDto = null;
//    public static AdminController adminController = new AdminController();
//    public static AdminService adminService = new AdminService();
   public static AdminRepo adminRepo = new AdminRepo();
    //    public static SmsService smsService = new SmsService();
//    public static EskizSmsService eskizSmsService = new EskizSmsService();
    //public static SmsService smsService = new EskizSmsService();
//    public static SmsService smsService = new SmsServiceImp();
}
