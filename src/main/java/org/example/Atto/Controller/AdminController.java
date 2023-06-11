package org.example.Atto.Controller;


import org.example.Atto.Container.ComponentContainer;
import org.example.Atto.Dto.ProfileDto;
import org.example.Atto.Service.AdminService;
import org.example.Atto.util.GetAction;
import org.example.Atto.Enum.ProfileStatus;
import org.example.Atto.Enum.CardStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Component
@Controller
public class AdminController {
    @Autowired
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
                case 6->createTerminal();
                case 7->terminalList();
                case 8->updateTerminal();
                case 9->changeTerminalStatus();
                case 10->deleteTerminal();
                case 11->getProfileList();
                case 12->changeProfileStatus();
                case 13->transactionList();
                case 14->companyCardBalance();
                case 15->bugungiTulovlar();
                case 16->kunlik();
                case 17->oraliq();
                case 18->umumiyBalance();
                case 19->transactionByTerminal();
                case 20->transactionByCard();
            }
        }
    }

    private void transactionByCard() {

    }

    private void transactionByTerminal() {

    }

    private void umumiyBalance() {
        adminService.companyCardBalance(profileDto.getPhone());
    }

    private void oraliq() {
        System.out.println("Enter fromDate (yyyy-MM-dd):");
        String fromDate= ComponentContainer.stringScanner.next();
        System.out.println("Enter toDate (yyyy-MM-dd):");
        String toDate= ComponentContainer.stringScanner.next();
        adminService.oraliq(fromDate, toDate);
    }

    private void kunlik() {
        System.out.println("Enter date (yyyy-MM-dd):");
        String date= ComponentContainer.stringScanner.next();
        adminService.bugun(LocalDate.parse(date));
    }

    private void bugungiTulovlar() {
        adminService.bugun(LocalDate.now());
    }

    private void companyCardBalance() {
        adminService.companyCardBalance(profileDto.getPhone());
    }

    private void transactionList() {
        adminService.transactionList();
    }

    private void deleteTerminal() {
        System.out.println("Enter terminal code:");
        String code= ComponentContainer.stringScanner.next();
        adminService.deleteTerminal(code);
    }

    private void changeTerminalStatus() {
        System.out.println("Enter terminal code:");
        String code= ComponentContainer.stringScanner.next();
        adminService.changeTerminalStatus(code);
    }

    private void updateTerminal() {
        System.out.println("Enter terminal code:");
        String code= ComponentContainer.stringScanner.next();
        System.out.println("Enter new address that you want :");
        String newAddress= ComponentContainer.stringScanner.next();
        adminService.updateTerminal(code, newAddress);
    }

    private void terminalList() {
        adminService.getTerminals();
    }

    private void createTerminal() {
        System.out.println("Enter terminal code:");
        String code= ComponentContainer.stringScanner.next();
        System.out.println("Enter terminal address:");
        String address= ComponentContainer.stringScanner.next();
        adminService.createTerminal(code, address);
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
                "    10. Delete Terminal\n" +
                "\n" +
                "    (Profile)\n" +
                "    11. Profile List\n" +
                "    12. Change Profile Status (by phone)\n" +
                "\n" +
                "    (Transaction)\n" +
                "    13. Transaction List\n" +
                "    14. Company Card Balance\n" +
                "\n" +
                "       (Statistic)\n" +
                "    15. Bugungi to'lovlar\n" +
                "    16. Kunlik to'lovlar (bir kunlik to'lovlar):\n" +
                "    17. Oraliq to'lovlar:\n" +
                "    18. Umumiy balance (company card dagi pulchalar)\n" +
                "    19. Transaction by Terminal:\n" +
                "    20. Transaction By Card:\n" +
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
