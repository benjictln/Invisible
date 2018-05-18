package com.benjamincastellan.invisible;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class ExampleFragment extends Fragment {

    private boolean good = false;

    public static ExampleFragment newInstance(String text,Boolean bool) {

        ExampleFragment f = new ExampleFragment();

        Bundle b = new Bundle();
        b.putString("text", text);
        b.putBoolean("good", bool);
        f.setArguments(b);
        return f;
    }

    public void addDetails(String details) {
        // TODO: 18/05/2018 add details to the view
    }

    public void setGood(boolean good) {
        this.good = good;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.sample_element_test, container, false);

        ((TextView) v.findViewById(R.id.text_element)).setText(getArguments().getString("text"));
        if (good) {
            ((ImageView) v.findViewById(R.id.image_element)).setImageDrawable(getResources().getDrawable(R.drawable.good));
        }else{
            ((ImageView) v.findViewById(R.id.image_element)).setImageDrawable(getResources().getDrawable(R.drawable.bad));
        }
        return v;
    }
}