package com.example.apprestaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.apprestaurant.dao.RestaurantDao;
import com.example.apprestaurant.database.AppDatabase;
import com.example.apprestaurant.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private RestaurantDao restaurantDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        restaurantDao = db.restaurantDao();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RestaurantAdapter(new ArrayList<>(),this);
        recyclerView.setAdapter(adapter);

        // Load data
        new LoadRestaurantsTask().execute();

        findViewById(R.id.btnAddRestaurant).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRestaurantActivity.class);
            startActivity(intent);
        });
    }

    private class LoadRestaurantsTask extends AsyncTask<Void, Void, List<Restaurant>> {
        @Override
        protected List<Restaurant> doInBackground(Void... voids) {
            return restaurantDao.getAll();
        }


    }
}
