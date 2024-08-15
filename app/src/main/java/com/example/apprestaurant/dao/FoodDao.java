package com.example.apprestaurant.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apprestaurant.entities.Food;

import java.util.List;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM food WHERE restaurant_id = :restaurantId")
    List<Food> getAllByRestaurant(int restaurantId);

    @Insert
    void insert(Food food);

    @Update
    void update(Food food);

    @Delete
    void delete(Food food);
}
