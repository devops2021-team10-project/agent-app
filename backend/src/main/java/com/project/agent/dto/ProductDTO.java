package com.project.agent.dto;

public class ProductDTO {
    private String name;
    private String image;
    private double price;
    private int inStock;
    private String agentUsername;

    public ProductDTO() {
    }

    public ProductDTO(String name, String image, double price, int inStock, String agentUsername) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.inStock = inStock;
        this.agentUsername = agentUsername;    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getAgentUsername() {
        return agentUsername;
    }

    public void setAgentUsername(String agentUsername) {
        this.agentUsername = agentUsername;
    }
}
