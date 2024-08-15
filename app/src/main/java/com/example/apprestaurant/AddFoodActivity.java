package com.example.apprestaurant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.apprestaurant.dao.FoodDao;
import com.example.apprestaurant.database.AppDatabase;
import com.example.apprestaurant.entities.Food;

public class AddFoodActivity extends AppCompatActivity {
    private EditText editTextName, editTextPrice, editTextDescription;
    private Spinner spinnerType;
    private Button buttonSave;
    private int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDescription = findViewById(R.id.editTextDescription);
        spinnerType = findViewById(R.id.spinnerType);
        buttonSave = findViewById(R.id.buttonSave);

        restaurantId = getIntent().getIntExtra("RESTAURANT_ID", -1);

        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            double price = Double.parseDouble(editTextPrice.getText().toString());
            String description = editTextDescription.getText().toString();
            String type = spinnerType.getSelectedItem().toString();

            if (!name.isEmpty() && restaurantId != -1) {
                new SaveFoodTask().execute(name, price, description, type, restaurantId);
            }
        });
    }

    private class SaveFoodTask extends AsyncTask<Object, Void, Void> {
        @Override
        protected Void doInBackground(Object... params) {
            FoodDao foodDao = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database-name").build().foodDao();
            Food food = new Food();
            food.name = (String) params[0];
            food.price = (Double) params[1];
            food.description = (String) params[2];
            food.type = (String) params[3];
            food.restaurant_id = (Integer) params[4];
            foodDao.insert(food);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(AddFoodActivity.this, "Food added", Toast.LENGTH_SHORT).show();
            finish();  // Close activity and return to the details screen
        }
    }
}

