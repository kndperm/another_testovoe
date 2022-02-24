package ru.knd.another_testovoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.knd.another_testovoe.dto.ProductQuantity;
import ru.knd.another_testovoe.dto.TransactionDTO;
import ru.knd.another_testovoe.dto.TransactionEditDTO;
import ru.knd.another_testovoe.model.Transaction;
import ru.knd.another_testovoe.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/add_arrival")
    public Transaction addArrivalTtransaction(@RequestBody TransactionDTO newTransactionDTO){
        return service.addTransaction(newTransactionDTO, "Поступление");
    }

    @PostMapping("/add_allowance")
    public Transaction addAllowanceTransaction(@RequestBody TransactionDTO newTransactionDTO){
        if(service.isProductQuantityNotNegative(newTransactionDTO))
        return service.addTransaction(newTransactionDTO, "Списание");
        else return null;
    }

    @GetMapping("/get/{id}")
    public Transaction getTransaction(@PathVariable long id){
        return service.getTransaction(id);
    }

    @PutMapping("/edit")
    public Transaction editTransaction(@RequestBody TransactionEditDTO transactionEditDTO){
        return service.editTransaction(transactionEditDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTransaction(@PathVariable long id){
        service.deleteTransaction(id);
    }

    @GetMapping("/product/{id}")
    public List<Transaction> getProductTransaction(@PathVariable long id){
        return service.getProductTransactions(id);
    }

    @GetMapping("/current_product_quantity")
    public List<ProductQuantity> getProductCurrentQuantity(){
        return service.getProductCurrentQuantity();
    }
}
