/******************************************
 Programmer: Javier Valerio
 Date Originally Submitted: February 10th, 2017
 Last Modified on: February 20th, 2017
 Dr. Shrivastava
 CS 481 - Menu Ordering System - Part Two
 ******************************************/

package com.example.jvalerio.menuorderingsystem;

import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Instance variables
    TextView itemHeading, itemDetails, itemURL;
    EditText quantityText;
    Map<String, MenuItem> items, cart;
    Button addToCartButton, callButton;
    String selectedItemSize;
    Spinner itemSpinner, sizeSpinner;
    Menu menu = new Menu();
    MenuItem mi;
    ImageView itemImage;
    RatingBar itemRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        // initialize widgets
        items = menu.getMenu();
        cart = new HashMap();
        addToCartButton = (Button) findViewById(R.id.add_to_cart);
        callButton = (Button) findViewById(R.id.call_button);
        quantityText = (EditText) findViewById(R.id.quantity_text);
        itemSpinner = (Spinner) findViewById(R.id.items_spinner);

        callButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent phoneDialerIntent= new Intent(Intent.ACTION_DIAL);
            phoneDialerIntent.setData(Uri.parse("tel:" +  123456789));
            startActivity(phoneDialerIntent);
            }

        });

        onSelectMenuItem();
    }


    /** PURPOSE: After an item is selected from the spinner, and the 'Select' button is clicked, this method initializes
                the Widget objects, gets the correct value from the spinners and gets the item's information from the menu
                such as name, quantity, price, and image URL.
     */
    public void onSelectMenuItem () {
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // view where the details will be selected
                itemHeading = (TextView) findViewById(R.id.item_heading);
                itemDetails = (TextView) findViewById(R.id.item_details);
                itemURL = (TextView) findViewById(R.id.item_url);

                sizeSpinner = (Spinner) findViewById(R.id.size_spinner);
                itemImage = (ImageView) findViewById(R.id.item_image);
                quantityText = (EditText) findViewById(R.id.quantity_text);

                itemRating = (RatingBar) findViewById(R.id.ratingBar);

                StringBuilder sb = new StringBuilder();
                String selectedItem = String.valueOf(itemSpinner.getSelectedItem());
                selectedItemSize = String.valueOf(sizeSpinner.getSelectedItem());

                quantityText.setText("");

                if (items.containsKey(itemSpinner.getSelectedItem())) {
                    //Log.w("myApp", "enabled");

                    // bind the item details to the correct Views.
                    if (items.containsKey(selectedItem)) {
                        mi = items.get(selectedItem);

                        sb.append("S: $" + String.format("%.2f", mi.getPrice()) + " | M: $" + String.format("%.2f", mi.getPrice()+1.5) + " | L: $" + String.format("%.2f", mi.getPrice()+2.25));

                        itemImage.setImageResource(mi.getImageURL());
                        itemImage.setVisibility(View.VISIBLE);

                        itemHeading.setText(mi.getName());
                        itemDetails.setText(sb.toString());

                        String text = "<a href='" + mi.getNutritionFactsLink() + "'>See Nutritional Facts</a>";
                        itemURL.setText(Html.fromHtml(text));

                        //itemURL.setText(Html.fromHtml("Here is a <a href=\"http://www.google.com\">link</a>"));
                        itemURL.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

                        itemRating.setVisibility(View.VISIBLE);
                        itemRating.setRating(mi.getRating());

                        items.get(selectedItem).setRating((int) itemRating.getRating());
                    }
                }

                else {
                    itemHeading.setText("Please select an item");
                    itemDetails.setText("");
                    itemURL.setText("");
                    quantityText.setText("");
                    itemImage.setVisibility(View.GONE);
                    itemRating.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }

        });
    }



    /** PURPOSE: This method adds to the cart the selected item when the 'Add to Cart' button is clicked. If the item is
                 in the cart and is re-selected again, this method makes sure that the quantity is updated as follows:
                 - If an item with the same name already is in the cart, check both sizes. If the sizes of both objects match,
                    then we update the quantity of the existing item and is not re-added again.
                 - Otherwise, if the sizes differ, then we add the item to the cart as a separate object.
    */
    public void addToCart(View view) {
        if (!quantityText.getText().toString().equals("")) {
            MenuItem existingOrder = new MenuItem();
            int quantity = Integer.parseInt(quantityText.getText().toString());

            selectedItemSize = String.valueOf(sizeSpinner.getSelectedItem());
            mi.setSize(selectedItemSize);

            existingOrder.setName(mi.getName());
            existingOrder.setQuantity(mi.getQuantity() + quantity);
            existingOrder.setSize(mi.getSize());
            existingOrder.setPrice(mi.getPrice());
            existingOrder.setImageURL(mi.getImageURL());
            existingOrder.setNutritionFactsLink(mi.getNutritionFactsLink());

            // trivial case: cart is empty
            if (cart.size() == 0) {

                // for example, Chicken Soup - M. This is in case another Chicken soup is ordered and its size is different.
                // in the cart, these two items will have keys: Chicken Soup - M and Chicken Soup - L respectively.
                cart.put(mi.getName() + " - " + mi.getSize().substring(0, 1), existingOrder);

            }

            else {

                if (cart.containsKey(mi.getName() + " - " + mi.getSize().substring(0, 1))) {

                    existingOrder.setQuantity(cart.get(mi.getName() + " - " + mi.getSize().substring(0, 1)).getQuantity() + quantity);

                }

                cart.put(existingOrder.getName() + " - " + existingOrder.getSize().substring(0, 1), existingOrder);
            }

            Toast.makeText(MainActivity.this, "Item added to cart", Toast.LENGTH_LONG).show();

        }

        else {

            Toast.makeText(MainActivity.this, "Please, select at least one item from the menu", Toast.LENGTH_LONG).show();

        }
    }


    /** PURPOSE: This method is called when the 'Continue to checkout' button is clicked. This switches to another 
                 activity, ReviewOrderDetails activity, and its corresponding Views. If the cart is empty, no switch
                 action is performed. Otherwise, an Intent object is created, serializes the Map<String, MenuItem> cart
                 object and sends it along the new activity invokation. In the new activity, this object is de-serialized.
    */
    public void onCheckout(View view) {
        if (cart.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please, select at least one item from the menu", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, ReviewOrderDetails.class);

            intent.putExtra("CartObject", (Serializable) cart);

            startActivity(intent);

        }
    }

}
