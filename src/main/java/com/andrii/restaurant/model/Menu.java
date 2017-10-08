package com.andrii.restaurant.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu implements Iterable<Dish> {

    private List<Dish> dishes = new ArrayList<>();

    public void add(Dish dish) {
        dishes.add(dish);
    }

    @Override
    public Iterator<Dish> iterator() {
        return dishes.iterator();
    }
}
