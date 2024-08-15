package com.example.apprestaurant.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurant")
public class Restaurant {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
}
