package com.andrii.restaurant.model;

import java.math.BigDecimal;
import java.util.Random;

public class Dish {

    private int id;
    private String name;
    private BigDecimal price;
    private Integer photoId;

    public Dish(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
    public Dish(){   }
    public Dish(String name, long price) {
        this(name, BigDecimal.valueOf(price));
    }

    public int getId() {
        return id;
    }

    public Dish setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dish setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Dish setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public Dish setPhotoId(Integer photoId) {
        this.photoId = photoId;
        return this;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", photoId='" + photoId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        if (price != null ? !price.equals(dish.price) : dish.price != null) return false;
        return photoId != null ? photoId.equals(dish.photoId) : dish.photoId == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (photoId != null ? photoId.hashCode() : 0);
        return result;
    }
}
