package com.example.apprestaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.apprestaurant.dao.FoodDao;
import com.example.apprestaurant.database.AppDatabase;
import com.example.apprestaurant.entities.Food;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FoodAdapter adapter;
    private FoodDao foodDao;
    private int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        foodDao = db.foodDao();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FoodAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        restaurantId = getIntent().getIntExtra("RESTAURANT_ID", -1);

        // Load food items
        new LoadFoodsTask().execute();

        findViewById(R.id.btnAddFood).setOnClickListener(v -> {
            Intent intent = new Intent(RestaurantDetailsActivity.this, AddFoodActivity.class);
            intent.putExtra("RESTAURANT_ID", restaurantId);
            startActivity(intent);
        });
    }

    private class LoadFoodsTask extends AsyncTask<Void, Void, List<Food>> {
        @Override
        protected List<Food> doInBackground(Void... voids) {
            return foodDao.getAllByRestaurant(restaurantId);
        }


    }
}
