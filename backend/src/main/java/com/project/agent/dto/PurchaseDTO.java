package com.project.agent.dto;

import java.util.List;

public class PurchaseDTO {

    private List<String> boughtProducts;
    private String buyerInfo;

    public PurchaseDTO() {
    }

    public PurchaseDTO(List<String> boughtProducts, String buyerInfo) {
        this.boughtProducts = boughtProducts;
        this.buyerInfo = buyerInfo;
    }

    public List<String> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<String> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public String getBuyerInfo() {
        return buyerInfo;
    }

    public void setBuyerInfo(String buyerInfo) {
        this.buyerInfo = buyerInfo;
    }
}
