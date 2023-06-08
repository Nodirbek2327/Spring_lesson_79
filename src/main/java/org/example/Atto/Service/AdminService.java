package org.example.Atto.Service;



import org.example.Atto.Dto.CardDto;
import org.example.Atto.Dto.ProfileDto;
import org.example.Atto.Enum.CardStatus;
import org.example.Atto.Enum.ProfileStatus;
import org.example.Atto.Repo.AdminRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class AdminService {
    private AdminRepo adminRepo;
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
}

