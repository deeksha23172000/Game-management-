package com.deeksha;

import com.deeksha.model.GameProduct;
import com.deeksha.model.Product;
import com.deeksha.qualifiers.AddGame;
import com.deeksha.qualifiers.DeleteGame;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GameService implements GameManager {
    private List<Product> gameProduct;

    @PostConstruct
    public void addGame() {
        gameProduct = new ArrayList<>();
        gameProduct.add(new GameProduct("ViceCity", 10, 50,"Rockstar North"));
    }
    @Override
    public List<Product> getProduct()
    {
        return gameProduct;
    }
    @Override

    public void delete(@Observes @DeleteGame Product product) {

        Product deletedGame = gameProduct.stream().filter(gamex ->
                gamex.getName().equals(product.getName())).findFirst().orElse(null);
        gameProduct.remove(deletedGame);
    }

    @Override
    public void add(@Observes  @AddGame Product product)
    {
        Product checkProduct = gameProduct.stream().filter(gamex ->
                gamex.getName().equals(product.getName()))
                .findFirst().orElse(null);

        if(gameProduct.contains(checkProduct)) {
            int newQty = checkProduct.getQuantityInStore()+1; // to increase the quantity by one

            product.setQuantityInStore(newQty);

            product.setProducerCompany(checkProduct.getProducerCompany());

            product.setPrice(checkProduct.getPrice());

            gameProduct.remove(checkProduct); // to remove a product by using name

            gameProduct.add(product); // to add a product
        }
        else {
            product.setQuantityInStore(1);
            gameProduct.add(product);
        }
    }



}
