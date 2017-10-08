package com.andrii.restaurant.persistent;

import com.andrii.restaurant.model.Dish;
import com.andrii.restaurant.model.Menu;

import java.util.List;

public interface DishDao {
    Dish findById(int id);
    Dish save(Dish dish);
    List<Dish> findAll();
  //  void delete(int id);
}
