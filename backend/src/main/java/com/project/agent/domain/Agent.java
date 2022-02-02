package com.project.agent.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.agent.dto.UserDTO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Agent extends User {

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    @JsonBackReference
    List<Product> productList;

    public Agent() {}

    public Agent(String username, String password) {
        super(username, password);
        this.productList = new ArrayList<>();
    }

    public Agent(UserDTO userDTO) {
        super(userDTO.getUsername(), userDTO.getPassword());
        this.productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
