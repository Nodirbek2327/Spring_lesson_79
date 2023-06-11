package org.example.Atto.Repo;



import org.example.Atto.Dto.CardDto;
import org.example.Atto.Dto.Terminal;
import org.example.Atto.Dto.Transaction;
import org.example.Atto.Enum.CardStatus;
import org.example.Atto.Enum.TerminalStatus;
import org.example.Atto.Enum.TransactionType;
import org.example.Atto.util.DBConnection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Component
@Repository
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


    public void refill(CardDto cardDto, long amount) {
        Connection connection = DBConnection.getConnection();
        String sql = "update card set balance=? where card_number=? ";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, String.valueOf(cardDto.getBalance()+amount));
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

    public List<Transaction> getTransactions() {
        Connection connection = DBConnection.getConnection();
        List<Transaction> transactionList = new LinkedList<>();
        String sql = "select * from transaction";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setType(TransactionType.valueOf(rs.getString("type")));
                transaction.setTerminal_code(rs.getString("terminal_code"));
                transaction.setCard_number(rs.getLong("card_number"));
                transaction.setAmount(rs.getLong("amount"));
                transaction.setCreated_date(LocalDateTime.parse(rs.getString("created_date")));
                transaction.setId(rs.getInt("id"));
                transactionList.add(transaction);
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
        return transactionList;
    }

    public Terminal getTerminal(int code) {
        Connection connection = DBConnection.getConnection();
        String sql = "select * from terminal where code=? ";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, code);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Terminal terminal = new Terminal();
                terminal.setStatus(TerminalStatus.valueOf(rs.getString("status")));
                terminal.setCode(Integer.valueOf(String.valueOf(code)));
                terminal.setCreated_date(LocalDateTime.parse(rs.getString("created_date")));
                terminal.setAddress(rs.getString("address"));
                return terminal;
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

    public void payment(CardDto cardDto, Terminal terminal, long amount) {
        Connection connection= DBConnection.getConnection();
        String sql="insert into transaction(card_number, amount, terminal_code, type, created_date) values(?,?,?,?,?)";
        try {
            PreparedStatement pr= connection.prepareStatement(sql);
            pr.setInt(1,cardDto.getCardNumber());
            pr.setLong(2,amount);
            pr.setInt(3,terminal.getCode());
            pr.setString(4, String.valueOf(TransactionType.PAYMENT));
            pr.setString(5, String.valueOf(LocalDateTime.now()));
            if (pr.executeUpdate()>0) {
                System.out.println("success");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update_card(CardDto cardDto, long amount) {
        Connection connection = DBConnection.getConnection();
        String sql = "update card set balance=? where card_number=? ";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setLong(1, (long) (cardDto.getBalance()+amount));
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
}
