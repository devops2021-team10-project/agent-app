package com.project.agent.service;

import com.project.agent.domain.Product;
import com.project.agent.dto.ProductDTO;
import com.project.agent.exceptions.EntityAlreadyExistsException;
import com.project.agent.exceptions.EntityDoesNotExistException;
import com.project.agent.exceptions.InvalidDataException;

import java.util.ArrayList;

public interface ProductService {

    ArrayList<Product> getAllProducts();
    ArrayList<Product> getAgentProducts(String agentUsername) throws EntityDoesNotExistException;
    void createProduct(ProductDTO productDTO) throws EntityAlreadyExistsException, InvalidDataException, EntityDoesNotExistException;
    void updateProduct(String productId, ProductDTO productDTO) throws EntityDoesNotExistException, EntityAlreadyExistsException, InvalidDataException;
    void deleteProduct(String productId) throws EntityDoesNotExistException;
}
