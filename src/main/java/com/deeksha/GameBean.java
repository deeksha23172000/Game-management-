package com.deeksha;

import com.deeksha.alternatives.GameAnnouncer;
import com.deeksha.interceptor.Logged;
import com.deeksha.model.GameProduct;
import com.deeksha.model.Product;
import com.deeksha.qualifiers.AddGame;
import com.deeksha.qualifiers.DeleteGame;


import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;


@Model
public class GameBean {
    private String name;
    private int price;
    private int quantityInStore;
    private String producerCompany;
    private String announcementMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantityInStore() {
        return quantityInStore;
    }

    public void setQuantityInStore(int quantityInStore) {
        this.quantityInStore = quantityInStore;
    }

    public String getProducerCompany() {
        return producerCompany;
    }

    public void setProducerCompany(String producerCompany) {
        this.producerCompany = producerCompany;
    }

    @Inject
    private GameAnnouncer announcer;
    //Events of  game

    @Inject
    @AddGame
    private Event<Product> gameAddEvent;

    @Inject
    @DeleteGame
    private Event<Product> gameDeleteEvent;

    @Inject
    private GameManager gameService;

    @PostConstruct
    public void constructMessage() {
        announcementMessage = announcer.announce();
    }

    public String getAnnouncementMessage() {
        return announcementMessage;
    }

    public List<Product> getProduct() {
        return gameService.getProduct();
    }

    @Logged
    public void addGame() {
        Product product_1 = new GameProduct(name,price,quantityInStore,producerCompany);
        gameAddEvent.fire(product_1);
    }
    @Logged
    public void deleteGame() {
        Product product_1 = new GameProduct(name,price,quantityInStore,producerCompany);
        gameDeleteEvent.fire(product_1);
    }
}

