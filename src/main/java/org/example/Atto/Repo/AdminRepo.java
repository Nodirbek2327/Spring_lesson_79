package org.example.Atto.Repo;


import org.example.Atto.Dto.CardDto;
import org.example.Atto.Dto.ProfileDto;
import org.example.Atto.Dto.Terminal;
import org.example.Atto.Dto.Transaction;
import org.example.Atto.Enum.*;
import org.example.Atto.util.DBConnection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Component
@Repository
public class AdminRepo {
    public CardDto checkCardByNum(int cardNum){
        Connection connection= DBConnection.getConnection();
        String sql="select * from card where card_number=?";
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setInt(1,cardNum);
            ResultSet rs=pr.executeQuery();
            if(rs.next()){
                CardDto cardDto= new CardDto();
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
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public boolean add_card(CardDto cardDto){
        Connection connection= DBConnection.getConnection();
        String sql="insert into card(card_number, crated_date, exp_date, balance, status) values(?,?,?,?,?)";
        try {
            PreparedStatement pr= connection.prepareStatement(sql);
            pr.setInt(1,cardDto.getCardNumber());
            pr.setTimestamp(2, Timestamp.valueOf(cardDto.getCreatedDate()));
            pr.setDate(3,Date.valueOf(cardDto.getExpDate()));
            pr.setDouble(4,cardDto.getBalance());
            pr.setString(5,cardDto.getCardStatus().toString());
            return  pr.executeUpdate()>0;
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

    public List<CardDto> getCardList() {
        Connection connection= DBConnection.getConnection();
        List<CardDto> cardDtoList=new LinkedList<>();
        String sql="select * from card ";
        try {
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                CardDto cardDto= new CardDto();
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
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return cardDtoList;
    }

    public void updateCard(CardDto cardDto) {
        Connection connection=DBConnection.getConnection();
        String sql="update card set exp_date=? where card_number=? ";
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
           pr.setDate(1,Date.valueOf(cardDto.getExpDate()));
            pr.setInt(2,cardDto.getCardNumber());
            pr.executeUpdate();
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

    public void update_card(CardDto cardDto) {
        Connection connection=DBConnection.getConnection();
        String sql="update card set status=? where card_number=? ";
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setString(1,cardDto.getCardStatus().toString());
            pr.setInt(2,cardDto.getCardNumber());
            pr.executeUpdate();
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

    public boolean deleteCard(int cardNum) {
        Connection connection=DBConnection.getConnection();
        String sql="delete from card where card_number=?";
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setInt(1,cardNum);
            return pr.executeUpdate()>0;
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

    public List<ProfileDto> getProfileList() {
        Connection connection= DBConnection.getConnection();
        List<ProfileDto> profileDtoList=new LinkedList<>();
        String sql="select * from profile";
        try {
            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                ProfileDto profileDto = new ProfileDto();
                profileDto.setId(rs.getInt("id"));
                profileDto.setName(rs.getString("name"));
                profileDto.setSurname(rs.getString("surname"));
                profileDto.setPhone(rs.getString("phone"));
                profileDto.setLogin(rs.getString("login"));
                profileDto.setPassword(rs.getString("password"));
                profileDto.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime());
                profileDto.setStatus(ProfileStatus.valueOf(rs.getString("status")));
                profileDto.setRole(ProfileRole.valueOf(rs.getString("role")));
                profileDtoList.add(profileDto);

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
        return profileDtoList;
    }

    public ProfileDto getProfile(String phone) {
        String sql="select * from profile where phone=?";
        Connection connection=DBConnection.getConnection();
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setString(1,phone);
            ResultSet rs=pr.executeQuery();
            if(rs.next()){
                ProfileDto profileDto = new ProfileDto();
                profileDto.setId(rs.getInt("id"));
                profileDto.setName(rs.getString("name"));
                profileDto.setSurname(rs.getString("surname"));
                profileDto.setPhone(rs.getString("phone"));
                profileDto.setLogin(rs.getString("login"));
                profileDto.setPassword(rs.getString("password"));
                profileDto.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime());
                profileDto.setStatus(ProfileStatus.valueOf(rs.getString("status")));
                profileDto.setRole(ProfileRole.valueOf(rs.getString("role")));
                return profileDto;
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
        return null;
    }

    public void updateProfile(ProfileDto profileDto) {
        Connection connection=DBConnection.getConnection();
        String sql="update profile set status=? where phone=?";
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setString(1,profileDto.getStatus().toString());
            pr.setString(2,profileDto.getPhone());
            pr.executeUpdate();
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

    public Terminal getTerminal(String code) {
        String sql="select * from terminal where code=? and status = ?";
        Connection connection=DBConnection.getConnection();
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setString(1,code);
            pr.setString(2, String.valueOf(TerminalStatus.ACTIVE));
            ResultSet rs=pr.executeQuery();
            if(rs.next()){
                Terminal terminal = new Terminal();
                terminal.setCode(Integer.valueOf(rs.getString("code")));
                terminal.setAddress(rs.getString("address"));
                terminal.setStatus(TerminalStatus.valueOf(rs.getString("status")));
                terminal.setCreated_date(LocalDateTime.parse(rs.getString("created_date")));
                return terminal;
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
        return null;
    }

    public void createTerminal(String code, String address) {
        Connection connection= DBConnection.getConnection();
        String sql="insert into terminal(code, address, status, created_date) values(?,?,?,?)";
        try {
            PreparedStatement pr= connection.prepareStatement(sql);
            pr.setString(1,code);
            pr.setString(2,address);
            pr.setString(3, String.valueOf(TerminalStatus.ACTIVE));
            pr.setString(3, String.valueOf(LocalDateTime.now()));
            if (pr.executeUpdate()>0){
                System.out.println("terminal created successfully");
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

    public List<Terminal> getTerminals() {
        Connection connection= DBConnection.getConnection();
        List<Terminal> terminalList=new LinkedList<>();
        String sql="select * from terminal";
        try {
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                Terminal terminal = new Terminal();
                terminal.setCode(Integer.valueOf(rs.getString("code")));
                terminal.setAddress(rs.getString("address"));
                terminal.setStatus(TerminalStatus.valueOf(rs.getString("status")));
                terminal.setCreated_date(LocalDateTime.parse(rs.getString("created_date")));
                terminalList.add(terminal);
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
        return terminalList;
    }

    public void updateTerminal(String code, String newAddress) {
        Connection connection=DBConnection.getConnection();
        String sql="update terminal set address=? where code=?";
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setString(1,newAddress);
            pr.setString(2,code);
            pr.executeUpdate();
            if (pr.executeUpdate()>0){
                System.out.println("updated successfully");
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

    public void changeterminalStatus(String code) {
        Connection connection=DBConnection.getConnection();
        String sql="update terminal set status=? where code=?";
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setString(1, String.valueOf(TerminalStatus.NOT_ACTIVE));
            pr.setString(2,code);
            pr.executeUpdate();
            if (pr.executeUpdate()>0){
                System.out.println("changed successfully");
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


    public void deleteTerminal(String code) {
        Connection connection=DBConnection.getConnection();
        String sql="update terminal set status=? where code=?";
        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setString(1, String.valueOf(TerminalStatus.NOT_ACTIVE));
            pr.setString(2,code);
            pr.executeUpdate();
            if (pr.executeUpdate()>0){
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

    public List<Transaction> getTransactionList() {
        Connection connection= DBConnection.getConnection();
        List<Transaction> transactions=new LinkedList<>();
        String sql="select * from transaction";
        try {
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                Transaction transaction = new Transaction();
                transaction.setAmount(rs.getLong("amount"));
                transaction.setId(rs.getInt("id"));
                transaction.setCard_number(rs.getLong("card_number"));
                transaction.setTerminal_code(rs.getString("terminal_code"));
                transaction.setCreated_date(LocalDateTime.parse(rs.getString("created_date")));
                transaction.setType(TransactionType.valueOf(rs.getString("type")));
                transactions.add(transaction);
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
        return transactions;
    }

    public List<Transaction> getTransactionListToday(LocalDate now) {
        Connection connection= DBConnection.getConnection();
        List<Transaction> transactions=new LinkedList<>();
        String sql="select * from transaction where created_date like  ?";

        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setString(1, now+"%");
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()){
                Transaction transaction = new Transaction();
                transaction.setAmount(resultSet.getLong("amount"));
                transaction.setId(resultSet.getInt("id"));
                transaction.setCard_number(resultSet.getLong("card_number"));
                transaction.setTerminal_code(resultSet.getString("terminal_code"));
                transaction.setCreated_date(LocalDateTime.parse(resultSet.getString("created_date")));
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transactions.add(transaction);
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
        return transactions;
    }

    public List<Transaction> getTransactionListOraliq(String fromDate, String toDate) {
        Connection connection= DBConnection.getConnection();
        List<Transaction> transactions=new LinkedList<>();
        String sql="select * from transaction where created_date BETWEEN LIKE ? AND LIKE ? ";

        try {
            PreparedStatement pr=connection.prepareStatement(sql);
            pr.setString(1, fromDate+"%");
            pr.setString(2, toDate+"%");
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()){
                Transaction transaction = new Transaction();
                transaction.setAmount(resultSet.getLong("amount"));
                transaction.setId(resultSet.getInt("id"));
                transaction.setCard_number(resultSet.getLong("card_number"));
                transaction.setTerminal_code(resultSet.getString("terminal_code"));
                transaction.setCreated_date(LocalDateTime.parse(resultSet.getString("created_date")));
                transaction.setType(TransactionType.valueOf(resultSet.getString("type")));
                transactions.add(transaction);
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
        return transactions;
    }
}


