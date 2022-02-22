package com.coderhouse.service.impl;

import com.coderhouse.builder.ProductBuilder;
import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;
import com.coderhouse.repository.ProductRepository;
import com.coderhouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest request) {
        validateRequestCreate(request);
        var document = productRepository.save(ProductBuilder.requestToDocument(request));
        return ProductBuilder.documentToResponse(document);
    }

    @Override
    public ProductResponse update(String code, ProductRequest request) {
        try {
            if (code.equals("0")) {
                throw ApiRestException.builder().message("El identificador del producto debe ser mayor a 0").build();
            }
            log.error("Id: {}", code);
            var oldProduct = productRepository.findByCode(code);
            log.error("oldProduct: {}", oldProduct);

            if (Objects.isNull(oldProduct)) {
                throw ApiRestException.builder().message("El producto con id "+ code +" no existe").build();
            }
            var oldId = oldProduct.getId();
            log.error("oldId: {}", oldId);
            var savedDocument = productRepository.save(ProductBuilder.requestToDocumentUpdate(oldId, request));
            return ProductBuilder.documentToResponse(savedDocument);
        } catch (ApiRestException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductResponse delete(String code) {
        return ProductBuilder.documentToResponse(productRepository.deleteByCode(code));
    }

    @Override
    public List<ProductResponse> searchAll() {
        return ProductBuilder.listDocumentToResponse(productRepository.findAll());
    }

    @Override
    public ProductResponse searchByCode(String code) throws ApiRestException{
        if (code.equals("0")) {
            throw new ApiRestException("El identificador del producto debe ser mayor a 0");
        }
        var dataFromDatabase = productRepository.findByCode(code);
        if (Objects.isNull(dataFromDatabase)) {
            throw new ApiRestException("El producto con id "+ code +" no existe");
        }
        return ProductBuilder.documentToResponse(dataFromDatabase);
    }

    @Override
    public List<ProductResponse> searchByCategory(String category) {
        return ProductBuilder.listDocumentToResponse(productRepository.findByCategory(category));
    }

    private void validateRequestCreate(ProductRequest request)  {
        try {
            var product = productRepository.findByCode(request.getCode());
            if (!Objects.isNull(product)) {
                throw ApiRestException.builder().message("El producto ya existe").build();
            }
        } catch (ApiRestException e) {
            e.printStackTrace();
        }
    }

}
