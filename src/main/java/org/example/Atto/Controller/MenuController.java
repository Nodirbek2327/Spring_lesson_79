package org.example.Atto.Controller;

import org.example.Atto.Container.ComponentContainer;
import org.example.Atto.Dto.ProfileDto;
import org.example.Atto.Enum.CardStatus;
import org.example.Atto.Service.ProfileService;
import org.example.Atto.util.GetAction;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
public class MenuController /*implements InitializingBean*/ {
    @Autowired
    private ProfileService profileService;

    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (GetAction.getAction()){
                case 1->login();
                case 2->registration();
                case 0 ->t=false;
            }
        }
    }



    public void show(){
        System.out.println("**MENU**");
        System.out.println("1. Login.");
        System.out.println("2. Registration.");
        System.out.println("0. Exit.");

    }
    public void registration(){
        System.out.println("Enter your name: ");
        String name= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your surname:");
        String surname= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your phone:");
        String phone= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your login:");
        String login= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your password:");
        String password= ComponentContainer.stringScanner.nextLine();

        ProfileDto profileDto= new ProfileDto();
        profileDto.setName(name);
        profileDto.setSurname(surname);
        profileDto.setPhone(phone);
        profileDto.setLogin(login);
        profileDto.setPassword(password);

        profileService.register(profileDto);
    }
    public void login(){
        System.out.println("Enter your login: ");
        String login=ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your password");
        String password=ComponentContainer.stringScanner.nextLine();
        profileService.login(login, password);
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("afterPropertiesSet");
//    }
}
