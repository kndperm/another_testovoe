package ru.knd.another_testovoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.knd.another_testovoe.dto.ProductQuantity;
import ru.knd.another_testovoe.model.Product;
import ru.knd.another_testovoe.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getAllByProductId(long id);

    @Query("select new ru.knd.another_testovoe.dto.ProductQuantity(p.name, sum(t.quantity))" +
            " from Transaction t join t.product p where t.operationType='Поступление' group by p.name")
    List<ProductQuantity> getProductsArrivalQuantity();

    @Query("select new ru.knd.another_testovoe.dto.ProductQuantity(p.name, sum(t.quantity))" +
            " from Transaction t join t.product p where t.operationType='Списание' group by p.name")
    List<ProductQuantity> getProductsAllowanceQuantity();
}
