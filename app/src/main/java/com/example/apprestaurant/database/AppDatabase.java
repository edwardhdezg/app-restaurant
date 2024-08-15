package com.example.apprestaurant.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.apprestaurant.dao.FoodDao;
import com.example.apprestaurant.dao.RestaurantDao;
import com.example.apprestaurant.entities.Food;
import com.example.apprestaurant.entities.Restaurant;


@Database(entities = {Restaurant.class, Food.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RestaurantDao restaurantDao();
    public abstract FoodDao foodDao();
}
