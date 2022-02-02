package com.project.agent.domain;

import com.project.agent.dto.PurchaseDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String buyerInfo;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> boughtProducts;

    public Purchase() {
    }

    public Purchase(String buyerInfo, List<String> boughtProducts) {
        this.buyerInfo = buyerInfo;
        this.boughtProducts = boughtProducts;
    }

    public Purchase(PurchaseDTO purchaseDTO) {
        this.buyerInfo = purchaseDTO.getBuyerInfo();
        this.boughtProducts = purchaseDTO.getBoughtProducts();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerInfo() {
        return buyerInfo;
    }

    public void setBuyerInfo(String buyerInfo) {
        this.buyerInfo = buyerInfo;
    }

    public List<String> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<String> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }
}
