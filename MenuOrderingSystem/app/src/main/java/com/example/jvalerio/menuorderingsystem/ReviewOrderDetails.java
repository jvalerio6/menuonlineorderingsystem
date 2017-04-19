/******************************************
 Programmer: Javier Valerio
 Date Originally Submitted: February 10th, 2017
 Last Modified on: February 20th, 2017
 Dr. Shrivastava
 CS 481 - Menu Ordering System - Part Two
 ******************************************/

package com.example.jvalerio.menuorderingsystem;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ReviewOrderDetails extends AppCompatActivity {

    // Instance variables
    TextView subTotalView, totalView, orderDetails, taxView, waitTime;
    Map<String, MenuItem> cart;
    List<MenuItem> menuList = new ArrayList<>();
    TabLayout tabLayout;
    ViewPager pager;
    Button submitButton;

    int totalSecs = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_order_v2);
        setTitle(R.string.order_details_label);

        // This activity is called from within the MainActivity class
        Intent intent = getIntent();

        // Deserialize the Map<String, MenuItem> object and operate on its elements
        cart = (HashMap<String, MenuItem>) intent.getSerializableExtra("CartObject");

        GridView gridView = (GridView) findViewById(R.id.gridview);

        displayCartItems();

        gridView.setAdapter(new MyGridAdapter(this, menuList));
    }

    /** PURPOSE: This method iterates over the elements of the cart and displays the details for each order: name, quantity
    *            and price. It then binds this data to the corresponding UI TextView and ImageView widgets.
    */
    public void displayCartItems() {
        Iterator iterator = cart.entrySet().iterator();
        double subTotal = 0, total = 0, tax = 0;

        // initialize UI widgets
        subTotalView = (TextView) findViewById(R.id.subtotal_view);
        taxView = (TextView) findViewById(R.id.tax_view);
        totalView = (TextView) findViewById(R.id.total_view);

        while (iterator.hasNext()) {
            Map.Entry e = (Map.Entry) iterator.next();
            MenuItem item = (MenuItem) e.getValue();

            // for simplicity, let's assume that each item takes 5 seconds to prepare
            totalSecs += (item.getQuantity() * 5);

            double current = item.getQuantity() * item.getPrice();

            subTotal += current;

            menuList.add(item);
        }

        // calculate tax and total value
        tax = subTotal * 0.075;
        total = subTotal + tax;

        subTotalView.setText("Subtotal: " + '\t' + "$" + String.format("%.2f", subTotal));
        taxView.setText("Tax (7.50%): " + '\t' + "$" + String.format("%.2f", tax));
        totalView.setText("Total: " + '\t' + "$" + String.format("%.2f", total));
    }


    /** PURPOSE: This method iterates over the elements of the cart and displays a textual representation of each item's details: name, quantity
     *            and price. It then binds this data to the corresponding UI TextViews. This method was used in the first version of the app and is
     *            currently kept for future reference. The method displayCartItems() is the active version of this method.
     *            @deprecated
     */
    public void displayCartItems_Old() {
        Iterator iterator = cart.entrySet().iterator();
        StringBuilder sb = new StringBuilder();

        // initialize UI widgets
        subTotalView = (TextView) findViewById(R.id.subtotal_view);
        taxView = (TextView) findViewById(R.id.tax_view);
        totalView = (TextView) findViewById(R.id.total_view);

        orderDetails = (TextView) findViewById(R.id.cart_details);
        orderDetails.setMovementMethod(new ScrollingMovementMethod());

        double subTotal = 0, total = 0, tax = 0;

        while (iterator.hasNext()) {
            Map.Entry e = (Map.Entry) iterator.next();
            MenuItem item = (MenuItem) e.getValue();


            // for simplicity, let's assume that each item takes 5 seconds to prepare
            totalSecs += (item.getQuantity() * 5);


            sb.append(item.getName()).append('\n');
            sb.append("Quantity: " + item.getQuantity()).append('\n');
            sb.append("Size: " + item.getSize()).append('\n');

            double current = item.getQuantity() * item.getPrice();
            sb.append("(" + item.getQuantity() + ") x $" + item.getPrice() + " = $" + current).append('\n').append('\n');

            subTotal += current;
        }

        // calculate tax and total value
        tax = subTotal * 0.075;
        total = subTotal + tax;

        orderDetails.setText(sb.toString());
        subTotalView.setText("Subtotal: " + '\t' + "$" + String.format("%.2f", subTotal));
        taxView.setText("Tax (7.50%): " + '\t' + "$" + String.format("%.2f", tax));
        totalView.setText("Total: " + '\t' + "$" + String.format("%.2f", total));

    }


    /** PURPOSE: This method is called upon the user clicking on the 'Submit Order' button inside the activity.
    *            The function of this method is to alert the user that the user has been processed and how long
    *            the overall order will take to ready. A timer is created for the order items, and for simplicity
    *            each item takes 5 seconds to be prepared. After the time is up, this method redirects to another view.
    *   PARAMETERS: View view - the current UI view.
    */
    public void onSubmitOrder(View view) {

        setContentView(R.layout.payment);
        setTitle(R.string.payment_select_label);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);

        final PagerAdapter adapter = new PagerAdapter (getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());

                System.out.println("onTabSelected: " + tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        onSubmitPaypalPayment();
                        break;
                    case 1:
                        onSubmitCardPayment();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    private void onSubmitPaypalPayment () {
        final String[] credentials = {"test@test.com", "test"};

        submitButton = (Button) findViewById(R.id.paypal_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.paypal_email);
                EditText password = (EditText) findViewById(R.id.paypal_password);

                if (username.getText().toString().equals(credentials[0]) && password.getText().toString().equals(credentials[1])) {
                    Toast.makeText(ReviewOrderDetails.this, "Success", Toast.LENGTH_SHORT).show();

                    setProcessingOrder();
                }

                else {
                    Toast.makeText(ReviewOrderDetails.this, "No Success", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }


    private void onSubmitCardPayment () {
        final String[] card_details = {"123456789", "001"};

        submitButton = (Button) findViewById(R.id.cc_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText digits = (EditText) findViewById(R.id.cc_number);
                EditText ccv = (EditText) findViewById(R.id.ccv);

                if (digits.getText().toString().equals(card_details[0]) && ccv.getText().toString().equals(card_details[1])) {
                    Toast.makeText(ReviewOrderDetails.this, "Success", Toast.LENGTH_SHORT).show();

                    setProcessingOrder();
                }

                else {
                    Toast.makeText(ReviewOrderDetails.this, "No Success", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    private void setProcessingOrder () {
        setContentView(R.layout.process_order);
        setTitle(R.string.processing_label);

        waitTime = (TextView) findViewById(R.id.waiting_time);

        // create a countdown for when the order is ready
        CountDownTimer cdt = new CountDownTimer(totalSecs * 1000, 1000) {

            public void onTick(long millisUntilFinished) {

                long hours = (millisUntilFinished / 1000) / 3600;
                long minutes = ((millisUntilFinished / 1000) % 3600) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;

                waitTime.setText("Waiting time: " + String.format("%02d:%02d:%02d", hours, minutes, seconds));
            }

            public void onFinish() {

                // when the order is ready, switch view.
                waitTime.setText("Done!");
                setContentView(R.layout.order_ready);
                setTitle(R.string.order_ready_label);

            }
        }.start();
    }


    public void placeNewOrder (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
