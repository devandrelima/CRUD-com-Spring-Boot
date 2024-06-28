package com.crud.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.spring.domain.product.Product;
import com.crud.spring.domain.product.RequestProduct;
import com.crud.spring.repository.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProduts() {
        var allProduts = repository.findAll();                                          // Recupera os dados do banco de dados e guarda na variável
        return ResponseEntity.ok(allProduts);                                           // Se tudo Ok, retorna os dados                                                            
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data) {    // Recebe e valida o dado do postman
        Product newProduct = new Product(data);                                         // Trata o dado que veio do postman para inserir no banco de dados 
        repository.save(newProduct);                                                    // Insere o produto no banco de dados 
        System.out.println(newProduct);         
        return ResponseEntity.ok().build();                                             // Se não colocar nada dentro do 'ok', precisa inserir o 'build' depois
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {
        Product product = repository.getReferenceById(data.id());
        product.setName(data.name());
        product.setPrice_in_cents(data.price_in_cents());
        return ResponseEntity.ok(product);
    }
}
