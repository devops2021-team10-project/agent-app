package com.project.agent.service;

import com.project.agent.domain.Agent;
import com.project.agent.domain.Product;
import com.project.agent.domain.Purchase;
import com.project.agent.domain.Report;
import com.project.agent.dto.PurchaseDTO;
import com.project.agent.exceptions.EntityDoesNotExistException;
import com.project.agent.repository.AgentRepository;
import com.project.agent.repository.ProductRepository;
import com.project.agent.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AgentRepository agentRepository;

    public void newOrder(PurchaseDTO purchaseDTO) throws EntityDoesNotExistException {
        Purchase purchase = new Purchase(purchaseDTO);

        for (String s : purchase.getBoughtProducts()) {
            Optional<Product> oProduct = productRepository.findByName(s);
            if (oProduct.isEmpty()) {
                throw new EntityDoesNotExistException("No product with given name");
            }

            Product product = oProduct.get();

            product.setInStock(product.getInStock()-1);
            product.getReport().setTimesSold(product.getReport().getTimesSold() + 1);
            product.getReport().setTotalProfit(product.getReport().getTotalProfit() + product.getPrice());

            productRepository.save(product);
        }

        purchaseRepository.save(purchase);
    }

    public String getMostSold(String agentUsername) throws EntityDoesNotExistException {
        Optional<Agent> agent = agentRepository.findByUsername(agentUsername);
        if (agent.isEmpty()) {
            throw new EntityDoesNotExistException("No agent with given username");
        }

        ArrayList<Product> products = new ArrayList<>(agent.get().getProductList());
        int max = 0;
        Product mostSoldProduct = null;

        for (Product product : products) {
            if (product.getReport().getTimesSold() >= max) {
                max = product.getReport().getTimesSold();
                mostSoldProduct = product;
            }
        }
        if (mostSoldProduct != null) {
            return mostSoldProduct.getName();
        }
        return "";
    }

    public String getMostProfit(String agentUsername) throws EntityDoesNotExistException {
        Optional<Agent> agent = agentRepository.findByUsername(agentUsername);
        if (agent.isEmpty()) {
            throw new EntityDoesNotExistException("No agent with given username");
        }

        ArrayList<Product> products = new ArrayList<>(agent.get().getProductList());
        double max = 0;
        Product mostProfitProduct = null;

        for (Product product : products) {
            if (product.getReport().getTotalProfit() >= max) {
                max = product.getReport().getTotalProfit();
                mostProfitProduct = product;
            }
        }
        if (mostProfitProduct != null) {
            return mostProfitProduct.getName();
        }
        return "";
    }
}
