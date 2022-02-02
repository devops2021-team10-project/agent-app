package com.project.agent.controller;

import com.project.agent.domain.Product;
import com.project.agent.dto.ProductDTO;
import com.project.agent.exceptions.EntityAlreadyExistsException;
import com.project.agent.exceptions.EntityDoesNotExistException;
import com.project.agent.exceptions.InvalidDataException;
import com.project.agent.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/get_all", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Product>> getAllProducts() {
        try {
            ArrayList<Product> products = productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{agentUsername}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Product>> getAgentProducts(@PathVariable String agentUsername) {
        try {
            ArrayList<Product> agentProducts = productService.getAgentProducts(agentUsername);
            return new ResponseEntity<>(agentProducts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.createProduct(productDTO);
        } catch (InvalidDataException | EntityAlreadyExistsException | EntityDoesNotExistException e) {
            return new ResponseEntity<>("Creation of a new product failed.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Creation of a new product successful", HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId}/update", method = RequestMethod.POST)
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO,
                                                @PathVariable String productId) {
        try {
            productService.updateProduct(productId, productDTO);
        } catch (InvalidDataException | EntityAlreadyExistsException | EntityDoesNotExistException e) {
            return new ResponseEntity<>("Update of a product failed.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Update of a product successful", HttpStatus.OK);
    }

    @RequestMapping(value = "/{productId}/delete", method = RequestMethod.POST)
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        try {
            productService.deleteProduct(productId);
        } catch (EntityDoesNotExistException e) {
            return new ResponseEntity<>("Deletion of a product failed.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Deletion of a product successful", HttpStatus.OK);
    }
}
