package ru.knd.another_testovoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.knd.another_testovoe.dto.ProductDTO;
import ru.knd.another_testovoe.model.Product;
import ru.knd.another_testovoe.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductDTO newProduct){
        return service.addProduct(newProduct);
    }

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable long id){
        return service.getProduct(id);
    }

    @PutMapping("/edit")
    public Product editProduct(@RequestBody Product product){
        return service.editProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable long id){
        service.deleteProduct(id);
    }
}
