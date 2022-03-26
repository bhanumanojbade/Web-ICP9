package com.example.pizzaordering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class summaryPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_page);
        //getting username from main activity using intent
        String customerName=getIntent().getStringExtra("customerName");
        // getting Summary from main activity using intent
        String Summary=getIntent().getStringExtra("finalReview");
        //binding the textview values
        TextView username=findViewById(R.id.username);
        // assigning value of username
        username.setText("Hello " + customerName);

        TextView description=findViewById(R.id.summary);
        description.setText(Summary);
    }
    public void backToOrder(View view) {
        //intent to navigate to new page
        Intent x = new Intent(summaryPage.this, MainActivity.class);
        startActivity(x);
    }

}
