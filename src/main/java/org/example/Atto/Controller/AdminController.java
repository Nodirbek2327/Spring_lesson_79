package org.example.Atto.Controller;


import org.example.Atto.Container.ComponentContainer;
import org.example.Atto.Dto.ProfileDto;
import org.example.Atto.Service.AdminService;
import org.example.Atto.util.GetAction;
import org.example.Atto.Enum.ProfileStatus;
import org.example.Atto.Enum.CardStatus;

public class AdminController {

    private AdminService adminService;

    private ProfileDto profileDto;

    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (GetAction.getAction()){
                case 0->t=false;
                case 1->createCard();
                case 2->getCardList();
                case 3->updateCard();
                case 4->changeCardStatus();
                case 5->deleteCard();
                case 11->getProfileList();
                case 12->changeProfileStatus();
            }
        }
    }




    public  void show(){
        System.out.println("*** Admin Menu ***\n" +
                "    (Card)\n" +
                "    1. Create Card(number,exp_date)\n" +
                "    2. Card List\n" +
                "    3. Update Card (number,exp_date)\n" +
                "    4. Change Card status\n" +
                "    5. Delete Card\n" +
                "\n" +
                "    (Terminal)\n" +
                "    6. Create Terminal (code unique,address)\n" +
                "    7. Terminal List\n" +
                "    8. Update Terminal (code,address)\n" +
                "    9. Change Terminal Status\n" +
                "    10. Delete\n" +
                "\n" +
                "    (Profile)\n" +
                "    11. Profile List\n" +
                "    12. Change Profile Status (by phone)\n" +
                "\n" +
                "    (Transaction)\n" +
                "    13. Transaction List\n" +
                "        CardNumber, TerminalNumber, Amount,TransactionDate,Type (oxirgi birinchi ko'rinadi)\n" +
                "    14. Company Card Balance\n" +
                "        (card bo'ladi shu cardga to'lovlar tushadi. bu sql da insert qilinga bo'ladi)\n" +
                "\n" +
                "       (Statistic)\n" +
                "    15. Bugungi to'lovlar\n" +
                "         CardNumber, TerminalNumber, Amount,TransactionDate,Type (oxirgi birinchi ko'rinadi)\n" +
                "    16. Kunlik to'lovlar (bir kunlik to'lovlar):\n" +
                "        Enter Date: yyyy-MM-dd\n" +
                "          CardNumber, TerminalNumber, Amount,TransactionDate,Type (oxirgi birinchi ko'rinadi)\n" +
                "    17. Oraliq to'lovlar:\n" +
                "        Enter FromDate: yyyy-MM-dd\n" +
                "        Enter ToDate:   yyyy-MM-dd\n" +
                "    18. Umumiy balance (company card dagi pulchalar)\n" +
                "    19. Transaction by Terminal:\n" +
                "        Enter terminal number:\n" +
                "    20. Transaction By Card:\n" +
                "        Enter Card number:" +
                "     0. Exit"      );
    }

    public void createCard(){
        System.out.println("Enter card number:");
        int cardNum= ComponentContainer.intScanner.nextInt();
        System.out.println("Enter expire date:");
        System.out.println("Enter year:");
        int year= ComponentContainer.intScanner.nextInt();
        System.out.println("Enter month by number: ");
        int month= ComponentContainer.intScanner.nextInt();
        adminService.createCard(cardNum,year,month);
    }
    public void getCardList(){
        adminService.getAllCardList();
    }
    public void updateCard(){
        System.out.println("Enter card number:");
        int cardNum= ComponentContainer.intScanner.nextInt();
        System.out.println("Enter expire date:");
        System.out.println("Enter year:");
        int year= ComponentContainer.intScanner.nextInt();
        System.out.println("Enter month by number: ");
        int month= ComponentContainer.intScanner.nextInt();
       adminService.updateCard(cardNum,year,month);
    }
    private void changeCardStatus() {
        System.out.println("Enter card number:");
        int cardNum= ComponentContainer.intScanner.nextInt();
        System.out.println("Enter card status number:");
        System.out.println("1=ACTIVE, 2=NOT_ACTIVE, 3=BLOCKED, 4=EXPIRED");
        int statusNum=ComponentContainer.intScanner.nextInt();
        String status="";
        switch (statusNum){
            case 1-> status= CardStatus.ACTIVE.toString();
            case 2-> status=CardStatus.NOT_ACTIVE.toString();
            case 3-> status=CardStatus.BLOCKED.toString();
            case 4-> status=CardStatus.EXPIRED.name();
        }

        adminService.changeStatus(cardNum,status);
    }
    private void deleteCard() {
        System.out.println("Enter card number:");
        int cardNum= ComponentContainer.intScanner.nextInt();
        adminService.deleteCard(cardNum);
    }
    private void getProfileList() {
        adminService.getAllProfile();
    }

    private void changeProfileStatus() {
        System.out.println("Enter profile phone:");
        String phone=ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter status number: ");
        System.out.println("1=ACTIVE, 2=NOT_ACTIVE ,3=BLOCKED");
        int statusNum=ComponentContainer.intScanner.nextInt();
        String status="";
        switch (statusNum){
            case 1-> status=ProfileStatus.ACTIVE.toString();
            case 2-> status=ProfileStatus.NOT_ACTIVE.toString();
            case 3-> status=ProfileStatus.BLOCKED.toString();
        }
        adminService.changeProfileStatus(phone, status);

    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public void setProfile(ProfileDto profileDto) {
        this.profileDto=profileDto;
    }
}
