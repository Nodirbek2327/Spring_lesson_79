package org.example.Atto.Service;


import org.example.Atto.Controller.AdminController;
import org.example.Atto.Controller.UserController;
import org.example.Atto.Dto.ProfileDto;
import org.example.Atto.Enum.ProfileRole;
import org.example.Atto.Enum.ProfileStatus;
import org.example.Atto.Repo.ProfileRepo;
import org.example.Atto.util.PhoneValidationUtil;

import java.time.LocalDateTime;

public class ProfileService {
    private ProfileRepo profileRepo;
    private UserController userController;
    private AdminController adminController;

    public void register(ProfileDto profileDto) {
        String phone = profileDto.getPhone();
        // validate phone
        if (!PhoneValidationUtil.isValidPhone(phone)) {
            System.out.println("This phone is invalid");
            return;
        }
        // check phone unique
        ProfileDto existProfile = profileRepo.getProfileByPhone(profileDto.getPhone());
        if (existProfile != null) {
            System.out.println("This phone is already registered");
            return;
        }
        // set detail
        profileDto.setCreated_date(LocalDateTime.now());
        profileDto.setRole(ProfileRole.USER);
        profileDto.setStatus(ProfileStatus.ACTIVE);
        boolean result = profileRepo.addProfile(profileDto);
        if (result) {
            System.out.println("you have successfully registered");
            System.out.println("Login to your account!");
        } else {
            System.out.println("Something went wrong");
        }
    }


    public void login(String login, String password) {
        ProfileDto profileDto = profileRepo.login(login, password);
        System.out.println(profileDto);
        if (profileDto == null) {
            System.out.println("login or password is incorrect!");
            return;
        } else if (profileDto.getStatus().equals(ProfileStatus.BLOCKED)) {
            System.out.println("your account is blocked!");
            return;
        }

        System.out.println("you have logged in successfully!");

        if (profileDto.getRole().equals(ProfileRole.USER)) {
            userController.setProfileDto(profileDto);
            userController.start();
            System.out.println("Welcome user");
        } else {
            adminController.setProfile(profileDto);
            adminController.start();
            System.out.println("Welcome admin");
        }

    }

    public void setProfileRepo(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }
}

