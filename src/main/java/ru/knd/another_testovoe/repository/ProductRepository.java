package ru.knd.another_testovoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knd.another_testovoe.model.Product;

public interface ProductRepository extends JpaRepository <Product, Long> {
}
