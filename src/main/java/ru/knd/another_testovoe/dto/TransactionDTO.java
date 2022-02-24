package ru.knd.another_testovoe.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionDTO {
    private long productId;
    private LocalDate date;
    private long quantity;
}
