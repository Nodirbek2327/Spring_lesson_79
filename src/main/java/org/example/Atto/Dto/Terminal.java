package org.example.Atto.Dto;

import org.example.Atto.Enum.TerminalStatus;

import java.time.LocalDateTime;

public class Terminal {
    private String code;
    private String address;
    private TerminalStatus status;
    private LocalDateTime created_date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TerminalStatus getStatus() {
        return status;
    }

    public void setStatus(TerminalStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "code='" + code + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", created_date=" + created_date +
                '}';
    }
}
