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

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Transaction addTransaction(TransactionDTO transactionDTO, String transactionType) {
        if (isProductExist(transactionDTO.getProductId())) {
            if (transactionType.equals("Списание") && isTotalQuantityNegative(transactionDTO))
                return null;
            return saveTransaction(transactionDTO, transactionType);
        } else return null;
    }

    private boolean isTotalQuantityNegative(TransactionDTO transactionDTO) {
        List<ProductQuantity> productsCurrentQuantity = getProductCurrentQuantity();
        Product product = productRepository.getById(transactionDTO.getProductId());
        try {
            ProductQuantity currentQuantity = productsCurrentQuantity.stream().filter(productQuantity ->
                    productQuantity.getName().equals(product.getName())).findFirst().get();
            return currentQuantity.getQuantity() - transactionDTO.getQuantity() < 0;
        } catch (Exception e) {
            return true;
        }
    }

    private Transaction saveTransaction(TransactionDTO newTransactionDTO, String transactionType) {
        Transaction transaction = new Transaction();
        transaction.setOperationType(transactionType);
        transaction.setProduct(productRepository.getById(newTransactionDTO.getProductId()));
        transaction.setDate(newTransactionDTO.getDate());
        transaction.setQuantity(newTransactionDTO.getQuantity());
        return transactionRepository.save(transaction);
    }

    private boolean isProductExist(long id) {
        return productRepository.existsById(id);
    }

    public Transaction getTransaction(long id) {
        return transactionRepository.getById(id);
    }

    @Transactional
    public Transaction editTransaction(TransactionEditDTO editTransactionData) {
        Transaction editedTransaction = getTransaction(editTransactionData.getTransactionId());
        if (editTransactionData.getDate() != null)
            editedTransaction.setDate(editTransactionData.getDate());
        if (editTransactionData.getQuantity() != 0)
            editedTransaction.setQuantity(editTransactionData.getQuantity());
        if (editTransactionData.getProductId() != 0)
            editedTransaction.setProduct(productRepository.getById(editTransactionData.getProductId()));
        return transactionRepository.save(editedTransaction);
    }

    public void deleteTransaction(long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getProductTransactions(long id) {
        return transactionRepository.getAllByProductId(id);
    }

    public List<ProductQuantity> getProductCurrentQuantity() {
        List<ProductQuantity> productsArrivalQuantity = transactionRepository.getProductsArrivalQuantity();
        List<ProductQuantity> productsAllowanceQuantity = transactionRepository.getProductsAllowanceQuantity();
        for (ProductQuantity productArrivalQuantity : productsArrivalQuantity) {
            ProductQuantity productAllowanceQuantity = productsAllowanceQuantity.stream().filter(
                            productQuantity -> productArrivalQuantity.getName().equals(productQuantity.getName()))
                    .findAny().orElse(new ProductQuantity(productArrivalQuantity.getName(), 0));
            productArrivalQuantity.setQuantity(productArrivalQuantity.getQuantity() - productAllowanceQuantity.getQuantity());
        }
        return productsArrivalQuantity;
    }
}
