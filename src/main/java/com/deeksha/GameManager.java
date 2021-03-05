package com.deeksha;

import com.deeksha.model.Product;

import java.util.List;

public interface GameManager {
    void add(Product product);

    List<Product> getProduct();

    void delete(Product product);

}
