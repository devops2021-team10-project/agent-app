package com.project.agent.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.agent.dto.ProductDTO;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column()
    private String image;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int inStock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agent;

    @OneToOne(cascade = CascadeType.ALL)
    private Report report;

    public Product() {
        this.report = new Report();
    }

    public Product(String name, String image, double price, int inStock) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.inStock = inStock;
        this.report = new Report();
    }

    public Product(ProductDTO productDTO) {
        this.name = productDTO.getName();
        this.image = productDTO.getImage();
        this.price = productDTO.getPrice();
        this.inStock = productDTO.getInStock();
        this.report = new Report();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
