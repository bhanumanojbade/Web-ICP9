package com.example.pizzaordering;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //variables declaration
    String review, mailReview,customerName,reviewChange,regularSize,mediumSize,largeSize,sides1,sides2,sides3,finalReview;
    int noOfPizza=1;
    TextView quantityValue;
    TextView userName;
    pizza pizza;
    int price;
    TextView cartValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //constructor calling
        pizza = new pizza();
        quantityValue = findViewById(R.id.quantityValue);
        userName = findViewById(R.id.userName);
        TextView customerName=findViewById(R.id.userName);
        cartValue = findViewById(R.id.cartValue);
        //cart value
        price = (pizza.getSize()+ pizza.getSalami()+ pizza.getblackOlives()+
                pizza.getBacon())*noOfPizza;
       //setting name
        cartValue.setText(""+price);

    }

        //Radio button method
        public void sizeMethod (View view){
            //checking whether the radio button is clicked
            boolean check = ((RadioButton) view).isChecked();
            switch (view.getId()) {
                case R.id.rb1:
                    if (check) {
                        pizza.setSize(20);
                        regularSize="Small";
                    }
                    break;
                case R.id.rb2:
                    if (check) {
                        pizza.setSize(23);
                        mediumSize="Medium";
                    }
                    break;
                case R.id.rb3:
                    if (check) {
                        pizza.setSize(25);
                        largeSize="Large";
                    }
                    break;
            }
            cartValue.setText(""+ pizza.getSize());
        }
       // check box method for topping
        public void checkBoxMethod (View view){
            //checking whether the check button is clicked
            boolean check = ((CheckBox) view).isChecked();
            switch (view.getId()) {
                case R.id.cb1:
                    if (check) {
                        pizza.setBacon(6);
                        sides1="Bacon";
                    } else {
                        pizza.setBacon(0);
                    }
                    break;
                case R.id.cb2:
                    if (check) {
                        pizza.setblackOlives(10);
                        sides2="Black Olives";
                    } else {
                        pizza.setblackOlives(0);
                    }
                    break;
                case R.id.cb3:
                    if (check) {
                        pizza.setSalami(10);
                        sides3="Salami";
                    } else {
                        pizza.setSalami(0);
                    }
                    break;
            }
            //cart value
            price = (pizza.getSize()+ pizza.getSalami()+ pizza.getblackOlives()+
                    pizza.getBacon());
            cartValue.setText(""+price);
        }

        // Increment Method calling
        public void increment (View view){
            //Quantity condition check
            if (noOfPizza >= 1) {
                //increment
                noOfPizza++;
                quantityValue.setText("" + noOfPizza);
                // cart value
                price = (pizza.getSize()+ pizza.getSalami()+ pizza.getblackOlives()+
                        pizza.getBacon())*noOfPizza;
                cartValue.setText(""+price);
            } else {
                noOfPizza = 1;
            }

        }
       // decrement method calling
        public void decrement (View view){
            //Quantity condition check
            if (noOfPizza > 1) {
                // decrement
                noOfPizza--;
                quantityValue.setText("" + noOfPizza);
                //cart value
                price = (pizza.getSize()+ pizza.getSalami()+ pizza.getblackOlives()+
                        pizza.getBacon())*noOfPizza;
                cartValue.setText(""+price);
            } else {
                noOfPizza = 1;
            }
        }

    public void overview(View view) {
        // message display
        Toast.makeText(MainActivity.this,"Your summary",Toast.LENGTH_SHORT).show();
        customerName= userName.getText().toString();
        if(customerName.isEmpty()){
            // name check
            Toast.makeText(MainActivity.this,"Name field should not be empty",Toast.LENGTH_SHORT).show();
        }else {
            // size check for summary
            if(regularSize == null && mediumSize ==null) {
                review = "\n You Ordered :" + largeSize + " size pizza";
            } else  if(mediumSize == null && largeSize == null) {
                review = "\n You Ordered :" + regularSize + " size pizza";
            } else  if(largeSize == null && regularSize==null) {
                review = "\n You Ordered :" + mediumSize + " size pizza";
            }
            // toppings check for summary
            if (sides1== null && sides2== null && sides3== null)
            {
                reviewChange="\n"+"empty!! no toppings selected"
                        +"\n" +"Quantity " +noOfPizza
                        +"\n" +"Cart Amount: " + price;
            }else{
                reviewChange="\n"+"Toppings Included: \n"
                         + ((sides2 == null) ? "" : sides2+"\n")
                         +((sides1 == null) ? "" : sides1+"\n")
                         +((sides3 == null) ? "" : sides3) +
                        "\n" +"Quantity" +noOfPizza
                         +"\n" +"Cart Amount: " + price;
            }
            //final summary
            finalReview=review +""+"\n"+""+reviewChange;
            //mail final summary
            mailReview="UserName: " +((customerName==null)?"no Name": customerName)+"\n" + ((finalReview==null)?"no final Summary": finalReview);
            Intent x = new Intent(MainActivity.this, summaryPage.class);
            x.putExtra("customerName", customerName);
            x.putExtra("finalReview", finalReview);
            startActivity(x);
        }
    }

    //mail method calling
    public void mailNotification(View view) {
        Toast.makeText(MainActivity.this,"Email",Toast.LENGTH_SHORT).show();
        //mail navigation intent
        Intent mailMessage = new Intent(Intent.ACTION_SEND);
        // to box filling
        mailMessage.putExtra(Intent.EXTRA_EMAIL,new String[]{"nag3v@umsystem.com"});
        // subject filling
        mailMessage.putExtra(Intent.EXTRA_SUBJECT, "Order Review");
        //body filling
        mailMessage.putExtra(Intent.EXTRA_TEXT, mailReview);
        mailMessage.setType("message/rfc822");
        startActivity(mailMessage);
    }
}
