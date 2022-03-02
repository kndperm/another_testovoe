package ru.knd.another_testovoe.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class TransactionEditDTO {
    private Long transactionId;
    private Long productId;
    private LocalDate date;
    private Long quantity;
}
