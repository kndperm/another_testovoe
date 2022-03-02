package ru.knd.another_testovoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.knd.another_testovoe.dto.ProductDTO;
import ru.knd.another_testovoe.dto.StatusResponse;
import ru.knd.another_testovoe.model.Product;
import ru.knd.another_testovoe.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/add")
    public StatusResponse addProduct(@RequestBody ProductDTO newProduct){
        Product product = service.addProduct(newProduct);
        return new StatusResponse("OK", "Продукт добавлен", product);
    }

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable long id){
        return service.getProduct(id);
    }

    @PutMapping("/edit")
    public StatusResponse editProduct(@RequestBody Product product){
        Product editProduct = service.editProduct(product);
        return new StatusResponse("OK", "Продукт изменен", editProduct);
    }

    @DeleteMapping("/delete/{id}")
    public StatusResponse deleteProduct(@PathVariable long id){
        service.deleteProduct(id);
        return new StatusResponse("OK", "Продукт удален", null);
    }
}
