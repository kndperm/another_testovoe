package ru.knd.another_testovoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knd.another_testovoe.dto.ProductDTO;
import ru.knd.another_testovoe.model.Product;
import ru.knd.another_testovoe.repository.ProductRepository;

import javax.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    public Product addProduct(ProductDTO newProductDTO){
        Product newProduct = new Product();
        newProduct.setName(newProductDTO.getName());
        newProduct.setShortName(newProductDTO.getShortName());
        newProduct.setSpecification(newProductDTO.getSpecification());
        return repository.save(newProduct);
    }

    public Product getProduct(long id){
        return repository.getById(id);
    }

    @Transactional
    public Product editProduct(Product editProductData){
        Product editedProduct =  getProduct(editProductData.getId());
        if(editProductData.getName()!=null)
            editedProduct.setName(editProductData.getName());
        if(editProductData.getShortName()!=null)
            editedProduct.setShortName(editProductData.getShortName());
        if(editProductData.getSpecification()!=null)
            editedProduct.setSpecification(editProductData.getSpecification());
        return repository.save(editedProduct);
    }

    public void deleteProduct(long id){
        repository.deleteById(id);
    }
}
