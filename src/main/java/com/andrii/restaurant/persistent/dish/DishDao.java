package com.andrii.restaurant.persistent.dish;

import com.andrii.restaurant.model.Dish;

import java.util.List;

public interface DishDao {
    Dish findById(int id);
    Dish save(Dish dish);
    List<Dish> findAll();
  //  void delete(int id);
}
