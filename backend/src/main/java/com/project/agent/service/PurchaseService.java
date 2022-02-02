package com.project.agent.service;

import com.project.agent.dto.PurchaseDTO;
import com.project.agent.exceptions.EntityDoesNotExistException;

public interface PurchaseService {

    void newOrder(PurchaseDTO purchaseDTO) throws EntityDoesNotExistException;
    String getMostSold(String agentUsername) throws EntityDoesNotExistException;
    String getMostProfit(String agentUsername) throws EntityDoesNotExistException;
}
