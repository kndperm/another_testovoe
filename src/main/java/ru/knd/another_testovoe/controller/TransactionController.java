package ru.knd.another_testovoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.knd.another_testovoe.dto.ProductQuantity;
import ru.knd.another_testovoe.dto.StatusResponse;
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
    public StatusResponse addArrivalTransaction(@RequestBody TransactionDTO newTransactionDTO) {
        Transaction transaction = service.addTransaction(newTransactionDTO, "Поступление");
        if (transaction != null)
            return new StatusResponse("OK", "Транзакция добавлена", transaction);
        else
            return new StatusResponse("ERROR", "Нет предложенного продукта", transaction);
    }

    @PostMapping("/add_allowance")
    public StatusResponse addAllowanceTransaction(@RequestBody TransactionDTO newTransactionDTO) {
        Transaction transaction = service.addTransaction(newTransactionDTO, "Списание");
        if (transaction != null)
            return new StatusResponse("OK", "Транзакция добавлена", transaction);
        else
            return new StatusResponse("ERROR", "Нет предложенного продукта или количество продуктов отрицательна", transaction);
    }

    @GetMapping("/get/{id}")
    public Transaction getTransaction(@PathVariable long id) {
        return service.getTransaction(id);
    }

    @PutMapping("/edit")
    public StatusResponse editTransaction(@RequestBody TransactionEditDTO transactionEditDTO) {
        Transaction transaction = service.editTransaction(transactionEditDTO);
        return new StatusResponse("OK", "Транзакция изменена", transaction);
    }

    @DeleteMapping("/delete/{id}")
    public StatusResponse deleteTransaction(@PathVariable long id) {
        service.deleteTransaction(id);
        return new StatusResponse("OK", "Транзакция удалена", null);
    }

    @GetMapping("/product/{id}")
    public List<Transaction> getProductTransaction(@PathVariable long id) {
        return service.getProductTransactions(id);
    }

    @GetMapping("/current_product_quantity")
    public List<ProductQuantity> getProductCurrentQuantity() {
        return service.getProductCurrentQuantity();
    }
}
