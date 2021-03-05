package com.deeksha.decorator;

import com.deeksha.GameBean;
import com.deeksha.GameManager;
import com.deeksha.GameService;
import com.deeksha.model.Product;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.util.List;

@Decorator
public class GameDecorator implements GameManager {

    @Inject
    @Delegate
    private GameService gameService;

    @Override
    public void add(Product product) {
        gameService.add(product);
    }

    @Override
    public List<Product> getProduct()
    {
        return gameService.getProduct();
    }
    @Override
    public void delete(Product product) {
        gameService.delete(product);
    }
}
