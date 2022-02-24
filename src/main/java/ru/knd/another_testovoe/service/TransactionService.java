package ru.knd.another_testovoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knd.another_testovoe.dto.ProductQuantity;
import ru.knd.another_testovoe.dto.TransactionDTO;
import ru.knd.another_testovoe.dto.TransactionEditDTO;
import ru.knd.another_testovoe.model.Product;
import ru.knd.another_testovoe.model.Transaction;
import ru.knd.another_testovoe.repository.ProductRepository;
import ru.knd.another_testovoe.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;

    public boolean isProductQuantityNotNegative(TransactionDTO transactionDTO){
        Product product = productRepository.getById(transactionDTO.getProductId());
        long arrivalQuantity = transactionRepository.getProductArrivalQuantity(product).getQuantity();
        long allowanceQuantity = transactionRepository.getProductAllowanceQuantity(product).getQuantity();
        return transactionDTO.getQuantity()>(arrivalQuantity-allowanceQuantity);
    }

    public Transaction addTransaction(TransactionDTO transactionDTO, String transactionType){
        if (isProductExist(transactionDTO.getProductId()))
        return saveTransaction(transactionDTO, transactionType);
        else return null;
    }

    public Transaction saveTransaction(TransactionDTO newTransactionDTO, String transactionType){
        Transaction transaction = new Transaction();
        transaction.setOperationType(transactionType);
        transaction.setProduct(productRepository.getById(newTransactionDTO.getProductId()));
        transaction.setDate(newTransactionDTO.getDate());
        transaction.setQuantity(newTransactionDTO.getQuantity());
        return transactionRepository.save(transaction);
    }

    private boolean isProductExist(long id){
        return productRepository.existsById(id);
    }

    public Transaction getTransaction(long id){
        return transactionRepository.getById(id);
    }

    public Transaction editTransaction(TransactionEditDTO editTransactionData){
        Transaction editedTransaction = getTransaction(editTransactionData.getTransactionId());
        if(editTransactionData.getDate()!=null)
            editedTransaction.setDate(editTransactionData.getDate());
        if(editTransactionData.getQuantity()!=0)
            editedTransaction.setQuantity(editTransactionData.getQuantity());
        if(editTransactionData.getProductId()!=0)
            editedTransaction.setProduct(productRepository.getById(editTransactionData.getProductId()));
        return transactionRepository.save(editedTransaction);
    }

    public void deleteTransaction(long id){
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getProductTransactions(long id){
        return transactionRepository.getAllByProductId(id);
    }

    public List<ProductQuantity> getProductCurrentQuantity(){
        List<ProductQuantity> productsArrivalQuantity = transactionRepository.getProductsArrivalQuantity();
        List<ProductQuantity> productsAllowanceQuantity = transactionRepository.getProductsAllowanceQuantity();
        for (ProductQuantity productArrivalQuantity: productsArrivalQuantity) {
            ProductQuantity productAllowanceQuantity = productsAllowanceQuantity.stream().filter(
                    productQuantity -> productArrivalQuantity.getName().equals(productQuantity.getName()))
                    .findAny().orElse(new ProductQuantity(productArrivalQuantity.getName(),0));
                productArrivalQuantity.setQuantity(productAllowanceQuantity.getQuantity()-productAllowanceQuantity.getQuantity());
        }
        return productsArrivalQuantity;
    }
}