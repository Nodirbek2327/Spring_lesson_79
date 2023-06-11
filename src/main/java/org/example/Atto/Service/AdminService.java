package org.example.Atto.Service;



import org.example.Atto.Dto.CardDto;
import org.example.Atto.Dto.ProfileDto;
import org.example.Atto.Dto.Terminal;
import org.example.Atto.Dto.Transaction;
import org.example.Atto.Enum.CardStatus;
import org.example.Atto.Enum.ProfileStatus;
import org.example.Atto.Repo.AdminRepo;
import org.example.Atto.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private UserRepo userRepo;
    public void createCard(int cardNum, int year ,int month){
        if(adminRepo.checkCardByNum(cardNum)!=null){
            System.out.println("This card is already registered by other user!");
        }else {
            CardDto cardDto= new CardDto();
            cardDto.setCardStatus(CardStatus.NOT_ACTIVE);
            cardDto.setCardNumber(cardNum);
            cardDto.setCreatedDate(LocalDateTime.now());
            cardDto.setExpDate(LocalDate.of(year,month,1));
            cardDto.setBalance(0.0);
            if (adminRepo.add_card(cardDto)) {
                System.out.println("You have created card successfully");
            }else {
                System.out.println("something went wrong");
            }

        }
    }

    public void getAllCardList() {
        List<CardDto> cardDtoList=adminRepo.getCardList();
        if(cardDtoList!=null){
            cardDtoList.forEach(System.out::println);
        }else {
            System.out.println("there is no card");
        }
    }

    public void updateCard(int cardNum, int year, int month) {
        CardDto cardDto=adminRepo.checkCardByNum(cardNum);
        if (cardDto!=null){
            cardDto.setExpDate(LocalDate.of(year,month,1));
            adminRepo.updateCard(cardDto);
            System.out.println("Card was updated Successfully");
        }else {
            System.out.println("Not found card with this number!");
        }
    }

    public void changeStatus(int cardNum, String status) {
        CardDto cardDto=adminRepo.checkCardByNum(cardNum);
        if (cardDto!=null){
            cardDto.setCardStatus(CardStatus.valueOf(status));
            adminRepo.update_card(cardDto);
            System.out.println("Card status was changed to: "+status);

        }else {
            System.out.println("Not found card with this number!");
        }
    }

    public void deleteCard(int cardNum) {
        CardDto cardDto=adminRepo.checkCardByNum(cardNum);
        if(cardDto!=null){
            boolean result=adminRepo.deleteCard(cardNum);
            if(result){
                System.out.println("Card was deleted successfully");
            }else {
                System.out.println("Something went wrong");
            }
        }else {
            System.out.println("Card was not found!");
        }
    }

    public void getAllProfile() {
        List<ProfileDto> profileDtoList=adminRepo.getProfileList();
        if(profileDtoList !=null){
            profileDtoList.forEach(System.out::println);
        }else {
            System.out.println("Profile was not found");
        }
    }

    public void changeProfileStatus(String phone, String status) {
        ProfileDto profileDto=adminRepo.getProfile(phone);
        if(profileDto!=null){
            profileDto.setStatus(ProfileStatus.valueOf(status));
            System.out.println("Profile status was changed to: "+status);
            adminRepo.updateProfile(profileDto);
        }else {
            System.out.println("This profile was not found");
        }
    }

    public void setAdminRepo(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public void createTerminal(String code, String address) {
        if (code.length()!=8){
            System.out.println("code 8 raqamdan iborat bo'lishi kerak");
            return;
        }
        Terminal terminal = adminRepo.getTerminal(code);
        if (terminal==null){
            System.out.println("this terminal already exist");
        }else {
            adminRepo.createTerminal(code, address);
        }

    }

    public void getTerminals() {
        List<Terminal> terminalList = adminRepo.getTerminals();
        if (terminalList.isEmpty()){
            System.out.println("there is no terminal");
        }else {
          terminalList.forEach(System.out::println);
        }
    }

    public void updateTerminal(String code, String newAddress) {
        Terminal terminal = adminRepo.getTerminal(code);
        if (terminal==null){
            System.out.println("terminal not found");
            return;
        }
        adminRepo.updateTerminal(code, newAddress);
    }

    public void changeTerminalStatus(String code) {
        Terminal terminal = adminRepo.getTerminal(code);
        if (terminal==null){
            System.out.println("terminal not found");
            return;
        }
        adminRepo.changeterminalStatus(code);
    }

    public void deleteTerminal(String code) {
        Terminal terminal = adminRepo.getTerminal(code);
        if (terminal==null){
            System.out.println("terminal not found");
            return;
        }
        adminRepo.deleteTerminal(code);
    }

    public void transactionList() {
        List<Transaction> transactions = adminRepo.getTransactionList();
        if (transactions.isEmpty()){
            System.out.println("there is no terminal");
        }else {
            transactions.forEach(System.out::println);
        }
    }

    public void companyCardBalance(String phone) {
        CardDto cardDto = userRepo.checkCardByNum(7777777, phone);
        System.out.println("Balance:  "+cardDto.getBalance());
    }

    public AdminRepo getAdminRepo() {
        return adminRepo;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void bugun(LocalDate now) {
        List<Transaction> transactions = adminRepo.getTransactionListToday(now);
        if (transactions.isEmpty()){
            System.out.println("there is no terminal");
        }else {
            transactions.forEach(System.out::println);
        }
    }

    public void oraliq(String fromDate, String toDate) {
        List<Transaction> transactions = adminRepo.getTransactionListOraliq(fromDate, toDate);
        if (transactions.isEmpty()){
            System.out.println("there is no terminal");
        }else {
            transactions.forEach(System.out::println);
        }
    }
}

