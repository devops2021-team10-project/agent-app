package com.project.agent.controller;

import com.project.agent.domain.Product;
import com.project.agent.dto.PurchaseDTO;
import com.project.agent.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<String> newPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        try {
            purchaseService.newOrder(purchaseDTO);
        } catch (Exception e) {
            return new ResponseEntity<>("Placing a new order failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Placing a new order successful", HttpStatus.OK);
    }

    @RequestMapping(value = "/{agentUsername}/most_sold", method = RequestMethod.GET)
    public ResponseEntity<String> getMostSoldProduct(@PathVariable String agentUsername) {
        try {
            String productName = purchaseService.getMostSold(agentUsername);
            return new ResponseEntity<>(productName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{agentUsername}/most_profit", method = RequestMethod.GET)
    public ResponseEntity<String> getMostProfitProduct(@PathVariable String agentUsername) {
        try {
            String productName = purchaseService.getMostProfit(agentUsername);
            return new ResponseEntity<>(productName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not Found", HttpStatus.BAD_REQUEST);
        }
    }
}
