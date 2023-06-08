package org.example.Atto.Repo;



import org.example.Atto.Dto.CardDto;
import org.example.Atto.Enum.CardStatus;
import org.example.Atto.util.DBConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class UserRepo {
    public CardDto checkCardByNum(int cardNum, String phone) {
        Connection connection = DBConnection.getConnection();
        String sql = "select * from card where card_number=? and phone = ? and status = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, cardNum);
            pr.setString(2, phone);
            pr.setString(3, String.valueOf(CardStatus.ACTIVE));
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                CardDto cardDto = new CardDto();
                cardDto.setCardNumber(rs.getInt("card_number"));
                cardDto.setBalance(rs.getDouble("balance"));
                cardDto.setCreatedDate(rs.getTimestamp("crated_date").toLocalDateTime());
                cardDto.setExpDate(rs.getDate("exp_date").toLocalDate());
                cardDto.setCardStatus(CardStatus.valueOf(rs.getString("status")));
                cardDto.setUserPhone(rs.getString("phone"));
                return cardDto;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public boolean add_card(int cardNumber, String phone) {
        Connection connection = DBConnection.getConnection();
        String sql="update card set status=? , phone = ? where card_number=? ";
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setString(1, String.valueOf(CardStatus.ACTIVE));
            pr.setString(2, phone);
            pr.setInt(3, cardNumber);
            int r = pr.executeUpdate();
            if (r>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public List<CardDto> getCardList(String phone) {
        Connection connection = DBConnection.getConnection();
        List<CardDto> cardDtoList = new LinkedList<>();
        String sql = "select * from card where phone=? and status = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, phone);
            pr.setString(2, String.valueOf(CardStatus.ACTIVE));
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                CardDto cardDto = new CardDto();
                cardDto.setCardNumber(rs.getInt("card_number"));
                cardDto.setBalance(rs.getDouble("balance"));
                cardDto.setCreatedDate(rs.getTimestamp("crated_date").toLocalDateTime());
                cardDto.setExpDate(rs.getDate("exp_date").toLocalDate());
                cardDto.setCardStatus(CardStatus.valueOf(rs.getString("status")));
                cardDto.setUserPhone(rs.getString("phone"));
                cardDtoList.add(cardDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return cardDtoList;
    }

    public void update_card(CardDto cardDto) {
        Connection connection = DBConnection.getConnection();
        String sql = "update card set status=? where card_number=? ";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, cardDto.getCardStatus().toString());
            pr.setInt(2, cardDto.getCardNumber());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean deleteCard(int cardNum, String phone) {
        Connection connection = DBConnection.getConnection();
        String sql = "update card set status=? where card_number=? and phone = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, cardNum);
            pr.setString(2, phone);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
