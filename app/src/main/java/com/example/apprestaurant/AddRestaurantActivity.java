package com.example.apprestaurant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.apprestaurant.dao.RestaurantDao;
import com.example.apprestaurant.database.AppDatabase;
import com.example.apprestaurant.entities.Restaurant;

public class AddRestaurantActivity extends AppCompatActivity {
    private EditText editTextName;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        editTextName = findViewById(R.id.editTextName);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            if (!name.isEmpty()) {
                new SaveRestaurantTask().execute(name);
            }
        });
    }

    private class SaveRestaurantTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... names) {
            RestaurantDao restaurantDao = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database-name").build().restaurantDao();
            Restaurant restaurant = new Restaurant();
            restaurant.name = names[0];
            restaurantDao.insert(restaurant);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(AddRestaurantActivity.this, "Restaurant added", Toast.LENGTH_SHORT).show();
            finish();  // Close activity and return to the main screen
        }
    }
}


