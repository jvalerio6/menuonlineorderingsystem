/******************************************
 Programmer: Javier Valerio
 Date Originally Submitted: February 10th, 2017
 Last Modified on: February 20th, 2017
 Dr. Shrivastava
 CS 481 - Menu Ordering System - Part Two
 ******************************************/

package com.example.jvalerio.menuorderingsystem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PayPalTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.card_fragment, container, false);
    }
}
