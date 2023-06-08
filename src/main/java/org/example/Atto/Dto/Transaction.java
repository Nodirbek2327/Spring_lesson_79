package org.example.Atto.Dto;

import org.example.Atto.Enum.TransactionType;

import java.time.LocalDateTime;

public class Transaction {
    private Integer id;
    private long card_number;
    private long amount;
    private String terminal_code;
    private TransactionType type;
    private LocalDateTime created_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getCard_number() {
        return card_number;
    }

    public void setCard_number(long card_number) {
        this.card_number = card_number;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getTerminal_code() {
        return terminal_code;
    }

    public void setTerminal_code(String terminal_code) {
        this.terminal_code = terminal_code;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", card_number=" + card_number +
                ", amount=" + amount +
                ", terminal_code='" + terminal_code + '\'' +
                ", type=" + type +
                ", created_date=" + created_date +
                '}';
    }
}
