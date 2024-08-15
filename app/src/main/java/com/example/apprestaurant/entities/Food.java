package com.example.apprestaurant.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "food",
        foreignKeys = @ForeignKey(entity = Restaurant.class,
                parentColumns = "id",
                childColumns = "restaurant_id",
                onDelete = ForeignKey.CASCADE))
public class Food {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int restaurant_id;
    public String name;
    public double price;
    public String description;
    public String type;
}
