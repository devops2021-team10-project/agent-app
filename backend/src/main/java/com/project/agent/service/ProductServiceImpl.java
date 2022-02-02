package com.project.agent.service;

import com.project.agent.domain.Agent;
import com.project.agent.domain.Product;
import com.project.agent.domain.User;
import com.project.agent.dto.ProductDTO;
import com.project.agent.repository.AgentRepository;
import com.project.agent.repository.ProductRepository;
import com.project.agent.exceptions.EntityAlreadyExistsException;
import com.project.agent.exceptions.EntityDoesNotExistException;
import com.project.agent.exceptions.InvalidDataException;
import com.project.agent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;


    public ArrayList<Product> getAllProducts() {
        ArrayList<Agent> agents = new ArrayList<>(agentRepository.findAll());
        ArrayList<Product> products = new ArrayList<>();

        for (Agent a : agents) {
            products.addAll(a.getProductList());
        }
        return products;
    }

    public ArrayList<Product> getAgentProducts(String agentUsername) throws EntityDoesNotExistException {

        Optional<Agent> agent = agentRepository.findByUsername(agentUsername);

        if (agent.isEmpty()) {
            throw new EntityDoesNotExistException("Given agent not found");
        }

        return new ArrayList<>(agent.get().getProductList());
    }

    public void createProduct(ProductDTO productDTO) throws EntityAlreadyExistsException, InvalidDataException, EntityDoesNotExistException {

        if (productDTO == null) {
            throw new InvalidDataException("Data not found");
        }

        if (productRepository.findByName(productDTO.getName()).isPresent()) {
            throw new EntityAlreadyExistsException("Product with given name already exists.");
        }

        Product product = new Product(productDTO);

        Optional<User> user = userRepository.findByUsername(productDTO.getAgentUsername());

        if (user.isEmpty()) {
            throw new EntityDoesNotExistException("Given agent not found");
        }


        Agent agent = (Agent) user.get();

        agent.getProductList().add(product);
        product.setAgent(agent);
        productRepository.save(product);
    }

    public void updateProduct(String productId, ProductDTO productDTO) throws EntityDoesNotExistException, EntityAlreadyExistsException, InvalidDataException {

        Optional<Product> product = productRepository.findById(Long.valueOf(productId));
        if (product.isEmpty()) {
            throw new EntityDoesNotExistException("Product does not exits");
        }

        productRepository.deleteById(Long.valueOf(productId));
        //TODO save report from previous version of the product
        this.createProduct(productDTO);
    }

    public void deleteProduct(String productId) throws EntityDoesNotExistException {

        Optional<Product> product = productRepository.findById(Long.valueOf(productId));
        if (product.isEmpty()) {
            throw new EntityDoesNotExistException("Product does not exits");
        }

        productRepository.deleteById(Long.valueOf(productId));
    }
}
