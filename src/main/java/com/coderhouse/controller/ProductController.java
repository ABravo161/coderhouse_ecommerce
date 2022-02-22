package com.coderhouse.controller;

import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;
import com.coderhouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @PostMapping("/add")
    public ProductResponse create(
            @Validated @RequestBody ProductRequest product) throws ApiRestException {
        return service.create(product);
    }

    @PutMapping("/{code}")
    public ProductResponse update(@PathVariable String code,
                                         @Validated @RequestBody ProductRequest product) {
        return service.update(code, product);
    }

    @GetMapping("/all")
    public List<ProductResponse> searchAll() {
        return service.searchAll();
    }

    @GetMapping("/{code}")
    public ProductResponse search(@PathVariable String code) throws ApiRestException{
        return service.searchByCode(code);
    }

    @GetMapping("/category/{category}")
    public List<ProductResponse> searchByCategory(@PathVariable String category) {
        return service.searchByCategory(category);
    }

    @DeleteMapping("/{code}")
    public ProductResponse delete(@PathVariable String code) {
        return service.delete(code);
    }

}
