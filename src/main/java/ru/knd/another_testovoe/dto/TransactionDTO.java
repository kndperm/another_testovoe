package ru.knd.another_testovoe.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionDTO {
    private Long productId;
    private LocalDate date;
    private Long quantity;
}
