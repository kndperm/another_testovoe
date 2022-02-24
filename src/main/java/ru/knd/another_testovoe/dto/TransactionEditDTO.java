package ru.knd.another_testovoe.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class TransactionEditDTO {
    private long transactionId;
    private long productId;
    private LocalDate date;
    private long quantity;
}
