package com.coderhouse.service;

import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest product) throws ApiRestException;

    ProductResponse update(String code, ProductRequest product);

    ProductResponse delete(String code);

    List<ProductResponse> searchAll();

    ProductResponse searchByCode(String code) throws ApiRestException;

    List<ProductResponse> searchByCategory(String category);
}
