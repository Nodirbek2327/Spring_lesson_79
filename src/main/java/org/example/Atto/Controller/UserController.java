package org.example.Atto.Controller;


import org.example.Atto.Container.ComponentContainer;
import org.example.Atto.Dto.ProfileDto;
import org.example.Atto.Enum.CardStatus;
import org.example.Atto.Service.UserService;
import org.example.Atto.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
@Component
public class UserController {
    private ProfileDto profileDto;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminController adminController;

    public void start() {
        boolean t = true;
        while (t) {
            show();
            switch (GetAction.getAction()) {
                case 1 -> addCard();
                case 2 -> cardList();
                case 3 -> cardChangeStatus();
                case 4 -> deleteCard();
                case 5 -> refill();
                case 6 -> transactions();
                case 7 -> payment();
                case 0 -> t = false;
            }
        }
    }

    private void payment() {
        System.out.println("Enter card number:");
        int cardNum = ComponentContainer.intScanner.nextInt();
        System.out.println("Enter terminal code:");
        int code = ComponentContainer.intScanner.nextInt();
        System.out.println("Enter money amount:");
        long amount = ComponentContainer.longScanner.nextLong();
        userService.payment(cardNum, code, amount, profileDto.getPhone());
    }

    private void transactions() {
        userService.getTransactions();
    }

    private void refill() {
        System.out.println("Enter card number:");
        int cardNum = ComponentContainer.intScanner.nextInt();
        System.out.println("Enter amount:");
        long amount = ComponentContainer.longScanner.nextLong();
        userService.refill(cardNum, amount, profileDto.getPhone());
    }


    public void show() {
        System.out.println("*** User  Menu **\n" +
                "    1. Add Card \n" +
                "    2. Card List \n" +
                "    3. Card Change Status\n" +
                "    4. Delete Card (visible_user = false,deleted_user)\n" +
                "    5. ReFill \n" +
                "    6. Transaction\n" +
                "    7. Make Payment" +
                "    0. Exit");
    }

    public void addCard() {
        adminController.getCardList();
        System.out.println("Which card do you want?");
        System.out.println("Enter card number:");
        int cardNum = ComponentContainer.intScanner.nextInt();

        userService.addCard(cardNum, profileDto.getPhone());
    }

    public void cardList() {
        userService.getCardList(profileDto.getPhone());
    }

    public void cardChangeStatus() {
        System.out.println("Enter card number to change status:");
        int cardNum = ComponentContainer.intScanner.nextInt();
        userService.cardChangeStatus(cardNum, profileDto.getPhone());
    }

    private void deleteCard() {
        System.out.println("Enter card number to delete: ");
        int cardNum = ComponentContainer.intScanner.nextInt();
        userService.deleteCard(cardNum, profileDto.getPhone());
    }

    public void setProfileDto(ProfileDto profileDto) {
        this.profileDto = profileDto;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }
}
