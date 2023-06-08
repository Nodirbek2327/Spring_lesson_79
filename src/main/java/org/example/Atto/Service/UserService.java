package org.example.Atto.Service;

import org.example.Atto.Dto.CardDto;
import org.example.Atto.Dto.ProfileDto;
import org.example.Atto.Enum.CardStatus;
import org.example.Atto.Repo.UserRepo;

import java.util.List;

public class UserService {
    private ProfileDto profileDto ;
    private UserRepo userRepo;

    public void addCard(int cardNum, String phone) {
        if (!userRepo.checkCardByNum(cardNum, phone).getCardStatus().equals(CardStatus.NOT_ACTIVE)) {
            System.out.println("This card is already registered  or blocked !");
            return;
        }
        if (userRepo.add_card(cardNum, phone)) {
            System.out.println("You have created card successfully");
        } else {
            System.out.println("something went wrong");
        }
    }

    public void getCardList(String phone) {
        List<CardDto> cardDtoList = userRepo.getCardList(phone);
        if (!cardDtoList.isEmpty()) {
            cardDtoList.forEach(System.out::println);
        } else {
            System.out.println("you don't have any cards");
        }
    }

    public void cardChangeStatus(int cardNum, String phone) {
        CardDto cardDto = userRepo.checkCardByNum(cardNum, phone);
        if (cardDto != null) {
            if (cardDto.getCardStatus().equals(CardStatus.ACTIVE)) {
                cardDto.setCardStatus(CardStatus.NOT_ACTIVE);
                System.out.println("Card status Changed to No Active");
            } else if (cardDto.getCardStatus().equals(CardStatus.NOT_ACTIVE)) {
                cardDto.setCardStatus(CardStatus.ACTIVE);
                System.out.println("Card status Changed to Active");
            }
            userRepo.update_card(cardDto);

        } else {
            System.out.println("Not found card with this number!");
        }
    }

    public void deleteCard(int cardNum, String phone) {
        CardDto cardDto = userRepo.checkCardByNum(cardNum, phone);
        if (cardDto != null) {
            System.out.println("Card was not found!");
            return;
        }
        // delete
        boolean result = userRepo.deleteCard(cardNum, phone);
        if (result) {
            System.out.println("Card was deleted successfully");
        } else {
            System.out.println("Something went wrong");
        }
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }


    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}
