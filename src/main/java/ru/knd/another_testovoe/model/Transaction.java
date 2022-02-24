package ru.knd.another_testovoe.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "product_transactions", schema = "test")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String operationType;
    @Column(name = "transaction_date")
    private LocalDate date;
    private long quantity;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
